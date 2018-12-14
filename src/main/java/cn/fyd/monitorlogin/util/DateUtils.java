package cn.fyd.monitorlogin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式转换工具类
 * @author fanyidong
 * @date Created in 2018-12-14
 */
public class DateUtils {

    /**
     * Date类型转换为String类型
     * @param date
     * @param format
     * @return
     */
    public static String DateToString(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
