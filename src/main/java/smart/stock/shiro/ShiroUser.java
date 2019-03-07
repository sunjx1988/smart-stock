package smart.stock.shiro;

import lombok.Data;

import java.util.Set;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 17:14
 * @Description:
 */
@Data
public class ShiroUser {

    private Long id;

    private String pwd;

    private String salt;

    private Set<String> roles;

    private Set<String> perms;
}
