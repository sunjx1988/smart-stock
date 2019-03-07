package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.entity.TrusteeTrade;
import smart.stock.entity.TrusteeTradeExample;

public interface TrusteeTradeMapper {
    long countByExample(TrusteeTradeExample example);

    int deleteByExample(TrusteeTradeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TrusteeTrade record);

    int insertSelective(TrusteeTrade record);

    List<TrusteeTrade> selectByExample(TrusteeTradeExample example);

    TrusteeTrade selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TrusteeTrade record, @Param("example") TrusteeTradeExample example);

    int updateByExample(@Param("record") TrusteeTrade record, @Param("example") TrusteeTradeExample example);

    int updateByPrimaryKeySelective(TrusteeTrade record);

    int updateByPrimaryKey(TrusteeTrade record);
}