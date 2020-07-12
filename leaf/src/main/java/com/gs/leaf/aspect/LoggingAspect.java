package com.gs.leaf.aspect;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(public * com.gs.leaf.service.*Service.*(..))")
    public void logCodition(){}

    //@Before("logCodition()")
    public void before(){
        System.out.println("======before======");

    }

    @Around("logCodition()")
    public Object around(ProceedingJoinPoint joinPoint) throws  Throwable{
        System.out.println(" ===before==== @Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = joinPoint.getArgs();
        System.out.println("request params : " + JSONObject.toJSONString(args));
        Object retVal = joinPoint.proceed();
        System.out.println(" ===after====  @Around：执行目标方法之后...");
        System.out.println("retVal : " + JSONObject.toJSONString(retVal));
        return retVal;
    }

    @AfterReturning(pointcut="execution(public * com.gs.leaf.service.*.*(..))",
            returning="returnValue")
    public void log(JoinPoint point, Object returnValue) {
        System.out.println("@AfterReturning：模拟日志记录功能...");
        System.out.println("@AfterReturning：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" +
                Arrays.toString(point.getArgs()));
        System.out.println("@AfterReturning：返回值为：" + returnValue);
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
    }


    @After("execution(public * com.gs.leaf.service.*.*(..))")
    public void releaseResource(JoinPoint point) {
        System.out.println("@After：模拟释放资源...");
        System.out.println("@After：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
    }
}
