package smart.stock.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import smart.stock.dto.UserDto;
import smart.stock.entity.User;
import smart.stock.service.UserService;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 10:16
 * @Description:
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    private static final String LIST_PAGE = "user/list";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage(Model model){
        return LIST_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<User> list(UserDto userDto){
        PageHelper.startPage(userDto.getPage(), userDto.getRows());
        return new PageInfo(userService.list());
    }
}

