package com.nojava.service;

import com.nojava.entities.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public List<Dept> findAll() {
                return null;
            }

            @Override
            public Dept findDeptByDeptno(Long deptno) {
                System.out.println("****************************");
                return new Dept().setDeptno(deptno).setDname("该id"+deptno+"没有对应的信息，consumer  提供了降级").setDb_source("no data");
            }
        };
    }
}
