package com.nojava.controller;

import com.nojava.entities.Dept;
import com.nojava.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private DeptClientService deptService;


    @RequestMapping(value = "/consumer/dept/add",method = RequestMethod.GET)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}", method = RequestMethod.POST)
    public Dept get(@PathVariable Long id){
        return deptService.findDeptByDeptno(id);
    }

    @RequestMapping(value = "/consumer/dept/list",method = RequestMethod.GET)
    public List<Dept> findAll(){
        return deptService.findAll();
    }

}
