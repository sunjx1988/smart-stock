package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.StockDto;
import smart.stock.dto.StockFinanceDto;
import smart.stock.entity.Stock;
import smart.stock.mapper.StockFinanceMapper;
import smart.stock.mapper.StockMapper;
import smart.stock.spider.ZxcwzbSpider;
import smart.stock.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 14:46
 * @Description:
 */
@Slf4j
@Service
public class StockFinanceService {

    @Autowired
    private StockFinanceMapper stockFinanceMapper;

    @Autowired
    private ZxcwzbSpider zxcwzbSpider;

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

        if(StringUtils.isEmpty(param.getSpiderParamType())){
            //TODO 财务指标类型为空查全部
            zxcwzbSpider.zxcwzb(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZXCWZB.getKey())){
            zxcwzbSpider.zxcwzb(param.getSpiderParamStockCode(), param.getSpiderParamYear(), param.getSpiderParamDateType());
        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.CWBL.getKey())){

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.CFZ.getKey())){

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.LR.getKey())){

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.XJLL.getKey())){

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZYSRFB.getKey())){

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.ZCJZ.getKey())){

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.YSZK.getKey())){

        }else if(param.getSpiderParamType().equals(Constants.FinanceInfoTypes.QTYSZK.getKey())){

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
        int[] years = DateUtil.allYears();
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
        int[] years = DateUtil.allYears();

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
}
