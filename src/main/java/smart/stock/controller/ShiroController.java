package smart.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import smart.stock.shiro.ShiroService;
import smart.stock.shiro.ShiroUser;

/**
 * @Auther: sunjx
 * @Date: 2019/3/8 0008 13:44
 * @Description:
 */
@Slf4j
@Controller
public class ShiroController {

    @Autowired
    private ShiroService shiroService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String unauthorized(){
        return "403";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult<ShiroUser> login(@RequestParam("phone") String phone,
                                       @RequestParam("commitPwd") String pwd){
        return BaseResult.success(shiroService.login(phone,pwd));
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseResult<ShiroUser> logout(){
        shiroService.logout();
        return BaseResult.success(null);
    }
}
