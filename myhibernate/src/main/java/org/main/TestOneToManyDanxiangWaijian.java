package org.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.onetomanydanxiangwaijian.Department;
import org.onetomanydanxiangwaijian.Employee;

import java.util.HashSet;
import java.util.Set;

public class TestOneToManyDanxiangWaijian {

//    public static void main(String[] args){
//
//        Department department = new Department();
//        department.setName("销售部");
//        Set<Employee> employees = new HashSet<Employee>();
//        Employee employee = new Employee();
//        employee.setName("张三");
//        employees.add(employee);
//        Employee employee1 = new Employee();
//        employee1.setName("李四");
//        employees.add(employee1);
//        department.setEmployees(employees);
//        Session session = HibernateUtil.getThreadLocalSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(department);
//        session.save(employee);
//        session.save(employee1);
//        transaction.commit();
//        session.close();
//
//    }

}
