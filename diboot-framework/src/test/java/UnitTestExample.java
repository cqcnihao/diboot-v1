import com.diboot.framework.model.BaseUser;
import com.diboot.framework.service.BaseUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 单元测试样例
 * @author Mazc@dibo.ltd
 * @version 2017/4/18
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringTestConfig.class})
public class UnitTestExample {

    @Autowired
    BaseUserService baseUserService;

    @Test
    public void testGetUser(){
        BaseUser user = baseUserService.getUserByUsername("admin");
        Assert.assertTrue(user != null);
    }

}
