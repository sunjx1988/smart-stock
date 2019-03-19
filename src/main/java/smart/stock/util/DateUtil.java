package smart.stock.util;

import org.thymeleaf.util.DateUtils;

import java.util.Calendar;

/**
 * @Auther: sunjx
 * @Date: 2019/3/19 0019 10:35
 * @Description:
 */
public class DateUtil {

    /**
     * 从2010年至今的全部年份
     */
    public static int[] allYears(){
        int start = 2010;
        int end = DateUtils.createNow().get(Calendar.YEAR);
        int [] all = new int[end - start + 1];

        for(int i = 0; i < all.length; i++){
            all[i] = start + i;
        }

        return all;
    }
}
