package com.nojava.util;

import com.nojava.entities.Dept;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 模拟数据库
 * User: nojava
 * Date: 2019-11-21
 * Time: 20:59
 */
public class TestData {

    private static List<Dept> deptList = new ArrayList<>();

    private static Long num = 0L;

    static {
        for(int i=0;i<5;i++){
            Dept dept = new Dept();
            dept.setDeptno(Long.valueOf(num)).setDname("lixiaofeng"+i).setDb_source("source3");
            deptList.add(dept);
            num++;
        }
    }

    public static List<Dept> getDeptList(){
        return deptList;
    }

    public synchronized static boolean add(Dept dept){
        deptList.add(dept);
        return true;
    }

    public synchronized static Dept findDeptByDeptno(Long deptno){
        for(Dept dept:deptList){
            if(dept.getDeptno().equals(deptno)){
                return dept;
            }
        }
        return null;
    }


}
