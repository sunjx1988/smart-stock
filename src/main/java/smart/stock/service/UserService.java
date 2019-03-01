package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.stock.entity.User;
import smart.stock.mapper.UserMapper;

import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 15:52
 * @Description:
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> list(){
        return userMapper.list();
    }

    public User detail(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public Long save(User user) {
        //名字不能为空
        if(StringUtils.isEmpty(user.getName())){
            throw BaseException.error("名字不能为空",null);
        }

        //与其他人名字相同
        if(userMapper.countByNameAndId(user) > 0){
            throw BaseException.error("不能与他人名字相同",null);
        }
        userMapper.insert(user);
        return user.getId();
    }
}
