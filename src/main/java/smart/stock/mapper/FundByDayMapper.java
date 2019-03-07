package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.entity.FundByDay;
import smart.stock.entity.FundByDayExample;

public interface FundByDayMapper {
    long countByExample(FundByDayExample example);

    int deleteByExample(FundByDayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FundByDay record);

    int insertSelective(FundByDay record);

    List<FundByDay> selectByExample(FundByDayExample example);

    FundByDay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FundByDay record, @Param("example") FundByDayExample example);

    int updateByExample(@Param("record") FundByDay record, @Param("example") FundByDayExample example);

    int updateByPrimaryKeySelective(FundByDay record);

    int updateByPrimaryKey(FundByDay record);
}