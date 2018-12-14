package cn.fyd.monitorlogin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * @author fanyidong
 * @date Created in 2018-12-14
 */
public class CheckUtils {

    public static boolean checkEmail(String email) {
        String rule = "^[A-Za-zd0-9]+([-_.][A-Za-zd0-9]+)*@([A-Za-zd0-9]+[-.])+[A-Za-zd]{2,5}$";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
