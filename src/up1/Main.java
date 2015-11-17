package up1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {

    private static String GMAIL_USER_NAME = "*****";
    private static String GMAIL_PASSWORD = "********";

    public void sendEmail(String from, String to, String subject, String body) {
        sendTextEmail(from, to, subject, body);
    }

    public void sendTextEmail(String from, String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = createTextEmail(from, to, subject, body);
            Transport.send(mimeMessage);
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }

    public MimeMessage createTextEmail(String from, String to, String subject, String body) {
        Session session = getSession();
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            Address[] tos = new InternetAddress[]{new InternetAddress(to)};
            Address[] froms = new InternetAddress[]{new InternetAddress(from)};
            mimeMessage.setFrom(froms[0]);
            mimeMessage.setRecipients(Message.RecipientType.TO, tos);
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);
            return  mimeMessage;
        } catch (MessagingException messagingException) {
            throw new RuntimeException(messagingException);
        }
    }

    public Session getSession() {
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
}
