package com.diboot.web.controller;

import com.diboot.framework.async.AsyncLogger;
import com.diboot.framework.config.Status;
import com.diboot.framework.exception.PermissionException;
import com.diboot.framework.model.JsonResult;
import com.diboot.framework.utils.BaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理类
 * @author Mazc@dibo.ltd
 * @version 2018/4/11
 *
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @Autowired
    AsyncLogger asyncLogger;

    /***
     * 捕获异常的统一处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String handleException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        String errorInfo = BaseHelper.buildExceptionInfo(request);
        if (this.asyncLogger != null) {
            this.asyncLogger.saveErrorLog(null, errorInfo);
        }
        if (BaseHelper.isAjaxRequest(request)) {
            Integer statusCode = (Integer)request.getAttribute("statusCode");
            boolean noPermission = ex instanceof PermissionException || statusCode != null && statusCode.equals(403);
            JsonResult errorResult = new JsonResult(noPermission ? Status.FAIL_NO_PERMISSION : Status.FAIL_EXCEPTION, new String[]{"处理异常: " + ex.getMessage()});
            BaseHelper.responseJson(response, errorResult);
            logger.debug("Ajax请求处理异常: " + errorResult.getMsg());
            return null;
        }
        else {
            return "common/error";
        }
    }

}
