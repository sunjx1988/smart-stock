import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import smart.stock.Application;
import smart.stock.shiro.ShiroService;
import smart.stock.shiro.ShiroUser;

/**
 * @Auther: sunjx
 * @Date: 2019/3/8 0008 11:25
 * @Description:
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class ApplicationTests {

    @Autowired
    private ShiroService shiroService;

    private PasswordService passwordService = new DefaultPasswordService();

    @Before
    public void setUp() {

    }

    @Test
    public void test() throws Exception {
        ShiroUser user = new ShiroUser();
        user.setName("sun");
        user.setPhone("123");
        user.setCommitPwd("456");

        System.out.println(passwordService.encryptPassword(user.getCommitPwd()));
//        JSON.toJSONString(shiroService.login(user.getPhone(), user.getCommitPwd()));
//        shiroService.save(user);
    }

}
