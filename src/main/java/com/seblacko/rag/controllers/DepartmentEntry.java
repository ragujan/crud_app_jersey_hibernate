package com.seblacko.rag.controllers;

import com.seblacko.rag.entities.Book;
import com.seblacko.rag.entities.Department;
import com.seblacko.rag.util.StringInputValidator;
import com.seblacko.rag.util.hibernate.AddRow;
import com.seblacko.rag.util.hibernate.InitialSessionFactory;
import com.seblacko.rag.util.hibernate.RowChecker;
import jakarta.ws.rs.*;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Path("/department_entry")
public class DepartmentEntry {

    @POST
    public String addDepartment(@FormDataParam("departmentName") String departmentName) {
        if(!StringInputValidator.isValid(departmentName)){
           return "invalid input";
        }else{
            System.out.println("Received department name dep2 : " + departmentName);
            Book book = new Book();
            book.setName("lor");
            book.setAuthor("ragjn2");

            SessionFactory sessionFactory = InitialSessionFactory.getSessionFactory();
            Session session = sessionFactory.openSession();

            Transaction transaction = session.getTransaction();

            try {
                transaction.begin();
                session.persist(book);
                transaction.commit();
            }finally {
                if(transaction.isActive()){
                    transaction.rollback();
                }
                session.close();
            }
            if(RowChecker.rowExists("Department","name",departmentName)){
                return "name already exists";
            }else{
                Department department = new Department(departmentName);
                AddRow.addRow(department);
                return "ok";
            }
        }

    }


}
