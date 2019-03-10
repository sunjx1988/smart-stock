package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.dto.FundDto;
import smart.stock.entity.Fund;
import smart.stock.entity.FundExample;

public interface FundMapper {
    long countByExample(FundExample example);

    int deleteByExample(FundExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Fund record);

    int insertSelective(Fund record);

    List<Fund> selectByExample(FundExample example);

    Fund selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Fund record, @Param("example") FundExample example);

    int updateByExample(@Param("record") Fund record, @Param("example") FundExample example);

    int updateByPrimaryKeySelective(Fund record);

    int updateByPrimaryKey(Fund record);

    FundDto detail(Long id);
}
