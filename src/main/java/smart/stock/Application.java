package smart.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: sunjx
 * @Date: 2019/2/27 0027 17:19
 * @Description:
 */
@SpringBootApplication
@MapperScan("smart.stock.mapper")
public class Application {
    public static void main(String[] args) {

    }
}
