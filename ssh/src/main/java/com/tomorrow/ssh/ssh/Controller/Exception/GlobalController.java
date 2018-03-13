package com.tomorrow.ssh.ssh.Controller.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局
 *
 * @AUTHOR TCH
 * @CREATE 2018-03-05
 **/
@ControllerAdvice
public class GlobalController {

    //日志
    private static Logger logger= LoggerFactory.getLogger(GlobalController.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        logger.error(e.getMessage());
        mav.addObject("exception", e.getMessage());
        mav.addObject("exceptionUrl", req.getRequestURL());
        mav.setViewName("global/exception");
        return mav;
    }

}
