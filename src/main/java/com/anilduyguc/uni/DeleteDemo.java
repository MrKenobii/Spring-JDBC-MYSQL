package com.anilduyguc.uni;

import com.anilduyguc.uni.entity.Instructor;
import com.anilduyguc.uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);

            System.out.println("Found instructor: " +instructor);
            if (instructor != null) session.delete(instructor); // will also delete associated "details" object (CascadeType.ALL)

            session.getTransaction().commit();

            System.out.println("Done!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
