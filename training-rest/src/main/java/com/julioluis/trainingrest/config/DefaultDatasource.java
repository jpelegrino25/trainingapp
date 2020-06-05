package com.julioluis.trainingrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("default")
public class DefaultDatasource {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource managerDataSource=new DriverManagerDataSource();
        managerDataSource.setUrl(environment.getProperty("default.url"));
        managerDataSource.setUsername(environment.getProperty("default.username"));
        managerDataSource.setPassword(environment.getProperty("default.password"));

        return managerDataSource;
    }
}
