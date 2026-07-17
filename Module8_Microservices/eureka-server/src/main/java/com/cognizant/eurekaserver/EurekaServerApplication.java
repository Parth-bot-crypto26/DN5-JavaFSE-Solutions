package com.cognizant.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * "Create and Launch Eureka Discovery Server"  ⭐ MANDATORY
 *
 * Run:  mvn spring-boot:run   (from the eureka-server folder)
 * View: http://localhost:8761 -> "Instances currently registered with Eureka"
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
