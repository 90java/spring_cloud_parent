package com.nojava.controller;

import com.nojava.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: nojava
 * Date: 2019-11-21
 * Time: 22:39
 */
@RestController
public class Consumer_DeptController {

    private static final String REST_URL_PREFIX = "http://127.0.0.1:8001";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(@RequestBody Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> findAll(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }


}
