package com.gs.leaf.config.dbconfig;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
//@ConditionalOnProperty(prefix = "spring.datasource.druid.slave",name = "enable",havingValue = "true",matchIfMissing = false)
public class SlaveDbConfig {

    //@Bean("slaveDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slaveDataSource() { return DruidDataSourceBuilder.create().build();}

    //@Bean("slaveDataSourceDecorator")
    public DataSourceDecorator slaveDataSourceDecorator(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceDecorator(Boolean.FALSE, dataSource,DataSourceType.DataSourceEnum.SLAVE_DS.getDsName());
    }

}
