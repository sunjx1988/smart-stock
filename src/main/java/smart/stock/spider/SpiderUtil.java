package smart.stock.spider;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: sunjx
 * @Date: 2019/3/18 0018 17:35
 * @Description:
 */
public class SpiderUtil {

    /**
     * 从按模板获取字符串中的参数
     */
    public static List<String> getParamFromTemplate(String str, String pattern){
        String [] splits = pattern.split("%s");

        List<String> result = new ArrayList<>();
        String temp = str;
        String [] tempSplits ;

        if(splits != null && splits.length > 0){
            for(String s : splits){
                temp = temp.replace(s,"|");
            }

            tempSplits = temp.split("\\|");

            if(tempSplits != null && tempSplits.length > 0) {
                for (String s : tempSplits) {
                    if(!StringUtils.isEmpty(s)){
                        result.add(s);
                    }
                }

            }
        }

        return result;
    }
}
