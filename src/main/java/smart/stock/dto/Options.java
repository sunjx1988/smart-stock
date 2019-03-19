package smart.stock.dto;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Options options = (Options) o;
        return Objects.equals(value, options.value) &&
                Objects.equals(text, options.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, text);
    }
}
