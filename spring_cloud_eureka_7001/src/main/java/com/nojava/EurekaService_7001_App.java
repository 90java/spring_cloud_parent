package com.nojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer         //服务端启动类 接受其他微服务注册进来
public class EurekaService_7001_App
{
    public static void main( String[] args )
    {
        SpringApplication.run(EurekaService_7001_App.class,args);
    }
}
