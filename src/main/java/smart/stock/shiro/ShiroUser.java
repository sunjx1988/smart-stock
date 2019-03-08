package smart.stock.shiro;

import lombok.Data;
import smart.stock.entity.Trustee;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 17:14
 * @Description:
 */
@Data
public class ShiroUser extends Trustee {

    //用户输入的密码
    private String commitPwd;

    private Set<String> roles = new HashSet<>();

    private Set<String> perms = new HashSet<>();;

    private boolean isAdmin = false;
}
