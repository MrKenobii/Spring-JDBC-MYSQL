package com.anilduyguc.jdbc;

import com.anilduyguc.jdbc.entitiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {
    private static void printStudents(List<Student> students) {
        System.out.println();
        for (Student student: students) {
            System.out.println(student);
        }
    }
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List studentList = session.createQuery("from Student").list(); // SELECT * from students;
            printStudents(studentList);

            studentList = session.createQuery("from Student s where s.lastName='James'").list(); // SELECT * from student
                                                                                                    // WHERE last_name="James"
            printStudents(studentList);

            studentList = session.
                    createQuery("from Student s " + //SELECT * from students WHERE last_name="James" OR first_name="Charles"
                            "where s.lastName='James' or s.firstName='Charles'")
                            .list();
            printStudents(studentList);

            studentList = session.
                    createQuery("from Student s where s.email like 'lebron%'") // SELECT * from students WHERE
                            .list();                                              // email LIKE 'lebron%'
            printStudents(studentList);

            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
