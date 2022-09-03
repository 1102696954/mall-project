package com.lars.store.controller;

import com.lars.store.service.ex.InsertException;
import com.lars.store.service.ex.ServiceException;
import com.lars.store.service.ex.UsernameDuplicateException;
import com.lars.store.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 拦截器基类
 */
public class BaseController {

    public static final int OK = 200;

    /**
     * @ExceptionHandler用于统一处理方法抛出的异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleException(Throwable e) {
        Result<Void> result = new Result<Void>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        }
        return result;
    }

}
