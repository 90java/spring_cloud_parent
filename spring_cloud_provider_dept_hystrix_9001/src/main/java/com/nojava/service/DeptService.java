package com.nojava.service;

import com.nojava.entities.Dept;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: nojava
 * Date: 2019-11-21
 * Time: 20:53
 */
public interface DeptService {

    boolean add(Dept dept);

    List<Dept> findAll();

    Dept findDeptByDeptno(Long deptno);


}
