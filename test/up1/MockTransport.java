package up1;

import javax.mail.*;

public class MockTransport extends Transport {
    public boolean isSend = false;
    public boolean isConnect = false;

    public MockTransport(Session session, URLName urlname) {
        super(session, urlname);
    }

    @Override
    public void connect(String host, String user, String password) throws MessagingException {
        isConnect = true;
    }

    @Override
    public void sendMessage(Message message, Address[] addresses) throws MessagingException {
        isSend = true;
    }

    @Override
    public synchronized void close() throws MessagingException {
    }
}
