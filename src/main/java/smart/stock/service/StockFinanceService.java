package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.Options;
import smart.stock.dto.StockDto;
import smart.stock.dto.StockFinanceDto;
import smart.stock.entity.Stock;
import smart.stock.mapper.StockFinanceMapper;
import smart.stock.mapper.StockMapper;
import smart.stock.spider.*;
import smart.stock.util.DateUtil;

import java.util.*;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 14:46
 * @Description:
 */
@Slf4j
@Service
public class StockFinanceService {
    //财务数据抓取的起始年份
    @Value("${setting.fetch-begin-year:2010}")
    private int fetchFinanceInfoBeginYear;

    @Autowired
    private StockFinanceMapper stockFinanceMapper;

    @Autowired
    private ZxcwzbSpider zxcwzbSpider;
    @Autowired
    private CwblSpider cwblSpider;
    @Autowired
    private ZcfzSpider zcfzSpider;
    @Autowired
    private LrSpider lrSpider;
    @Autowired
    private XjllSpider xjllSpider;
    @Autowired
    private ZysrfbSpider zysrfbSpider;
    @Autowired
    private ZcjzSpider zcjzSpider;
    @Autowired
    private YszkSpider yszkSpider;
    @Autowired
    private QtyszkSpider qtyszkSpider;

    @Autowired
    private StockMapper stockMapper;

    public void financeFetch(StockFinanceDto param){
        //年份不能为空
        if(StringUtils.isEmpty(param.getSpiderParamYear())){
            throw BaseException.error("年份不能为空", param);
        }
        //股票代码不能为空
        if(StringUtils.isEmpty(param.getSpiderParamStockCode())){
            throw BaseException.error("股票代码不能为空", param);
        }

        //财务指标类型为空查全部
        if(StringUtils.isEmpty(param.getSpiderParamType())){
            zxcwzbSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            cwblSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            zcfzSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            lrSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            xjllSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            zysrfbSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            zcjzSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            yszkSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
            qtyszkSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZXCWZB.getKey())){
            zxcwzbSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.CWBL.getKey())){
            cwblSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZCFZ.getKey())){
            zcfzSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.LR.getKey())){
            lrSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.XJLL.getKey())){
            xjllSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZYSRFB.getKey())){
            zysrfbSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZCJZ.getKey())){
            zcjzSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.YSZK.getKey())){
            yszkSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.QTYSZK.getKey())){
            qtyszkSpider.fetch(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }
    }

    @Transactional
    public Integer batchSave(List<StockFinanceDto> list){
        if(!CollectionUtils.isEmpty(list)){
            for(StockFinanceDto dto: list){
                dto.setCreateTime(new Date());
            }
            return stockFinanceMapper.insertBatch(list);
        }
        return 0;
    }

    //抓取某股票全部报表
    public void financeFetchByCode(String code) {
        //遍历年份
        int[] years = DateUtil.allYears(fetchFinanceInfoBeginYear);
        for(int year: years){
            StockFinanceDto param = new StockFinanceDto();
            param.setCode(code);
            param.setSpiderParamYear(String.valueOf(year));
            financeFetch(param);
        }
    }

    //抓取某年份的全部报表
    public void financeFetchByYear(String year) {
        //遍历股票
        List<StockDto> stocks = stockMapper.list(null);
        if(!CollectionUtils.isEmpty(stocks)){
            for(Stock stock: stocks){
                StockFinanceDto param = new StockFinanceDto();
                param.setSpiderParamYear(year);
                param.setSpiderParamStockCode(stock.getCode());
                financeFetch(param);
            }
        }
    }

    //抓取所有股票所有年份全部报表
    public void financeFetchAll() {
        //遍历股票
        List<StockDto> stocks = stockMapper.list(null);
        //遍历年份
        int[] years = DateUtil.allYears(fetchFinanceInfoBeginYear);

        if(!CollectionUtils.isEmpty(stocks)){
            for(int year: years){
                for(Stock stock: stocks){
                    StockFinanceDto param = new StockFinanceDto();
                    param.setSpiderParamYear(String.valueOf(year));
                    param.setSpiderParamStockCode(stock.getCode());
                    financeFetch(param);
                }
            }
        }
    }

    public List<StockFinanceDto> list(StockFinanceDto param) {
        if(!StringUtils.isEmpty(param.getParamStockId())
                && StringUtils.isEmpty(param.getParamCode())){
            StockDto stockDto = stockMapper.detail(param.getParamStockId());
            param.setParamCode(stockDto.getCode());
        }

        if(!StringUtils.isEmpty(param.getParamYear())
                && null != param.getDateType() && param.getDateType() > 0){
            param.setParamDate(param.getParamYear() + Constants.FinanceDateTypes.getTextByKey(param.getDateType()));
        }

        if(!StringUtils.isEmpty(param.getParamStartYear()) && param.getParamDateType() > 0){
            param.setParamStartYear(param.getParamStartYear() + Constants.FinanceDateTypes.getTextByKey(param.getParamDateType()));
        }

        if(!StringUtils.isEmpty(param.getParamEndYear()) && param.getParamDateType() > 0){
            param.setParamEndYear(param.getParamEndYear() + Constants.FinanceDateTypes.getTextByKey(param.getParamDateType()));
        }

        return stockFinanceMapper.list(param);
    }

    public List<Options> dateOptions(Long stockId) {
        Stock stock = stockMapper.selectByPrimaryKey(stockId);
        List<Options> list = stockFinanceMapper.dateOptions(stock.getCode());

        if(!CollectionUtils.isEmpty(list)){
            for(int i = 0 ; i < list.size() ; i ++){
                Options option = list.get(i);
                option.setText(option.getText().split("\\.")[0]);
                option.setValue(option.getText());
            }
            Set<Options> set = new HashSet<>(list);
            return new ArrayList<>(set);
        }
        return null;
    }
}
