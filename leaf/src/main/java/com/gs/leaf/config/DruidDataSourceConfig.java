package com.gs.leaf.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.type.AnnotationMetadata;

//@Configuration
//@Import(DataSourceConfig.class)
public class DruidDataSourceConfig implements ImportBeanDefinitionRegistrar,EnvironmentAware {


    private ConfigurableEnvironment environment;

    @Autowired
    DataSourceConfig dsConfig;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        //DataSourceConfig dataSourceConfig = parseDataSourceConfig();

        //BeanDefinitionBuilder

    }

    /**
     * 解析数据源配置
     * @return
     */
    /*private DataSourceConfig parseDataSourceConfig() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(dsConfig.getUrl());
        ds.setUsername(dsConfig.getUsername());
        ds.setPassword(dsConfig.getPassword());
        ds.
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.
    }*/
}
