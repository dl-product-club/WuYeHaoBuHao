package com.datanese.wuye.exception;

import com.datanese.wuye.ErrorCode;
import com.datanese.wuye.dto.ErrorInfoDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SessionExpiredException.class)
    @ResponseBody
    public ErrorInfoDTO jsonErrorHandler(HttpServletRequest req, SessionExpiredException e) throws Exception {
        ErrorInfoDTO r = new ErrorInfoDTO();
        r.setMessage(ErrorCode.SESSION_EXPIRED.getDesc());
        r.setErrorCode(ErrorCode.SESSION_EXPIRED.getCode());
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

    @ExceptionHandler(value = UserNotExistException.class)
    @ResponseBody
    public ErrorInfoDTO jsonErrorHandler(HttpServletRequest req, UserNotExistException e) throws Exception {
        ErrorInfoDTO r = new ErrorInfoDTO();
        r.setMessage(ErrorCode.USER_NOT_EXIST.getDesc());
        r.setErrorCode(ErrorCode.USER_NOT_EXIST.getCode());
        r.setUrl(req.getRequestURL().toString());
        return r;
    }

    @ExceptionHandler(value = BadWordException.class)
    @ResponseBody
    public ErrorInfoDTO jsonErrorHandler(HttpServletRequest req, BadWordException e) throws Exception {
        ErrorInfoDTO r = new ErrorInfoDTO();
        r.setMessage(ErrorCode.BAD_WORD.getDesc());
        r.setErrorCode(ErrorCode.BAD_WORD.getCode());
        r.setUrl(req.getRequestURL().toString());
        return r;
    }



}