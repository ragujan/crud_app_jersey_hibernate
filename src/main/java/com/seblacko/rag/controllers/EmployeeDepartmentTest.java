package com.seblacko.rag.controllers;

import com.seblacko.rag.entities.Department;
import com.seblacko.rag.entities.Employee;
import com.seblacko.rag.entities.EmployeePosition;
import com.seblacko.rag.util.hibernate.InitialSessionFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/")
public class EmployeeDepartmentTest {
    @GET
    @Path("emp_dep_test")
    public String test(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(format);
                SessionFactory sessionFactory = InitialSessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.getTransaction();
        Department department = new Department();
        department.setId(1);
        EmployeePosition employeePosition = new EmployeePosition();
        employeePosition.setId(6);
        Employee employee = new Employee();
        employee.setFirstName("f_name");
        employee.setLastName("l_name");
        employee.setEmployeePosition(employeePosition);
        employee.setHiredDate(formattedDateTime);
        employee.setDepartment(department);
        try {
            transaction.begin();
            session.persist(employee);
            transaction.commit();
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }

        }
        return "Ok";
    }
}
