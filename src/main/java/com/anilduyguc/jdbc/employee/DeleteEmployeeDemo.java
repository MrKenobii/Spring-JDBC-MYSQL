package com.anilduyguc.jdbc.employee;

import com.anilduyguc.jdbc.entitiy.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteEmployeeDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session;
        try {
            Long employeeId = Long.valueOf(10);
            session = factory.getCurrentSession();
            session.beginTransaction();

            /*Employee employee = session.get(Employee.class, employeeId);
            System.out.println("Deleting a employee with Id: " +employeeId);
            session.delete(employee);*/

            session.createQuery("delete from Employee where id=10").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
