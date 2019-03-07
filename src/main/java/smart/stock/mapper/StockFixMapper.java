package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.entity.StockFix;
import smart.stock.entity.StockFixExample;

public interface StockFixMapper {
    long countByExample(StockFixExample example);

    int deleteByExample(StockFixExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StockFix record);

    int insertSelective(StockFix record);

    List<StockFix> selectByExample(StockFixExample example);

    StockFix selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StockFix record, @Param("example") StockFixExample example);

    int updateByExample(@Param("record") StockFix record, @Param("example") StockFixExample example);

    int updateByPrimaryKeySelective(StockFix record);

    int updateByPrimaryKey(StockFix record);
}