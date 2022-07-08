package com.anilduyguc.uni.first;

import com.anilduyguc.uni.entity.Instructor;
import com.anilduyguc.uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            int id = 222;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            System.out.println("Instructor details with id: " + id + " -> " +instructorDetail);
            System.out.println("The associated instructor is: " + instructorDetail.getInstructor());

            session.getTransaction().commit();

            System.out.println("Done!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // handling the session leak issue
            sessionFactory.close();
        }
    }
}
