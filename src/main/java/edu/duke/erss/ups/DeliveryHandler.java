package edu.duke.erss.ups;

import edu.duke.erss.ups.dao.TrackingShipDao;
import edu.duke.erss.ups.dao.TruckDao;
import edu.duke.erss.ups.dao.UserDao;
import edu.duke.erss.ups.dao.UserTrackingDao;
import edu.duke.erss.ups.entity.ShipInfo;
import edu.duke.erss.ups.entity.ShipStatus;
import edu.duke.erss.ups.entity.Truck;
import edu.duke.erss.ups.entity.User;
import edu.duke.erss.ups.proto.UPStoWorld.UDeliveryLocation;
import edu.duke.erss.ups.proto.UPStoWorld.UDeliveryMade;
import edu.duke.erss.ups.proto.UPStoWorld.UResponses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private TruckDao truckDao;

    DeliveryHandler(long seq, WorldController worldController, ShipInfo shipInfo, TrackingShipDao trackingShipDao, UserDao userDao, TruckDao truckDao) {
        super(seq, shipInfo.getTruckID(), worldController);
        this.locations = new ArrayList<>();
        this.shipInfo = shipInfo;
        this.trackingShipDao = trackingShipDao;
        this.userDao = userDao;
        this.truckDao = truckDao;
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
                    System.out.println("Resend go deliver seq=" + seq + ", tracking=" + shipInfo.getTrackingID());
                    worldController.goDeliver(seq, shipInfo);
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
                    int errIdx = index - uResponses.getCompletionsCount() - uResponses.getDeliveredCount() - uResponses.getTruckstatusCount();
                    System.out.println("Error delivering: " + uResponses.getError(errIdx).getErr() + ". Seq = " +
                            uResponses.getError(errIdx).getOriginseqnum());
                    worldController.sendAckCommand(uResponses.getError(errIdx).getSeqnum());
                    return;
                }

                UDeliveryMade uDeliveryMade = getDeliver(uResponses, index);
                System.out.println("Package " + uDeliveryMade.getPackageid() + " of truck " + uDeliveryMade.getTruckid() + " delivered");
                worldController.sendAckCommand(uDeliveryMade.getSeqnum()); // acking

                UDeliveryLocation toDelete = null;
                for (UDeliveryLocation location : locations) {
                    if (location.getPackageid() == uDeliveryMade.getPackageid()) {
                        toDelete = location;
                        break;
                    }
                }
                locations.remove(toDelete);
                if (locations.isEmpty()) {
                    System.out.println("@Sequence: resolving deliver " + uResponses.getAcks(index));
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
                emailInform();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

    private void emailInform() {
        List<User> users = this.userDao.getUserByTrackingID(shipInfo.getTrackingID());
        if (users != null && !users.isEmpty()) {
            User user = users.get(0);
            String from = "shaoyf98@gmail.com";
            String to = user.getEmail();
            String subject = "Your package has been delivered!";
            String msg = "Dear " + user.getName() + ", your shipment has been delivered!";
            sendEmail(from, to, subject, msg);
            return;
        }
        throw new IllegalArgumentException("User not exist! email not sent.");
    }

    private UDeliveryMade getDeliver(UResponses uResponses, int index) {
        int deliverIdx;
        int countOfContent = uResponses.getCompletionsCount() + uResponses.getDeliveredCount() +
                uResponses.getTruckstatusCount() + uResponses.getErrorCount();
        UDeliveryMade uDeliveryMade;
        if (uResponses.getAcksCount() == countOfContent) {
            deliverIdx = index - uResponses.getCompletionsCount();
            uDeliveryMade = uResponses.getDelivered(deliverIdx);
        }
        else {
            int diff = countOfContent - uResponses.getAcksCount();
            deliverIdx = index - uResponses.getCompletionsCount() + diff;
            // Truck finished
            uDeliveryMade = uResponses.getDelivered(deliverIdx);
            truckDao.updateTruckStatus(uDeliveryMade.getTruckid(), Truck.Status.IDLE.getText());
        }
        return uDeliveryMade;
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
