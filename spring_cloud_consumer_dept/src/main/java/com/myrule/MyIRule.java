package com.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description: ribbon 自定义负载均衡算法
 * User: nojava
 * Date: 2019-11-24
 * Time: 18:46
 */
@Configuration
public class MyIRule {

    @Bean
    public IRule myMyRule(){
//        return new RandomRule();
        return new MySelfRule();
    }


}
