package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.dto.StockDto;
import smart.stock.entity.Stock;
import smart.stock.entity.StockExample;

public interface StockMapper {
    long countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    List<StockDto> list(StockDto stockDto);

    StockDto detail(Long id);
}
