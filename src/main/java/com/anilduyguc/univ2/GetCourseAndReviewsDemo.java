package com.anilduyguc.univ2;

import com.anilduyguc.univ2.entity.Course;
import com.anilduyguc.univ2.entity.Instructor;
import com.anilduyguc.univ2.entity.InstructorDetail;
import com.anilduyguc.univ2.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCourseAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate04.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            int id = 10;
            Course course = session.get(Course.class, id);
            System.out.println(course + "\n" + course.getReviews());

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
