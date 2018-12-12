import com.diboot.common.service.UserService;
import com.diboot.framework.model.BaseUser;
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
    UserService userService;

    @Test
    public void testGetUser(){
        BaseUser user = userService.getUserByUsername("admin");
        Assert.assertTrue(user != null);
        System.out.println(user.getRealname());
    }

}
