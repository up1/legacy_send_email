package up1;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {

    private static final String GMAIL_USER_NAME = "*****";
    private static final String GMAIL_PASSWORD = "********";
    private static final String GMAIL_HOST = "smtp.gmail.com";
    private Session session = null;
    private Transport transport = null;

    public void sendEmail(String from, String to, String subject, String body) {
        sendTextEmail(from, to, subject, body);
    }

    public void sendTextEmail(String from, String to, String subject, String body) {
        this.session = getSession();
        try {
            MimeMessage mimeMessage = createTextEmail(this.session, from, to, subject, body);
            Transport transport = getTransport();
            transport.connect(GMAIL_HOST, GMAIL_USER_NAME, GMAIL_PASSWORD);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }
    }

    public MimeMessage createTextEmail(Session session, String from, String to, String subject, String body) {
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
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", GMAIL_HOST);
        properties.put("mail.smtp.user", GMAIL_USER_NAME);
        properties.put("mail.smtp.password", GMAIL_PASSWORD);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        return Session.getDefaultInstance(properties);
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    private Transport getTransport() throws NoSuchProviderException {
        if( this.transport == null ) {
            return this.session.getTransport("smtp");
        }
        return this.transport;
    }
}
