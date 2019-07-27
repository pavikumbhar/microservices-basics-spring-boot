
package com.pavikumbhar.microservices.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * The Main Spring Boot Application class that starts the Eureka discovery
 * server since the application is annotated with {@link EnableEurekaServer}.
 * <br>
 * <br>
 * 
 * Note that all these annotations work in conjunction with properties defined
 * in the external configuration files specified by the config server.
 * 
 * @author pavikumbhar
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

    public static void main(String[] args) {
	SpringApplication.run(DiscoveryServerApplication.class, args);
    }

}
