package com.pavikumbhar.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Main Spring Boot Application class which does the following
 * <ol>
 * <li>Act as a Eureka client; this behavior is provided by the
 * {@link EnableEurekaClient} annotation. The Eureka server URL is provided by
 * the external configuration provided by the config server.</li>
 * <li>Act as Zuul reverse proxy; this behavior is provided by the
 * {@link EnableZuulProxy} annotation. Annotating the application with
 * {@link EnableZuulProxy} forwards local calls to the appropriate service. By
 * convention, a service with the Eureka ID "users", will receive requests from
 * the proxy located at /users (with the prefix stripped).</li>
 * <li>Enable OAuth2 single sign on (SSO) using the {@link EnableOAuth2Sso}
 * annotation.
 * <ol>
 * <li>If your app has a Spring Cloud Zuul embedded reverse proxy (using
 * {@link EnableZuulProxy}) then you can ask it to forward OAuth2 access tokens
 * downstream to the services it is proxying.</li>
 * <li>If you also add the {@link EnableOAuth2Sso} annotation; then it will (in
 * addition to loggin the user in and grabbing a token) pass the authentication
 * token downstream to the /proxy/* services.</li>
 * <li>If those services are implemented with {@link EnableResourceServer} then
 * they will get a valid token in the correct header.</li>
 * </ol>
 * </li>
 * <li>Note that all these annotations work in conjunction with properties
 * defined in the external configuration files specified by the config server.
 * </li>
 * </ol>
 * 
 * @author pavikumbhar
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	
}
