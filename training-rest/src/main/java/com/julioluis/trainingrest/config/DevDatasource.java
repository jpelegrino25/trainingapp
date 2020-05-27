package com.julioluis.trainingrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Configuration
@Profile("dev")
public class DevDatasource {
    @Autowired
   private Environment environment;

    @Bean
  public DataSource getDataSource() {
        DriverManagerDataSource managerDataSource=new DriverManagerDataSource();
        managerDataSource.setUrl(environment.getProperty("dev.url"));
        managerDataSource.setUsername(environment.getProperty("dev.username"));
        managerDataSource.setPassword(environment.getProperty("dev.password"));

      return managerDataSource;
  }
}
