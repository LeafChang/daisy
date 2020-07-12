package com.gs.leaf.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.gs.leaf.service.UserService;
import com.gs.leaf.common.ResponseUtil;
import com.gs.leaf.common.ReturnCode;
import com.gs.leaf.common.ReturnModule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //这里做鉴权处理
        System.out.println("这里是鉴权拦截器.....");
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(JSONObject.toJSONString(ResponseUtil.failResponse(ReturnCode.FAIL, ReturnModule.MODULE_COMMON)));
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
