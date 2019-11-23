package com.nojava.controller;

import com.nojava.Dept;
import com.nojava.service.DeptService;
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
    private DeptService deptService;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{deptno}",method = RequestMethod.GET)
    public Dept findDeptByDeptno(@PathVariable Long deptno){
        return deptService.findDeptByDeptno(deptno);
    }

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
