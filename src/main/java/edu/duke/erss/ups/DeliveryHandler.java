package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.UserDao;
import edu.duke.erss.ups.dao.UserTrackingDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.entity.User;
import edu.duke.erss.ups.proto.UPStoWorld.UDeliveryLocation;
import edu.duke.erss.ups.proto.UPStoWorld.UDeliveryMade;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class DeliveryHandler extends WorldCommandHandler {
    ArrayList<UDeliveryLocation> locations;
    ShipInfo shipInfo;

    private TrackingShipDao trackingShipDao;
    private UserDao userDao;

    DeliveryHandler(long seq, WorldController worldController, ShipInfo shipInfo, TrackingShipDao trackingShipDao, UserDao userDao) {
        super(seq, shipInfo.getTruckID(), worldController);
        this.locations = new ArrayList<>();
        this.shipInfo = shipInfo;
        this.trackingShipDao = trackingShipDao;
        this.userDao = userDao;
    }

    public void addLocations(ArrayList<UDeliveryLocation> locations) {
        this.locations.addAll(locations);
    }

    @Override
    public void setTimerAndTask() {
        resend = new TimerTask() {
            @Override
            public void run() {
                try {
                    worldController.goDeliver(shipInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(resend, TIME_OUT);
    }

    @Override
    public void onReceive(UResponses uResponses, int index) {
        cancelTimer();
        new Thread(() -> {
            try {
                if (uResponses.getErrorCount() != 0) {
                    System.out.println("Error delivering: " + uResponses.getError(index).getErr() + ". Seq = " +
                            uResponses.getError(index).getOriginseqnum());
                    worldController.sendAckCommand(uResponses.getError(index).getSeqnum());
                    return;
                }
                UDeliveryMade uDeliveryMade = uResponses.getDelivered(index);
                System.out.println("Package " + uDeliveryMade.getPackageid() + " of truck " + uDeliveryMade.getTruckid() + " delivered");
                UDeliveryLocation toDelete = null;
                for (UDeliveryLocation location : locations) {
                    if (location.getPackageid() == uDeliveryMade.getPackageid()) {
                        toDelete = location;
                        break;
                    }
                }
                locations.remove(toDelete);
                if (locations.isEmpty()) {
                    worldController.seqHandlerMap.remove(seq);
                }
                worldController.sendAckCommand(uDeliveryMade.getSeqnum());

                // database: Package delivered
                shipInfo.setStatus(ShipStatus.DELIVERED.getText());
                trackingShipDao.updateTracking(shipInfo);

                //stop tracking
                worldController.trackingRecords.remove(shipInfo.getTrackingID());

                // inform amazon
                worldController.amazonController.sendPackageDelivered(shipInfo);
                User user = this.userDao.getUserByTrackingID(shipInfo.getTrackingID()).get(0);
                String from = "shaoyf98@gmail.com";
                String to = user.getEmail();
                String subject = "Your package has been delivered!";
                String msg = "Dear " + user.getName() + ", your shipment has been delivered!";
                sendEmail(from, to, subject, msg);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }


    public static void sendEmail(String from, String to, String subject, String msg) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "rqahmutmfvbzdpmt");
            }
        });
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);
            // send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
