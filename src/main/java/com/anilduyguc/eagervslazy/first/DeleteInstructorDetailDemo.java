package com.anilduyguc.eagervslazy.first;

import com.anilduyguc.eagervslazy.entity.Instructor;
import com.anilduyguc.eagervslazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            int id = 3;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

            System.out.println("Instructor details with id: " + id + " -> " +instructorDetail);
            System.out.println("The associated instructor is: " + instructorDetail.getInstructor());
            System.out.println("Deleting instructor detail: " + instructorDetail);

            instructorDetail.getInstructor().setInstructorDetail(null); //break bidirectional link, remove the associated object reference
            session.delete(instructorDetail);

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
