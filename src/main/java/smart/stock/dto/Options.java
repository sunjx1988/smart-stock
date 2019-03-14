package smart.stock.dto;

import lombok.Data;

/**
 * @Auther: sunjx
 * @Date: 2019/3/11 0011 12:02
 * @Description:
 */
@Data
public class Options {

    private String value;
    private String text;

    public Options(){}

    public Options(String value, String text) {
        this.value = value;
        this.text = text;
    }
}