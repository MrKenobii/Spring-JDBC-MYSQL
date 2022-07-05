package com.anilduyguc.uni;

import com.anilduyguc.uni.entity.Instructor;
import com.anilduyguc.uni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Instructor instructor = new Instructor("Kobe", "Bryant", "kobebryant@nba.com");
            InstructorDetail instructorDetail = new InstructorDetail("http://www.kobebryant.com/youtube", "Basketball");

            instructor.setInstructorDetail(instructorDetail);
            System.out.println("Saving the instructor: " + instructor);
            session.save(instructor);

            session.getTransaction().commit();

            System.out.println("Done!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
