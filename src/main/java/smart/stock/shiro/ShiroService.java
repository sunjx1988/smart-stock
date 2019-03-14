package smart.stock.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import smart.stock.constant.Constants;
import smart.stock.constant.ResultCode;
import smart.stock.mapper.ShiroMapper;
import smart.stock.mapper.TrusteeMapper;
import smart.stock.service.BaseException;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 17:25
 * @Description:
 */
@Slf4j
@Service
public class ShiroService {

    @Autowired
    private ShiroMapper shiroMapper;

    @Autowired
    private TrusteeMapper trusteeMapper;

    private PasswordService passwordService = new DefaultPasswordService();

    public ShiroUser findUserByPhone(String name){
        return shiroMapper.findUserByPhone(name);
    }

    public Set<String> getRolesByUserId(Long id) {
        return shiroMapper.getRolesByUserId(id);
    }

    public Set<String> getPermsByUserId(Long id) {
        return shiroMapper.getPermsByUserId(id);
    }

    @Transactional
    public Long save(ShiroUser user) {

        //名字不能为空
        if(StringUtils.isEmpty(user.getName())){
            throw BaseException.error("名字不能为空",null);
        }

        //与其他人名字相同
        if(trusteeMapper.countByNameAndId(user) > 0){
            throw BaseException.error("不能与他人名字相同",null);
        }

        //与其他人电话相同
        if(trusteeMapper.countByPhoneAndId(user) > 0){
            throw BaseException.error("不能与他人电话相同",null);
        }

        //提交了密码,说明要修改密码
        if(StringUtils.isNotEmpty(user.getCommitPwd())){
            String encryptPwd = passwordService.encryptPassword(user.getCommitPwd());
            String[] encryptPwdArray = encryptPwd.split("\\$");
            user.setLoginPwd(encryptPwdArray[5]);
            user.setLoginSalt(encryptPwdArray[4]);
        }

        if(null == user.getId()){

            if(null == user.getPrincipal()){
                user.setPrincipal(BigDecimal.ZERO);
            }

            if(null == user.getTotalUnit()){
                user.setTotalUnit(0);
            }

            if(null == user.getStatus()){
                user.setStatus(Constants.TrusteeStatus.Disabled.getKey());
            }
            trusteeMapper.insert(user);
        }else{
            trusteeMapper.updateByPrimaryKeySelective(user);
        }

        //分配角色
        if(!CollectionUtils.isEmpty(user.getRoles())){
            shiroMapper.batchDeleteUserRole(user.getId());
            shiroMapper.batchSaveUserRole(user.getId(), user.getRoles());
        }

        return user.getId();
    }

    public ShiroUser login(String phone, String pwd){

        if (StringUtils.isEmpty(phone)) {
            throw new BaseException(ResultCode.LOGIN.EMPTY_PHONE.getCode(), ResultCode.LOGIN.getTextByCode(ResultCode.LOGIN.EMPTY_PHONE.getCode()), null);
        }

        if (StringUtils.isEmpty(pwd)) {
            throw new BaseException(ResultCode.LOGIN.EMPTY_PWD.getCode(), ResultCode.LOGIN.getTextByCode(ResultCode.LOGIN.EMPTY_PWD.getCode()), null);
        }

        ShiroUser user = shiroMapper.findUserByPhone(phone);
        if(null == user){
            throw new BaseException(ResultCode.LOGIN.NOT_EXISTS.getCode(), ResultCode.LOGIN.getTextByCode(ResultCode.LOGIN.NOT_EXISTS.getCode()), null);
        }

        Subject subject = SecurityUtils.getSubject();

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(phone, pwd);
            subject.login(token);
        } catch (UnknownAccountException e) {
            throw new BaseException(ResultCode.LOGIN.ERROR_PWD.getCode(), ResultCode.LOGIN.getTextByCode(ResultCode.LOGIN.ERROR_PWD.getCode()), null);
        } catch (IncorrectCredentialsException e) {
            throw new BaseException(ResultCode.LOGIN.ERROR_PWD.getCode(), ResultCode.LOGIN.getTextByCode(ResultCode.LOGIN.ERROR_PWD.getCode()), null);
        } catch (RuntimeException e) {
            throw BaseException.error("未知错误,请联系管理员", null);
        }

        return user;
    }

    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setTimeout(1);
        subject.logout();
    }
}
