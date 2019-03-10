package smart.stock.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
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

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;


    }

}
