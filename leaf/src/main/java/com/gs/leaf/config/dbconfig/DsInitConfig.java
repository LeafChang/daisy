package com.gs.leaf.config.dbconfig;

import com.gs.leaf.config.dbconfig.scanner.MapperScanner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@Configuration
//@MapperScan(basePackages = "${spring.datasource.druid.mapperLocation}",sqlSessionTemplateRef = "sqlSessionTemplate")
//@MapperScan(basePackages = "com.gs.leaf.mapper",sqlSessionTemplateRef = "sqlSessionTemplate")
//@MapperScanner(basePackages = "${spring.datasource.druid.mapperLocation}",sqlSessionTemplateRef = "sqlSessionTemplate")
public class DsInitConfig {


    //@Bean("dynamicDataSource")
    //@Primary
    public DynamicDataSource dynamicDataSource(ObjectProvider<List<DataSourceDecorator>> objectProvider) {
        List<DataSourceDecorator> ifAvailable = objectProvider.getIfAvailable();
        if (ifAvailable == null || ifAvailable.isEmpty()){
            throw new IllegalArgumentException("No data source can be found...");
        }
        Optional<DataSourceDecorator> primaryDs = ifAvailable.stream().filter(DataSourceDecorator::getIsPrimary).findAny();
        if (!primaryDs.isPresent()){
            throw new IllegalArgumentException("No primary(default) data source can be found...");
        }
        DynamicDataSource dynamicDataSource = BeanUtils.instantiate(DynamicDataSource.class);
        Map<Object, Object> dataSourceMap = ifAvailable.stream().collect(Collectors.toMap(DataSourceDecorator::getDsName, DataSourceDecorator::getDataSource));
        dynamicDataSource.setDefaultTargetDataSource(primaryDs.get().getDataSource());
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }


    //@Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource, DataSourceConfig dataSourceConfig) throws Exception {
        return dataSourceConfig.createSqlSessionFactory(dynamicDataSource);
    }

    //@Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }


    /**
     * 配置事务管理器
     */
    //@Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource")DynamicDataSource dynamicDataSource) throws Exception {
       return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean("mapperManager")
    public MapperManager mapperManager(ObjectProvider<SqlSessionFactory> objectProvider){
        SqlSessionFactory ifAvailable = objectProvider.getIfAvailable();
        if (ifAvailable != null){
            MapperManager mapperManager  = new MapperManager(ifAvailable.getConfiguration().getMapperRegistry());
            return mapperManager;
        }
        return null;
    }

}
