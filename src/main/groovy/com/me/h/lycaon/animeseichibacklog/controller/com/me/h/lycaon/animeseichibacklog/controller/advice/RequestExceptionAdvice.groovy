package com.me.h.lycaon.animeseichibacklog.controller.com.me.h.lycaon.animeseichibacklog.controller.advice

import com.me.h.lycaon.animeseichibacklog.controller.exception.RequestRecoverableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

/**
 * Created by lycaon_h on 2014/03/15.
 */
@ControllerAdvice
public class RequestExceptionAdvice {

    @ExceptionHandler(value = [RequestRecoverableException.class])
    public ModelAndView handleException(RequestRecoverableException e) {
        return null;
    }

}
