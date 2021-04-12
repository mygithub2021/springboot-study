package com.neo.model;

import java.util.Date;

/**
 * @program: Employee
 * @description: 员工
 * @author: yepengfei
 * @create: 2021/3/22、16:42
 * @Version 1.0
 **/


public class Employee {
    private Integer empId;
    private String empName;
    private Integer empAge;
    private Date birth;

    public Employee(){}

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public Date getBirth(){
        return birth;
    }

    private void setBirth(Date birth){
        this.birth = birth ;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAge=" + empAge +
                '}';
    }
}
