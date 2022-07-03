package com.anilduyguc.jdbc;

import com.anilduyguc.jdbc.entitiy.Employee;
import com.anilduyguc.jdbc.entitiy.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadEmployeeDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            Employee employee  = new Employee("Jost", "Capito", "Williams Mercedes Formula 1 Team");
            System.out.println("Creating a new object");
            session.beginTransaction();
            System.out.println("Saving the employee...");
            System.out.println(employee);
            session.save(employee);
            session.getTransaction().commit();
            System.out.println("Employee added. Generated Id: " + employee.getId() + ". Done!");

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nGetting employee with Id: " + employee.getId());
            Employee getEmployee = session.get(Employee.class, employee.getId());
            System.out.println("Getting completed. " + getEmployee);
            session.getTransaction().commit();
            System.out.println("Done!");



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
