package com.anilduyguc.jdbc.employee;

import com.anilduyguc.jdbc.entitiy.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryEmployeeDemo {
    private static void printEmployees(List<Employee> employees) {
        System.out.println();
        for (Employee employee: employees) {
            System.out.println(employee);
        }
    }
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            List employeeList = session.createQuery("from Employee ").list(); // SELECT * from students;
            printEmployees(employeeList);

            employeeList = session.createQuery("from Employee e where e.lastName='Wolff'").list(); // SELECT * from employee
                                                                                                    // WHERE last_name="Wolff"
            printEmployees(employeeList);

            employeeList = session.
                    createQuery("from Employee e " + //SELECT * from employee WHERE last_name="Binotto" OR first_name="Otmar"
                            "where e.lastName='Binotto' or e.firstName='Otmar'")
                            .list();
            printEmployees(employeeList);

            employeeList = session.
                    createQuery("from Employee e where e.company like 'Scuderia%'") // SELECT * from employee WHERE
                            .list();                                              // company LIKE 'Scuderia%'
            printEmployees(employeeList);

            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
