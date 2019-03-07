package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.entity.TrusteeByDay;
import smart.stock.entity.TrusteeByDayExample;

public interface TrusteeByDayMapper {
    long countByExample(TrusteeByDayExample example);

    int deleteByExample(TrusteeByDayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TrusteeByDay record);

    int insertSelective(TrusteeByDay record);

    List<TrusteeByDay> selectByExample(TrusteeByDayExample example);

    TrusteeByDay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TrusteeByDay record, @Param("example") TrusteeByDayExample example);

    int updateByExample(@Param("record") TrusteeByDay record, @Param("example") TrusteeByDayExample example);

    int updateByPrimaryKeySelective(TrusteeByDay record);

    int updateByPrimaryKey(TrusteeByDay record);
}