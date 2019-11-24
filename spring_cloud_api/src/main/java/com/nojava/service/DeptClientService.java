package com.nojava.service;

import com.nojava.entities.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "SPRINGCLOUD-DEPT")
public interface DeptClientService {

    @RequestMapping(value = "/dept/add",method = RequestMethod.GET)
    boolean add(@RequestBody Dept dept);

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    List<Dept> findAll();

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.POST)
    Dept findDeptByDeptno(@PathVariable Long deptno);

}
