package com.gs.leaf.config.dbconfig.scanner;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MapperScannerRegister implements ImportBeanDefinitionRegistrar,ResourceLoaderAware,EnvironmentAware{


    private ResourceLoader resourceLoader;
    private Environment environment;


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(MapperScanner.class.getName()));
        ClassPathMapperScanner scanner = new ClassPathMapperScanner(beanDefinitionRegistry);
        if (resourceLoader != null){
            scanner.setResourceLoader(resourceLoader);
        }

        Class<? extends Annotation> annotationClass = attributes.getClass("annotationClass");
        if(!Annotation.class.equals(annotationClass)){
            scanner.setAnnotationClass(annotationClass);
        }

        Class<?> markerInterface = attributes.getClass("markerInterface");
        if(!Class.class.equals(markerInterface)){
            scanner.setMarkerInterface(markerInterface);
        }

        Class<? extends BeanNameGenerator> generatorClass = attributes.getClass("nameGenerator");
        if(!BeanNameGenerator.class.equals(generatorClass)){
            scanner.setBeanNameGenerator(BeanUtils.instantiate(generatorClass));
        }

        //scanner.setSqlSessionFactoryBeanName(attributes.getString("setSqlSessionFactoryRef"));
        scanner.setSqlSessionTemplateBeanName(attributes.getString("setSqlSessionTemplateRef"));

        List<String> basePackages = Lists.newArrayList();

        for (String pkg: attributes.getStringArray("basePackages")) {
            if (StringUtils.hasText(pkg)){
                basePackages.add(parsePlaceHolder(pkg));
            }
        }

        for (Class<?> clazz: attributes.getClassArray("basePackageClasses")) {
            basePackages.add(ClassUtils.getPackageName(clazz));
        }

        basePackages = basePackages.stream().filter(org.apache.commons.lang.StringUtils::isNotEmpty).collect(Collectors.toList());
        if (!basePackages.isEmpty()){
            scanner.registerFilters();
            scanner.doScan(StringUtils.toStringArray(basePackages));
        }
    }


    private String parsePlaceHolder(String pro){
        if (pro != null && pro.contains(PropertySourcesPlaceholderConfigurer.DEFAULT_PLACEHOLDER_PREFIX)){
            String value = environment.getProperty(pro.substring(2,pro.length()-1));
            log.warn("parsePlaceHolder --> value : {}",value);
            return value;
        }
        return pro;
    }

}
