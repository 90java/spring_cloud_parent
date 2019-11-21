package com.nojava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * https://docs.spring.io/spring/docs/current/javadoc-api/
 *
 * User: nojava
 * Date: 2019-11-21
 * Time: 22:36
 */
@Configuration  //==applicationcontext.xml
public class AppConfig {
    /**
     * resttemplate 提供多种便捷访问远程http服务的方法
     * 是一种访问restful服务的模板类，是spring提供的用于访问rest服务的客户端模板工具集
     * @return
     */
    @Bean(value = "restTemplate")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
