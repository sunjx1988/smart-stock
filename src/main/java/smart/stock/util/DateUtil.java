package smart.stock.util;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: sunjx
 * @Date: 2019/3/19 0019 10:35
 * @Description:
 */
public class DateUtil {

    /**
     * 从2010年至今的全部年份
     */
    public static int[] allYears(int start){
        int end = Calendar.getInstance().get(Calendar.YEAR);
        int [] all = new int[end - start + 1];

        for(int i = 0; i < all.length; i++){
            all[i] = start + i;
        }

        return all;
    }

    /**
     * 日期相差周年数
     */
    public static int floorDiffYear(Date date1, Date date2){
        for (int i = 0; ; i++) {
            if(DateUtils.addYears(date2, i + 1).compareTo(date1) > 0){
                return i;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String parttern = "yyyy-MM-dd HH:mm:ss";
        Date date1 = DateUtils.parseDate("2019-04-04 00:00:00", parttern);
        Date date2 = DateUtils.parseDate("2018-03-04 00:00:00", parttern);
        System.out.println(floorDiffYear(date1, date2));
    }
}
