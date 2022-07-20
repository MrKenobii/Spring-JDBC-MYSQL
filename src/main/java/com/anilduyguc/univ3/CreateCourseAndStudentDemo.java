package com.anilduyguc.univ3;

import com.anilduyguc.univ3.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {
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
            Course course = new Course("CSE 404");

            System.out.println("Saving the course...\n" + course);
            session.save(course);

            Student student1 = new Student("Sebastian", "Vettel", "vet@abc.com");
            Student student2 = new Student("Michael", "Schumacher", "msc@abc.com");
            Student student3 = new Student("Nico", "Rosberg", "ros@abc.com");

            course.addStudent(student1);
            course.addStudent(student2);
            course.addStudent(student3);

            System.out.println("\nSaving students");

            session.save(student1);
            session.save(student2);
            session.save(student3);
            System.out.println("\nStudents saved: " + course.getStudents());



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
