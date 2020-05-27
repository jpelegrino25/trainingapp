package com.julioluis.trainingrest;

import com.julioluis.trainingrest.config.DevDatasource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class TrainingRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingRestApplication.class, args);
	}

}
