package smart.stock.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.dto.FundStockDto;
import smart.stock.dto.StockTradeDto;
import smart.stock.entity.Fund;
import smart.stock.entity.Stock;
import smart.stock.entity.StockTrade;
import smart.stock.mapper.FundMapper;
import smart.stock.mapper.FundStockMapper;
import smart.stock.mapper.StockMapper;
import smart.stock.mapper.StockTradeMapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/10 0010 17:17
 * @Description:
 */
@Slf4j
@Service
public class StockTradeService {

    @Autowired
    private StockTradeMapper stockTradeMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private FundMapper fundMapper;

    @Autowired
    private FundStockMapper fundStockMapper;

    public List<StockTradeDto> list(StockTradeDto stockTradeDto) {
        List<StockTradeDto> list = stockTradeMapper.list(stockTradeDto);
        if(!CollectionUtils.isEmpty(list)){
            for(StockTradeDto dto: list){
                dto.setTypeText(Constants.StockTradeTypes.getTextByKey(dto.getType()));
            }
        }
        return list;
    }

    @Transactional
    public Long save(StockTrade stockTrade) {

        if(null == stockTrade.getFundId()){
            throw BaseException.error("基金代码不能为空",null);
        }

        //股票代码不能为空
        if(StringUtils.isEmpty(stockTrade.getCode())){
            throw BaseException.error("股票代码不能为空",null);
        }

        //交易类型不能为空
        if(null == stockTrade.getType()){
            throw BaseException.error("交易类型不能为空",null);
        }

        //成本价不能为空
        if(null == stockTrade.getUnitPrice()){
            throw BaseException.error("成本价不能为空",null);
        }

        if(stockTrade.getUnitPrice().doubleValue() <= 0){
            throw BaseException.error("成本价必须大于0",null);
        }

        //成交数量不能为空
        if(null == stockTrade.getUnit()){
            throw BaseException.error("成交数量不能为空",null);
        }

        if(stockTrade.getUnit().doubleValue() <= 0){
            throw BaseException.error("成交数量必须大于0",null);
        }

        Stock stock = stockMapper.selectByCode(stockTrade.getCode());

        if(null == stock){
            throw BaseException.error("该股票["+stockTrade.getCode()+"]未添加",null);
        }

        Fund fund = fundMapper.selectByPrimaryKey(stockTrade.getFundId());

        stockTrade.setName(stock.getName());
        stockTrade.setFundName(fund.getName());
        stockTrade.setCreateTime(new Date());
        stockTrade.setDate(DateFormatUtils.format(stockTrade.getCreateTime(),"yyyyMMdd"));
        stockTrade.setTotal(stockTrade.getUnitPrice().multiply(new BigDecimal(stockTrade.getUnit())));
        stockTradeMapper.insert(stockTrade);

        //交易额不能大于基金现金余额
        if(fund.getBanlance().compareTo(stockTrade.getTotal()) < 0){
            throw BaseException.error("交易金额不得大于基金现金余额",null);
        }

        if(stockTrade.getType() == Constants.StockTradeTypes.Buy.getKey()){
            //扣减基金现金余额
            fund.setBanlance(fund.getBanlance().subtract(stockTrade.getTotal()));
        }else {
            //增加基金现金余额
            fund.setBanlance(fund.getBanlance().add(stockTrade.getTotal()));
        }


        //重新计算仓位 保留两位,四舍五入
        fund.setPosition(fund.getPrincipal().subtract(fund.getBanlance()).divide(fund.getPrincipal(),2, BigDecimal.ROUND_HALF_UP));
        fundMapper.updateByPrimaryKey(fund);

        //更新fundstock记录
        FundStockDto fundStockDto;
        FundStockDto fundStockParam = new FundStockDto();
        fundStockParam.setParamFundId(fund.getId());
        fundStockParam.setCode(stock.getCode());
        List<FundStockDto> fundStockList = fundStockMapper.list(fundStockParam);

        if(CollectionUtils.isEmpty(fundStockList)){
            fundStockDto = new FundStockDto();
            fundStockDto.setName(stock.getName());
            fundStockDto.setCode(stock.getCode());
            fundStockDto.setUnitPrice(BigDecimal.ZERO);
            fundStockDto.setUnit(0);
            fundStockDto.setTotal(BigDecimal.ZERO);
            fundStockDto.setFundId(fund.getId());
            fundStockDto.setFundName(fund.getName());
            fundStockDto.setCreateTime(new Date());
            fundStockDto.setMarketType(-1);
            fundStockDto.setStatus(Constants.FundStockStatus.Holding.getKey());
        }else{
            fundStockDto = fundStockList.get(0);
        }

        fundStockDto.setUnit(stockTrade.getUnit() + fundStockDto.getUnit());
        fundStockDto.setTotal(fundStockDto.getTotal().add(stockTrade.getTotal()));
        //计算成本价
        fundStockDto.setUnitPrice(fundStockDto.getTotal().divide(new BigDecimal(fundStockDto.getUnit()), 2, BigDecimal.ROUND_HALF_UP));

        if(fundStockDto.getId() == null){
            fundStockMapper.insert(fundStockDto);
        }else{
            fundStockMapper.updateByPrimaryKey(fundStockDto);
        }

        return stockTrade.getId();
    }

}
