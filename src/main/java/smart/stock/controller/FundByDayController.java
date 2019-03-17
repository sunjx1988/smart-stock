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
import smart.stock.dto.FundByDayDto;
import smart.stock.service.FundByDayService;

/**
 * @Auther: sunjx
 * @Date: 2019/3/17 0017 10:19
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/fund_by_day")
public class FundByDayController {

    private static final String LIST_PAGE = "/fundbyday/list";

    @Autowired
    private FundByDayService fundByDayService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage(Model model){
        return LIST_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<FundByDayDto> list(FundByDayDto fundByDayDto){
        PageHelper.startPage(fundByDayDto.getPage(), fundByDayDto.getRows());
        return new PageInfo(fundByDayService.list(fundByDayDto));
    }


}
