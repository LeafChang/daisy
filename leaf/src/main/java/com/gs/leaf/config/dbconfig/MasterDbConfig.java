package com.gs.leaf.config.dbconfig;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

//@Configuration
//@ConditionalOnProperty(prefix = "spring.datasource.druid.master",name = "enable",havingValue = "true",matchIfMissing = true)
public class MasterDbConfig {


    //@Bean("masterDataSource")
    //@Primary
    //@ConfigurationProperties(prefix = "spring.datasource.druid.master")
    //@ConditionalOnProperty(prefix = "spring.datasource.druid.master",name = "enable",havingValue = "true",matchIfMissing = false)
    public DataSource masterDataSource() { return DruidDataSourceBuilder.create().build();}

    //@Bean("masterDataSourceDecorator")
    public DataSourceDecorator masterDataSourceDecorator(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceDecorator(Boolean.TRUE, dataSource,DataSourceType.DataSourceEnum.MASTER_DS.getDsName());
    }

}
