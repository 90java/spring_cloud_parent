package com.nojava.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nojava.entities.Dept;
import com.nojava.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: nojava
 * Date: 2019-11-21
 * Time: 21:34
 */
@RestController
public class DeptController {

    @Autowired
    private DeptClientService deptService;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{deptno}",method = RequestMethod.GET)
    //该注解表示一旦服务方法失败并抛出错误信息后，自动调用该注解内的fallbackMethod 调用类中的制定方法
    @HystrixCommand(fallbackMethod = "hystrix_get")
    public Dept findDeptByDeptno(@PathVariable Long deptno){
        Dept dept = deptService.findDeptByDeptno(deptno);
        if(dept==null){
            throw new RuntimeException("该ID:"+deptno+"不存在");
        }
        return dept;
    }

    public Dept hystrix_get(@PathVariable Long deptno){
        return new Dept().setDeptno(deptno).setDname("该id"+deptno+"没有对应的信息").setDb_source("no data");
    }


    @HystrixCommand
    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> findAll(){
        return deptService.findAll();
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-DEPT");


        return instances;
    }


}
