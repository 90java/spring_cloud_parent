package com.nojava.service;

import com.nojava.entities.Dept;
import com.nojava.util.TestData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 操作静态类 相当于操作数据库
 * User: nojava
 * Date: 2019-11-21
 * Time: 20:56
 */
@Service
public class DeptServiceImpl implements DeptClientService {

    @Override
    public boolean add(Dept dept) {
        return TestData.add(dept);
    }

    @Override
    public List<Dept> findAll() {
        return TestData.getDeptList();
    }

    @Override
    public Dept findDeptByDeptno(Long deptno) {
        return TestData.findDeptByDeptno(deptno);
    }
}
