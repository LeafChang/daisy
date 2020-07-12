package com.gs.leaf.config.dbconfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.stream.Stream;

//@Component
@Slf4j
@Order(-1)
//@Aspect
public class DataSourceAspect {


    @Pointcut("(execution(* com.gs.leaf.mapper*..*(..)))" +
            "||@annotation(com.gs.leaf.config.dbconfig.UseDataSource)" +
            "||@annotation(org.apache.ibatis.annotations.Mapper)")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        //log.info("选择数据源---{}",dataSource.getDsName())
        DataSourceType.DataSourceEnum dataSourceEnum =  DataSourceType.DataSourceEnum.MASTER_DS;

        Class<?> aClass = joinPoint.getTarget().getClass();
        Class<?>[] classes = aClass.getClasses();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature =  (MethodSignature)signature;
        Method method = methodSignature.getMethod();

        try {
            if (method.isAnnotationPresent(UseDataSource.class)){
                UseDataSource useDataSource = method.getAnnotation(UseDataSource.class);
                dataSourceEnum = useDataSource.value();
            }else{
                if (isAnnotationPresent(UseDataSource.class,classes)){
                    UseDataSource useDataSource = method.getAnnotation(UseDataSource.class);
                    dataSourceEnum = useDataSource.value();
                }
            }
            DataSourceContextHolder.setDBKey(dataSourceEnum.getDsName());
        } catch (Exception e) {
            log.error("DataSourceAspect  DataSourceContextHolder parse datasource type error...,{}",e);
        }
    }

    private boolean isAnnotationPresent(Class<? extends Annotation> useDataSourceClass, Class<?>[] classes) {
        if (classes == null) return false;
        return Stream.of(classes).anyMatch(aClass -> aClass.isAnnotationPresent(useDataSourceClass));
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clearDBKey();
    }

}
