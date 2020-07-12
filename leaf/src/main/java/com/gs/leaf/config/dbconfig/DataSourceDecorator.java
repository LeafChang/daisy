package com.gs.leaf.config.dbconfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceDecorator {

    private Boolean isPrimary;

    private DataSource dataSource;

    private String dsName;


}
