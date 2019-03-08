package smart.stock.mapper;

import org.apache.ibatis.annotations.Param;
import smart.stock.shiro.ShiroUser;

import java.util.Set;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 17:31
 * @Description:
 */
public interface ShiroMapper {

    ShiroUser findUserByPhone(String name);

    Set<String> getRolesByUserId(Long userId);

    Set<String> getPermsByUserId(Long userId);

    int batchDeleteUserRole(Long userId);

    int batchSaveUserRole(@Param("userId") Long userId, @Param("roleKeys") Set<String> roleKeys);
}
