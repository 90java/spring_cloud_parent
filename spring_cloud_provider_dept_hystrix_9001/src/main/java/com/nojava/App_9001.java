package com.nojava;

import com.nojava.util.TestData;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient  //表示该服务为eureka客户端
@EnableDiscoveryClient
@EnableCircuitBreaker  //对熔断器hystrix进行支持
//@EnableHystrix
public class App_9001
{

//    @Bean
//    public ServletRegistrationBean hystrixMetricsStreamServlet() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
//        registration.addUrlMappings("/actuator/hystrix.stream");
//        return registration;
//    }

    public static void main( String[] args )
    {
        SpringApplication.run(App_9001.class,args);
    }

}
