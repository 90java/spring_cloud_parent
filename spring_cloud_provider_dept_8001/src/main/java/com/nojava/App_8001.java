package com.nojava;

import com.nojava.util.TestData;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 *
 */
@SpringBootApplication
@EnableEurekaClient  //表示该服务为eureka客户端
public class App_8001
{
    public static void main( String[] args )
    {
        SpringApplication.run(App_8001.class,args);
    }

}
