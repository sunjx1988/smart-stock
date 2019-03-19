package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.dto.Options;
import smart.stock.dto.StockFinanceDto;
import smart.stock.entity.StockFinance;
import smart.stock.entity.StockFinanceExample;

public interface StockFinanceMapper {
    long countByExample(StockFinanceExample example);

    int deleteByExample(StockFinanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StockFinance record);

    int insertSelective(StockFinance record);

    List<StockFinance> selectByExample(StockFinanceExample example);

    StockFinance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StockFinance record, @Param("example") StockFinanceExample example);

    int updateByExample(@Param("record") StockFinance record, @Param("example") StockFinanceExample example);

    int updateByPrimaryKeySelective(StockFinance record);

    int updateByPrimaryKey(StockFinance record);

    Integer insertBatch(List<StockFinanceDto> list);

    int deleteByCodeAndTypeAndDateType(@Param("code") String code,
                                       @Param("type") String type,
                                       @Param("date") String date);

    List<StockFinanceDto> list(StockFinanceDto param);

    List<Options> dateOptions(String code);
}
