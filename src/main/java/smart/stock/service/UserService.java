package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
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
}
