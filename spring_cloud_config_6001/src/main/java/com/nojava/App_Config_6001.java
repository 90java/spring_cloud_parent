package com.nojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableConfigServer
public class App_Config_6001
{
    public static void main( String[] args )
    {
        SpringApplication.run(App_Config_6001.class,args);
    }
}
