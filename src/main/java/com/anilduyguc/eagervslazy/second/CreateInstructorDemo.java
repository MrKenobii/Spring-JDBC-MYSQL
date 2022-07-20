package com.anilduyguc.eagervslazy.second;

import com.anilduyguc.eagervslazy.entity.Course;
import com.anilduyguc.eagervslazy.entity.Instructor;
import com.anilduyguc.eagervslazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate03.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
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
            session.close();
            sessionFactory.close();
        }
    }
}
