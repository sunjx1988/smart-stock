package smart.stock.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import smart.stock.dto.Options;
import smart.stock.dto.TrusteeDto;
import smart.stock.entity.Trustee;
import smart.stock.entity.TrusteeExample;

public interface TrusteeMapper {
    long countByExample(TrusteeExample example);

    int deleteByExample(TrusteeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Trustee record);

    int insertSelective(Trustee record);

    List<Trustee> selectByExample(TrusteeExample example);

    Trustee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Trustee record, @Param("example") TrusteeExample example);

    int updateByExample(@Param("record") Trustee record, @Param("example") TrusteeExample example);

    int updateByPrimaryKeySelective(Trustee record);

    int updateByPrimaryKey(Trustee record);

    List<TrusteeDto> list(TrusteeDto param);

    //与其他人名字相同
    int countByNameAndId(Trustee user);

    TrusteeDto selectById(Long id);

    int countByPhoneAndId(Trustee user);

    List<Options> options();

    int trusteeNum(Long fundId);
}
