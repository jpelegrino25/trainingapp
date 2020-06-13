package com.julioluis.trainingrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("qa")
public class QaDatasource {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource managerDataSource=new DriverManagerDataSource();
        managerDataSource.setUrl(environment.getProperty("qa.url"));
        managerDataSource.setUsername(environment.getProperty("qa.username"));
        managerDataSource.setPassword(environment.getProperty("qa.password"));

        return managerDataSource;
    }
}
