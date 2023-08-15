package com.seblacko.rag.controllers;

import com.seblacko.rag.entities.Department;
import com.seblacko.rag.util.HibernateUtil;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Path("/api")
public class Test {
   @GET
   @Path("/test")
   public String viewTest(){

       SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
       Session session = sessionFactory.openSession();
       Transaction transaction = session.getTransaction();

       try {
           transaction.begin();
           Department department = new Department();
           department.setName("finance_department");

           session.persist(department);
           transaction.commit();

       }finally {
           if(transaction.isActive()){
               transaction.rollback();
           }
           session.close();
       }


       return "This is test view";
   }
}
