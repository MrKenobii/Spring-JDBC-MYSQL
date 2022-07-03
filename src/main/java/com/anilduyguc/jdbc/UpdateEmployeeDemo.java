package com.anilduyguc.jdbc;

import com.anilduyguc.jdbc.entitiy.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class UpdateEmployeeDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session;
        try {
            Long employeeId = Long.valueOf(3);
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting employee with Id: " + employeeId);
            Employee employee = session.get(Employee.class, employeeId);
            System.out.println("Updating employee");
            employee.setFirstName("Toto");
            System.out.println(employeeId);
            session.getTransaction().commit();

/*            System.out.println("Updating firstName all employees");
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("update Employee set firstName='Tifosi'").executeUpdate();
            session.getTransaction().commit();*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
