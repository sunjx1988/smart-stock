package smart.stock.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import smart.stock.shiro.ShiroRealm;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * @Auther: sunjx
 * @Date: 2019/3/7 0007 16:58
 * @Description:
 */
@Configuration
@AutoConfigureBefore({DataBaseConfig.class, DruidConfig.class,MvcConfig.class})
public class ShiroConfig {

    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/*/insert/**", "authc, roles[admin]");
        chainDefinition.addPathDefinition("/*/edit/**", "authc, roles[admin]");
        chainDefinition.addPathDefinition("/*/delete/**", "authc, roles[admin]");
        chainDefinition.addPathDefinition("/*/save/**", "authc, roles[admin]");

        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
        chainDefinition.addPathDefinition("/plugins/**", "anon");

        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
//        final ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//        factoryBean.setSecurityManager(securityManager);
//        final LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
//        factoryBean.setFilterChainDefinitions("/*/insert/** = authc,roles[admin]");
//        factoryBean.setFilterChainDefinitions("/*/save/** = authc,roles[admin]");
//        factoryBean.setFilterChainDefinitions("/*/edit/** = authc,roles[admin]");
//        factoryBean.setFilterChainDefinitions("/*/delete/** = authc,roles[admin]");
//        factoryBean.setSuccessUrl("/");
//        factoryBean.setLoginUrl("/login");
//        factoryBean.setUnauthorizedUrl("/403");
//        factoryBean.setFilters(filters);
//        return factoryBean;
//    }
}
