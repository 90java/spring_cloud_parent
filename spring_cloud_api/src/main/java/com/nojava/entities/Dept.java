package com.nojava.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: nojava
 * Date: 2019-11-21
 * Time: 0:29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)    //链式写法
public class Dept implements Serializable {

    private Long deptno;

    private String dname;

    private String db_source;


    public static void main(String[] args) {
        Dept dept = new Dept();
        ////链式写法
        dept.setDeptno(111L).setDname("lixiaofeng").setDb_source("11");
        System.out.println(dept.toString());
    }

}
