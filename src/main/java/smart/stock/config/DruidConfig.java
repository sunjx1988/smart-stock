package smart.stock.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @Auther: sunjx
 * @Date: 2019/2/28 0028 16:16
 * @Description:
 */
@Configuration
public class DruidConfig {
    @Bean
    public DataSource dataSource(Environment env){
        DruidDataSource dataSource = DruidDataSourceBuilder
                .create()
                .build(env,"spring.datasource.druid.");
        return dataSource;
    }
}
