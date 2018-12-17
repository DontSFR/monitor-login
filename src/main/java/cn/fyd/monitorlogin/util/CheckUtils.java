package cn.fyd.monitorlogin.util;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.fyd.monitorlogin.common.Constant.DATE_FOEMAT_TO_SECOND;

/**
 * 验证工具类
 * @author fanyidong
 * @date Created in 2018-12-14
 */
public class CheckUtils {

    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String rule = "^[A-Za-zd0-9]+([-_.][A-Za-zd0-9]+)*@([A-Za-zd0-9]+[-.])+[A-Za-zd]{2,5}$";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断时间是否过期
     * @param lastMailOutTime 过期时间
     * @return ture：安全码已过期
     *         false：安全码未过期
     * @throws ParseException
     */
    public static boolean isEmailOutTime(String lastMailOutTime) throws ParseException {
        // 转换格式
        Date lastMailOutTimeDate = DateUtils.StringToDate(lastMailOutTime, DATE_FOEMAT_TO_SECOND);
        // 若安全码已过期，返回true
        return lastMailOutTimeDate.before(new Date());
    }
}
