package edu.duke.erss.ups.Controller;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class test {

    public static void main(String[] args) {
        sendEmail("shaoyf98@gmail.com", "shaoyifan98@126.com", "hello", "hello");
    }

    public static void sendEmail(String from, String to, String subjuct, String msg) {

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
            message.setSubject(subjuct);
            message.setText(msg);
            // send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    // rqahmutmfvbzdpmt

}
