import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

/**
 * @Auther: sunjx
 * @Date: 2019/3/8 0008 10:46
 * @Description:
 */
public class MainTest {

    public static void main(String[] args) {
        PasswordService passwordService = new DefaultPasswordService();
        String pwd = "123";
        String encryptPwd = passwordService.encryptPassword(pwd);

        System.out.println(encryptPwd);

        System.out.println(passwordService.passwordsMatch(pwd,encryptPwd));
    }
}
