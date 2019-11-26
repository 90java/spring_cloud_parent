package com.nojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class App_10001
{
    public static void main( String[] args )
    {
        SpringApplication.run(App_10001.class,args);
    }
}
