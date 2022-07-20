package com.anilduyguc.univ3;

import com.anilduyguc.univ3.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMscDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate05.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Student student = session.get(Student.class, 2); // db column id
            System.out.println("\nLoaded student: " + student + "\n Courses: " + student.getCourses());

            Course course1 = new Course("CSE 403");
            Course course2 = new Course("CSE 402");

            course1.addStudent(student);
            course2.addStudent(student);

            System.out.println("Saving the courses!");
            session.save(course1);
            session.save(course2);
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
