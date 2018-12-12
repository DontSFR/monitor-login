package cn.fyd.monitorlogin.common;

import static cn.fyd.monitorlogin.common.Constant.FAILED;
import static cn.fyd.monitorlogin.common.Constant.SUCCESS;

/**
 * 返回公用类
 * @author fanyidong
 * @date 2018-12-11
 */
public class Response {

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

    public static Response success() {
        Response res = new Response();
        res.setResult(SUCCESS);
        return res;
    }

    public static Response success(Object data) {
        Response res = new Response();
        res.setResult(SUCCESS);
        res.setData(data);
        return res;
    }

    public static Response failed(String reason) {
        Response res = new Response();
        res.setResult(FAILED);
        res.setMessage(reason);
        return res;
    }
}
