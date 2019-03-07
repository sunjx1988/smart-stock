package smart.stock.shiro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smart.stock.mapper.ShiroMapper;

import java.util.Set;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 17:25
 * @Description:
 */
@Slf4j
@Component
public class ShiroService {

    @Autowired
    private ShiroMapper shiroMapper;

    public ShiroUser findUserByName(String name){
        return shiroMapper.findUserByName(name);
    }

    public Set<String> getRolesByUserId(Long id) {
        return shiroMapper.getRolesByUserId(id);
    }

    public Set<String> getPermsByUserId(Long id) {
        return shiroMapper.getPermsByUserId(id);
    }
}
