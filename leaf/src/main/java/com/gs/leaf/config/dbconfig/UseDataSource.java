package com.gs.leaf.config.dbconfig;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface UseDataSource {

    DataSourceType.DataSourceEnum value() default DataSourceType.DataSourceEnum.MASTER_DS;
}
