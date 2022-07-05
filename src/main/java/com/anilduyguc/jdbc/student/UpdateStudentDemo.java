package com.anilduyguc.jdbc.student;

import com.anilduyguc.jdbc.entitiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session;
        try {
            Long studentId = Long.valueOf(3);
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting student with Id: " + studentId);
            Student student = session.get(Student.class, studentId);
            System.out.println("Updating student");
            student.setFirstName("Arthur");
            System.out.println(student);
            session.getTransaction().commit();

            System.out.println("Updating email all students");

            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Student set email='foo.gmail.com'").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
