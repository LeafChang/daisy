package com.gs.leaf.config.dbconfig;

import com.google.common.collect.Maps;
import org.apache.ibatis.binding.MapperRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Map;

public class MapperManager implements InitializingBean,ApplicationContextAware {

    private MapperRegistry mapperRegistry;
    private Map<Class<?>,Object> mapperProxyBeansMap = Maps.newConcurrentMap();
    private ApplicationContext applicationContext;

    public MapperManager(MapperRegistry registry){
        this.mapperRegistry = registry;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<Class<?>> mappers = this.mapperRegistry.getMappers();
        mappers.stream().forEach(p->{
            Object mapperProxy = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext,p);
            if (mapperProxy != null){
                mapperProxyBeansMap.put(p,mapperProxy);
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
