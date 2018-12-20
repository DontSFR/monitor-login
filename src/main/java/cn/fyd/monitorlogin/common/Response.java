package cn.fyd.monitorlogin.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cn.fyd.monitorlogin.common.Constant.FAILED;
import static cn.fyd.monitorlogin.common.Constant.SUCCESS;

/**
 * 返回公用类
 * @author fanyidong
 * @date 2018-12-11
 */
public class Response {

    private static Logger logger = LogManager.getLogger(Response.class);

    /**
     * 返回结果success/failed
     */
    private String result;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"result\":\"" + result + "\"," +
                "\"message\":\"" + message + "\"," +
                "\"data\":\"" + data + "\""+
                "}";
    }

    public static String success() {
        Response res = new Response();
        res.setResult(SUCCESS);
        logger.info("日志信息 => return:" + res.toString());
        return res.toString();
    }

    public static String success(Object data) {
        Response res = new Response();
        res.setResult(SUCCESS);
        res.setData(data);
        logger.info("日志信息 => return:" + res.toString());
        return res.toString();
    }

    public static String failed(String reason) {
        Response res = new Response();
        res.setResult(FAILED);
        res.setMessage(reason);
        logger.info("日志信息 => return:" + res.toString());
        return res.toString();
    }
}
