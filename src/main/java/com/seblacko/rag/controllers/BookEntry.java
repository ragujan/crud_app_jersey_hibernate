package com.seblacko.rag.controllers;


import com.seblacko.rag.entities.Book;
import com.seblacko.rag.entities.EmployeePosition;
import com.seblacko.rag.util.hibernate.InitialSessionFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Path("book-entry")
public class BookEntry {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String enter(){

//        Book book = new Book();
//        book.setName("lor");
//        book.setAuthor("ragjn2");
//        EmployeePosition employeePosition = new EmployeePosition();
//        employeePosition.setPositionName("bro rag");
//
//        SessionFactory sessionFactory = InitialSessionFactory.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        Transaction transaction = session.getTransaction();
//
//        try {
//            transaction.begin();
//            session.persist(book);
//            session.persist(employeePosition);
//            transaction.commit();
//        }finally {
//            if(transaction.isActive()){
//                transaction.rollback();
//            }
//
//        }
        return "Ok";
    }

}
