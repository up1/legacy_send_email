package up1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {

    private static String GMAIL_USER_NAME = "*****";
    private static String GMAIL_PASSWORD = "********";

    public void sendEmail(String from, String to, String subject, String body) {
        sendTextEmail(from, new String[]{to}, subject, body);
    }

    private void sendTextEmail(String from, String[] to, String subject, String body) {
        try {
            MimeMessage mimeMessage = createTextEmail(from, to, subject, body);
            Transport.send(mimeMessage);
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }

    private MimeMessage createTextEmail(String from, String[] to, String subject, String body) {
        Session session = getSession();
        try {
            MimeMessage mineMessage = new MimeMessage(session);
            mineMessage.setFrom(new InternetAddress(from));
            setToAddresses(to, mineMessage);
            mineMessage.setSubject(subject);
            mineMessage.setText(body);
            return  mineMessage;
        } catch (MessagingException messagingException) {
            throw new RuntimeException(messagingException);
        }
    }

    private Session getSession() {
        Properties properties = System.getProperties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", GMAIL_USER_NAME);
        properties.put("mail.smtp.password", GMAIL_PASSWORD);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        return Session.getDefaultInstance(properties);
    }

    private static void setToAddresses(String[] to, MimeMessage message) throws MessagingException {
        InternetAddress[] toAddress = new InternetAddress[to.length];

        for (int i = 0; i < to.length; i++) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for (int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }
    }
}
