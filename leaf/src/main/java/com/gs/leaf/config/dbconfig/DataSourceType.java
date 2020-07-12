package com.gs.leaf.config.dbconfig;

public class DataSourceType {

    private static final String MASTER_DATASOURCE ="master";
    private static final String SLAVE_DATASOURCE ="slave";
    private static final String SLAVE_TWO_DATASOURCE ="tidb";


    public enum DataSourceEnum{

        MASTER_DS(MASTER_DATASOURCE),
        SLAVE_DS(SLAVE_DATASOURCE),
        //SLAVE_TWO_DS(MASTER_DATASOURCE),
        ;
        private String dsName;

        DataSourceEnum(String dsName) {
            this.dsName = dsName;
        }

        public String getDsName() {
            return dsName;
        }

    }

}
