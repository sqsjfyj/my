package org.main;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.manytomany.Course;
import org.manytomany.Student;

import java.util.HashSet;
import java.util.Set;

public class TestManyToMany {

    public static void main(String[] args){

        Session session = HibernateUtil.getThreadLocalSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student("张三", "男", 20);
        Student student1 = new Student("李四", "女", 18);
        Course course = new Course("物理");
        Course course1 = new Course("数学");
        Course course2 = new Course("语文");
        session.save(course);
        session.save(course1);
        session.save(course2);
        Set<Course> courses = new HashSet<Course>();
        courses.add(course);
        courses.add(course1);
        courses.add(course2);
        student.setCourses(courses);
        session.save(student);
        session.save(student1);
        transaction.commit();
        session.close();

    }

}
