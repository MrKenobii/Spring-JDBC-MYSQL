package com.anilduyguc.jdbc;

import com.anilduyguc.jdbc.entitiy.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateEmployeeDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            Employee employee = new Employee("Jost", "Capito", "Williams Mercedes Formula 1 Team");
            System.out.println("Creating a new object");
            session.beginTransaction();
            System.out.println("Saving the employee...");
            session.save(employee);
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
