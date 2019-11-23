package com.nojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient  //表示该服务为eureka客户端
@EnableDiscoveryClient
public class App_8003
{
    public static void main( String[] args )
    {
        SpringApplication.run(App_8003.class,args);
    }

}
