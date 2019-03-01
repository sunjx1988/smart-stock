package smart.stock.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    private static final String DETAIL_PAGE = "user/detail";

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

    @RequestMapping(value = "{mode}/{id}", method = RequestMethod.GET)
    public String detailPage(@ModelAttribute("mode") String mode,
                             @ModelAttribute("id") Long id,
                             Model model){
        return DETAIL_PAGE;
    }

    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public String insertPage(Model model){
        model.addAttribute("mode","insert");
        return DETAIL_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public BaseResult<User> detail(@PathVariable("id") Long id, Model model){
        return BaseResult.success(userService.detail(id));
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResult<User> save(@RequestBody User user){
        Long id = userService.save(user);
        if(id > 0){
            return BaseResult.success(user);
        }else{
            return BaseResult.error("保存失败", user);
        }
    }
}

