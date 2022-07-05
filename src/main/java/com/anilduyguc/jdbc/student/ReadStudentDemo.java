package com.anilduyguc.jdbc.student;

import com.anilduyguc.jdbc.entitiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            Student student = new Student("Charles", "Leclerc", "charleclerc@gmail.com");
            System.out.println("Creating a new object");
            session.beginTransaction();
            System.out.println("Saving the student...");
            System.out.println(student);
            session.save(student);
            session.getTransaction().commit();
            System.out.println("Student added. Generated Id: " + student.getId() + ". Done!");

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nGetting student with Id: " + student.getId());
            Student getStudent = session.get(Student.class, student.getId());
            System.out.println("Getting completed. " + getStudent);
            session.getTransaction().commit();
            System.out.println("Done!");



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
