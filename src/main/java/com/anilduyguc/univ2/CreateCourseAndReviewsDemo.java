package com.anilduyguc.univ2;

import com.anilduyguc.univ2.entity.Course;
import com.anilduyguc.univ2.entity.Instructor;
import com.anilduyguc.univ2.entity.InstructorDetail;
import com.anilduyguc.univ2.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {
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
            Course course = new Course("CSE 301");
            course.addReview(new Review("Great course loved it..."));
            course.addReview(new Review("Cool course..."));
            course.addReview(new Review("Meh course its hard..."));

            System.out.println("Saving the course\n" + course + "\n" + course.getReviews());
            session.save(course);

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
