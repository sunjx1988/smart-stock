package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.entity.StockPrice;
import smart.stock.entity.StockPriceExample;

public interface StockPriceMapper {
    long countByExample(StockPriceExample example);

    int deleteByExample(StockPriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StockPrice record);

    int insertSelective(StockPrice record);

    List<StockPrice> selectByExample(StockPriceExample example);

    StockPrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StockPrice record, @Param("example") StockPriceExample example);

    int updateByExample(@Param("record") StockPrice record, @Param("example") StockPriceExample example);

    int updateByPrimaryKeySelective(StockPrice record);

    int updateByPrimaryKey(StockPrice record);
}