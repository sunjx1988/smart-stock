package smart.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smart.stock.dto.FundDto;
import smart.stock.entity.Fund;
import smart.stock.service.FundService;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 15:32
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/fund")
public class FundController {

    private static final String DETAIL_PAGE = "fund/detail";

    @Autowired
    private FundService fundService;

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
    public BaseResult<FundDto> detail(@PathVariable("id") Long id, Model model){
        return BaseResult.success(fundService.detail(id));
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResult<Fund> save(@RequestBody Fund fund){
        Long id = fundService.save(fund);
        if(id > 0){
            return BaseResult.success(fund);
        }else{
            return BaseResult.error(fund);
        }
    }
}
