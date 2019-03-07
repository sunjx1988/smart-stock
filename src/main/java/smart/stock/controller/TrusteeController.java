package smart.stock.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smart.stock.dto.TrusteeDto;
import smart.stock.entity.Trustee;
import smart.stock.service.TrusteeService;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 10:16
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/trustee")
public class TrusteeController {

    private static final String LIST_PAGE = "trustee/list";

    private static final String DETAIL_PAGE = "trustee/detail";

    @Autowired
    private TrusteeService trusteeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage(Model model){
        return LIST_PAGE;
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<TrusteeDto> list(TrusteeDto trusteeDto){
        PageHelper.startPage(trusteeDto.getPage(), trusteeDto.getRows());
        return new PageInfo(trusteeService.list());
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
    public BaseResult<TrusteeDto> detail(@PathVariable("id") Long id, Model model){
        return BaseResult.success(trusteeService.detail(id));
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public BaseResult<Trustee> save(@RequestBody Trustee trustee){
        Long id = trusteeService.save(trustee);
        if(id > 0){
            return BaseResult.success(trustee);
        }else{
            return BaseResult.error(trustee);
        }
    }
}


