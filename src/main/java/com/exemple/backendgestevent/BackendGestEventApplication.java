package com.exemple.backendgestevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BackendGestEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendGestEventApplication.class, args);
    }

}
