package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.entity.StockTrade;
import smart.stock.entity.StockTradeExample;

public interface StockTradeMapper {
    long countByExample(StockTradeExample example);

    int deleteByExample(StockTradeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StockTrade record);

    int insertSelective(StockTrade record);

    List<StockTrade> selectByExample(StockTradeExample example);

    StockTrade selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StockTrade record, @Param("example") StockTradeExample example);

    int updateByExample(@Param("record") StockTrade record, @Param("example") StockTradeExample example);

    int updateByPrimaryKeySelective(StockTrade record);

    int updateByPrimaryKey(StockTrade record);
}