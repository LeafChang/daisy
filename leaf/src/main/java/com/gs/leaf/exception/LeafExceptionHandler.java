package com.gs.leaf.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class LeafExceptionHandler {

    public static final String LEAF_ERROR_VIEW="error";

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Exception e) throws Exception{
        e.printStackTrace();
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception",e);
        mv.addObject("url",request.getRequestURL());
        mv.setViewName(LEAF_ERROR_VIEW);
        return mv;
    }


}
