package up1;


import org.junit.Ignore;
import org.junit.Test;

public class SendMailTest {


    @Test
    public void real_new_send_main_with_parameter() {
        Main main = new Main();
        main.sendEmail("somkiat@xxx.com", "roofimin@xxx.com", "subject", "body");
    }


}
