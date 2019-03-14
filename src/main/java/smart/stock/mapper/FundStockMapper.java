package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.dto.FundStockDto;
import smart.stock.entity.FundStock;
import smart.stock.entity.FundStockExample;

public interface FundStockMapper {
    long countByExample(FundStockExample example);

    int deleteByExample(FundStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FundStock record);

    int insertSelective(FundStock record);

    List<FundStock> selectByExample(FundStockExample example);

    FundStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FundStock record, @Param("example") FundStockExample example);

    int updateByExample(@Param("record") FundStock record, @Param("example") FundStockExample example);

    int updateByPrimaryKeySelective(FundStock record);

    int updateByPrimaryKey(FundStock record);

    List<FundStockDto> list(FundStockDto fundStockParam);
}
