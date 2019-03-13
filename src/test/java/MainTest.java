import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: sunjx
 * @Date: 2019/3/8 0008 10:46
 * @Description:
 */
public class MainTest {

    public static void main(String[] args) throws Exception{
//        PasswordService passwordService = new DefaultPasswordService();
//        String pwd = "123";
//        String encryptPwd = passwordService.encryptPassword(pwd);
//
//        System.out.println(encryptPwd);
//
//        System.out.println(passwordService.passwordsMatch(pwd,encryptPwd));

        Date date = DateUtils.parseDate("2019-03-13 01:00:00","yyyy-MM-dd HH:mm:ss");

        System.out.println(DateFormatUtils.format(DateUtils.ceiling(date, Calendar.DATE), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateFormatUtils.format(DateUtils.truncate(date, Calendar.DATE), "yyyy-MM-dd HH:mm:ss"));
    }
}
