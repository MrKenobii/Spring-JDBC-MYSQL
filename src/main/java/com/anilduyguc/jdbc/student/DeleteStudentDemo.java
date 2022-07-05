package com.anilduyguc.jdbc.student;

import com.anilduyguc.jdbc.entitiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteStudentDemo {
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

            /*Student student = session.get(Student.class, studentId);
            System.out.println("Deleting a student with Id: " +studentId);
            session.delete(student);*/

            session.createQuery("delete from Student where id=5").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
