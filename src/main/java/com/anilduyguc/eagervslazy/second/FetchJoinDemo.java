package com.anilduyguc.eagervslazy.second;

import com.anilduyguc.eagervslazy.entity.Course;
import com.anilduyguc.eagervslazy.entity.Instructor;
import com.anilduyguc.eagervslazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {
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
            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                    "JOIN FETCH i.courses " +
                    "WHERE i.id=:theInstructorId", //this id and
                    Instructor.class);
            //set parameter on query
            query.setParameter("theInstructorId", id); // this id should match
            Instructor instructor = query.getSingleResult();
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
