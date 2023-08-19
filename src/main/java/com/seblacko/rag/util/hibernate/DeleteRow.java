package com.seblacko.rag.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeleteRow {
    public static void delete(Object object) {
        SessionFactory sessionFactory = null;
        Session session = null;
        try {
            sessionFactory = InitialSessionFactory.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
}
