package com.seblacko.rag.controllers;


import com.seblacko.rag.entities.Book;
import com.seblacko.rag.entities.Employee;
import com.seblacko.rag.entities.EmployeePosition;
import com.seblacko.rag.util.hibernate.InitialSessionFactory;
import com.seblacko.rag.util.hibernate.RowChecker;
import com.seblacko.rag.util.hibernate.UpdateRow;
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
    public String enter() {
//        UpdateRow.update();

        Book book = RowChecker.getEntityByColumn(Book.class,"name","lor");
        book.setName("lor222");
        book.setAuthor("book id 4");
        UpdateRow.update(book);
        Employee employee = new Employee();

//        SessionFactory sessionFactory = InitialSessionFactory.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        Transaction transaction = session.getTransaction();
//
//        try {
//            transaction.begin();
//            session.update(book);
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
