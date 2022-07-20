package com.anilduyguc.eagervslazy.second;

import com.anilduyguc.eagervslazy.entity.Course;
import com.anilduyguc.eagervslazy.entity.Instructor;
import com.anilduyguc.eagervslazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
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

            Course course1 = new Course("CSE 101");
            Course course2 = new Course("CSE 212");
            Course course3 = new Course("CSE 221");
            Course course4 = new Course("CSE 351");
            Course course5 = new Course("CSE 344");
            Course course6 = new Course("CSE 348");

            instructor.add(course1);
            instructor.add(course2);
            instructor.add(course3);
            instructor.add(course4);
            instructor.add(course5);
            instructor.add(course6);

            session.save(course1);
            session.save(course2);
            session.save(course3);
            session.save(course4);
            session.save(course5);
            session.save(course6);

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
