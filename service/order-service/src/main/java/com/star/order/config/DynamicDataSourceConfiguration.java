package com.star.order.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Caolp
 */
@Configuration
public class DynamicDataSourceConfiguration {


    @Bean(name = DataSourceNames.MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource masterDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = DataSourceNames.SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slaveDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 同样可以理解为一种数据源，和上边两种是一样的
     * @param masterSource master
     * @param slaveSource slave
     * @return dynamicDataSource
     */
    @Bean
    @Primary
    public DynamicDataSource datasource(@Qualifier(DataSourceNames.MASTER) DataSource masterSource, @Qualifier(DataSourceNames.SLAVE) DataSource slaveSource){
        HashMap<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceNames.MASTER, masterSource);
        targetDataSources.put(DataSourceNames.SLAVE, slaveSource);
        System.out.println("DataSources" + targetDataSources);
        return new DynamicDataSource(masterSource, targetDataSources);
    }

}
