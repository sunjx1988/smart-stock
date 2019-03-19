package smart.stock.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import smart.stock.entity.StockFinance;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 15:04
 * @Description:
 */
@Data
public class StockFinanceDto extends StockFinance {

    //列表查询条件
    @JsonIgnore
    private Long paramStockId;
    @JsonIgnore
    private String paramCode;
    @JsonIgnore
    private String paramYear;
    @JsonIgnore
    private int paramDateType;
    @JsonIgnore
    private String paramDate;
    @JsonIgnore
    private String paramType;
    @JsonIgnore
    private int page = 1;
    @JsonIgnore
    private int rows = 10;

    //spider 抓取条件
    //股票代码
    @JsonIgnore
    private String spiderParamStockCode;
    //日期type <= 0 查全部的
    @JsonIgnore
    private int spiderParamDateType;
    //年份
    @JsonIgnore
    private String spiderParamYear;
    //财务指标类型type
    @JsonIgnore
    private String spiderParamType;
}
