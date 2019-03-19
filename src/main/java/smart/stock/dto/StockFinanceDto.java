package smart.stock.dto;

import lombok.Data;
import smart.stock.entity.StockFinance;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 15:04
 * @Description:
 */
@Data
public class StockFinanceDto extends StockFinance {

    private int page = 1;
    private int rows = 10;

    //spider 抓取条件
    //股票代码
    private String spiderParamStockCode;
    //日期type <= 0 查全部的
    private int spiderParamDateType;
    //年份
    private String spiderParamYear;
    //财务指标类型type
    private String spiderParamType;
}
