package smart.stock.mapper;

import smart.stock.shiro.ShiroUser;

import java.util.Set;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 17:31
 * @Description:
 */
public interface ShiroMapper {
    ShiroUser findUserByName(String name);

    Set<String> getRolesByUserId(Long id);

    Set<String> getPermsByUserId(Long id);
}
