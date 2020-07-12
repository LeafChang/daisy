package com.gs.leaf.config.dbconfig;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

//@Configuration
public class DataSourceConfig {

    private final MybatisProperties properties;

    private final Interceptor[] interceptors;

    private final ResourceLoader resourceLoader;

    private final DatabaseIdProvider databaseIdProvider;

    private final List<ConfigurationCustomizer> configurationCustomizers;


    public DataSourceConfig(MybatisProperties properties,
                            ObjectProvider<Interceptor[]> interceptors,
                            ResourceLoader resourceLoader,
                            ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                            ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizers) {
        this.properties = properties;
        this.interceptors = interceptors.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = configurationCustomizers.getIfAvailable();
    }


    public SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(getProperties().getConfigLocation())){
            factoryBean.setConfigLocation(getResourceLoader().getResource(getProperties().getConfigLocation()));
        }

        org.apache.ibatis.session.Configuration configuration = getProperties().getConfiguration();
        if (configuration == null && !StringUtils.hasText(getProperties().getConfigLocation())){
            configuration = new org.apache.ibatis.session.Configuration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(getConfigurationCustomizers())){
            for (ConfigurationCustomizer configurationCustomizer: getConfigurationCustomizers() ) {
                configurationCustomizer.customize(configuration);
            }
        }
        factoryBean.setConfiguration(configuration);
        if (getProperties().getConfigurationProperties() != null){
            factoryBean.setConfigurationProperties(getProperties().getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(getInterceptors())){
            factoryBean.setPlugins(getInterceptors());
        }

        if (getDatabaseIdProvider() != null){
            factoryBean.setDatabaseIdProvider(getDatabaseIdProvider());
        }
        if (StringUtils.hasLength(getProperties().getTypeAliasesPackage())){
            factoryBean.setTypeAliasesPackage(getProperties().getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(getProperties().getTypeHandlersPackage())){
            factoryBean.setTypeHandlersPackage(getProperties().getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(getProperties().resolveMapperLocations())){
            factoryBean.setMapperLocations(getProperties().resolveMapperLocations());
        }
        return factoryBean.getObject();
    }




    public MybatisProperties getProperties() {
        return properties;
    }

    public Interceptor[] getInterceptors() {
        return interceptors;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public DatabaseIdProvider getDatabaseIdProvider() {
        return databaseIdProvider;
    }

    public List<ConfigurationCustomizer> getConfigurationCustomizers() {
        return configurationCustomizers;
    }
}
