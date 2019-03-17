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
import smart.stock.dto.TrusteeByDayDto;
import smart.stock.service.TrusteeByDayService;

/**
 * @Auther: sunjx
 * @Date: 2019/3/17 0017 16:07
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/trustee_by_day")
public class TrusteeByDayController {

    private static final String LIST_PAGE = "/trustee_by_day/list";

    @Autowired
    private TrusteeByDayService trusteeByDayService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage(Model model){
        return LIST_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<TrusteeByDayDto> list(TrusteeByDayDto trusteeByDayDto){
        PageHelper.startPage(trusteeByDayDto.getPage(), trusteeByDayDto.getRows());
        return new PageInfo(trusteeByDayService.list(trusteeByDayDto));
    }
}
