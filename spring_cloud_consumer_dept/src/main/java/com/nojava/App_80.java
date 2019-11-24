package com.nojava;

import com.myrule.MyIRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "springcloud-dept",configuration = MyIRule.class) //针对哪个微服务负载均衡  configuration为自定义负载均衡算法
public class App_80
{
    public static void main( String[] args )
    {
        SpringApplication.run(App_80.class,args);
    }
}
