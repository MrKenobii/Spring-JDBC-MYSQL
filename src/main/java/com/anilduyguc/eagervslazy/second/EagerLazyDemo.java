package com.anilduyguc.eagervslazy.second;

import com.anilduyguc.eagervslazy.entity.Course;
import com.anilduyguc.eagervslazy.entity.Instructor;
import com.anilduyguc.eagervslazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {
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
            int id =1;
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println("Instructor: " + instructor);
            System.out.println("Courses:" + instructor.getCourses());

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
