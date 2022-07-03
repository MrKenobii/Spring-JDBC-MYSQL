package com.anilduyguc.jdbc;

import com.anilduyguc.jdbc.entitiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            Student student = new Student("Melis", "Gorucu", "melisgorucu@gmail.com");
            System.out.println("Creating a new object");
            session.beginTransaction();
            System.out.println("Saving the student...");
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
