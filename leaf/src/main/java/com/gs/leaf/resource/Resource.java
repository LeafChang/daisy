package com.gs.leaf.resource;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;

@Configuration
@ConfigurationProperties(prefix = "com.gs.leaf")
@PropertySource(value = "classpath:resource.properties")
public class Resource {

    private String name;
    private String domain;
    private String lang;
    private String you;
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getYou() {
        return you;
    }

    public void setYou(String you) {
        this.you = you;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
