package cn.fyd.monitorlogin.common;

import cn.fyd.common.MonitorException;
import cn.fyd.common.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.fyd.common.Constant.SYSTEM_ERROR;

/**
 * 全局异常处理
 * @author fanyidong
 * @date Created in 2018-12-18
 */
@RestControllerAdvice
public class MonitorAdvice {

    private static Logger logger = LogManager.getLogger(MonitorAdvice.class);

    /**
     * 拦截捕捉异常 Exception.class
     * @param e
     * @return java.lang.String
     */
    @ExceptionHandler(value = Exception.class)
    public String monitorErrorHandler(Exception e) {
        if (e instanceof MonitorException) {
            logger.error(e.getMessage());
            return Response.failed(e.getMessage());
        } else {
            logger.error(e);
            return Response.failed(SYSTEM_ERROR);
        }
    }

}
