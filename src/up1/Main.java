package up1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {

    private static String USER_NAME = "*****";  // GMail user name (just the part before "@gmail.com")
    private static String PASSWORD = "********"; // GMail password
    private static String RECIPIENT = "lizard.bill@myschool.edu";

    public static void main(String[] args) {
        Main main = new Main();
        main.sendEmail();
    }

    public void sendEmail() {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";

        sendTextEmail(from, pass, to, subject, body);
    }

    private void sendTextEmail(String from, String password, String[] to, String subject, String body) {
        try {
            MimeMessage mimeMessage = createTextEmail(from, password, to, subject, body);
            Transport.send(mimeMessage);
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }

    private MimeMessage createTextEmail(String from, String password, String[] to, String subject, String body) {
        Session session = getSession(from, password);
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

    private Session getSession(String from, String password) {
        Properties properties = System.getProperties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        return Session.getDefaultInstance(properties);
    }

    private static void setToAddresses(String[] to, MimeMessage message) throws MessagingException {
        InternetAddress[] toAddress = new InternetAddress[to.length];

        // To get the array of addresses
        for (int i = 0; i < to.length; i++) {
            toAddress[i] = new InternetAddress(to[i]);
        }

        for (int i = 0; i < toAddress.length; i++) {
            message.addRecipient(Message.RecipientType.TO, toAddress[i]);
        }
    }
}
