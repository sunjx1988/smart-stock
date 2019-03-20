package smart.stock.spider;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.StockDto;
import smart.stock.dto.StockFinanceDto;
import smart.stock.mapper.StockFinanceMapper;
import smart.stock.mapper.StockMapper;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/20 0020 10:37
 * @Description:
 */
public abstract class StockFinancePipeline implements Pipeline {

    @Autowired
    private StockFinanceMapper stockFinanceMapper;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<String> params = processCodeAndDate(resultItems.getRequest().getUrl(), SpiderUrlConst.ZXCWZB);
        String code = params.get(0);
        String dateParam = params.get(1);
        String dateParamSuffix = dateParam.substring(4);
        StockDto stockDto = stockMapper.selectByCode(code);

        if (!CollectionUtils.isEmpty(resultItems.getAll())) {
            StockFinanceDto stockFinance = new StockFinanceDto();
            stockFinance.setName(stockDto.getName());
            stockFinance.setCode(code);
            stockFinance.setType(Constants.FinanceInfoTypes.ZXCWZB.getKey());
            stockFinance.setDate(dateParam);
            stockFinance.setDateType(Constants.FinanceDateTypes.getKeyByText(dateParamSuffix));
            stockFinance.setInfo(JSON.toJSONString(resultItems.getAll()));
            stockFinance.setInfoVersion(0);
            //删除该条件的数据,再保存新数据,条件code,type,date
            stockFinanceMapper.deleteByCodeAndTypeAndDateType(stockFinance.getCode(), stockFinance.getType(), dateParam);
            stockFinance.setCreateTime(new Date());
            stockFinanceMapper.insert(stockFinance);
        }
    }

    //返回code 和 日期
    protected abstract List<String> processCodeAndDate(String url, String urlTemplate);
}
