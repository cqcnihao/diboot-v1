import com.diboot.components.config.MsgCons;
import com.diboot.components.msg.sms.config.MsgConfig;
import com.diboot.framework.utils.V;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mazc@dibo.ltd
 * @version 2017/9/14
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringTestConfig.class})
public class TestSms {

    @Test
    public void testSystemConfig(){
        String key = "sms.eton.mturl";
        String cfg = MsgConfig.getValueFromFileAndDb(MsgCons.SUBCATEGORY.SMS_ETON,key);
        Assert.assertTrue(V.notEmpty(cfg));
    }
}
