package org.ccf.ccfpedia.cms.exception;

import org.ccf.ccfpedia.cms.bean.resp.RestResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory
            .getLogger(ExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResp handle(Exception e){
        logger.error("internal error", e);
        return new RestResp(500, "服务器内部错误");
    }

}