package smart.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 10:17
 * @Description:
 */
@Controller
@Slf4j
@RequestMapping("")
public class IndexController {

    @RequestMapping("")
    public String index(){
        return "index";
    }

    @RequestMapping("layout")
    public String layout(){
        return "layout/layout";
    }
}
