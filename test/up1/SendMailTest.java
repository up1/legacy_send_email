package up1;


import org.junit.Ignore;
import org.junit.Test;

import javax.mail.Session;

import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class SendMailTest {

    @Test
    public void mock_transport_for_send_email_success() {
        Properties properties = System.getProperties();
        MockTransport mockTransport = new MockTransport(Session.getDefaultInstance(properties), null);
        Main main = new Main();
        main.setTransport( mockTransport );

        main.sendEmail("somkiat@xxx.com", "roofimin@xxx.com", "subject", "body");

        assertTrue("Call method connect to connect to smtp server", mockTransport.isConnect);
        assertTrue("Call method sendMessage to send email", mockTransport.isSend);
    }


}
