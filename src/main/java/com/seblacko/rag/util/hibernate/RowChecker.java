package com.seblacko.rag.util.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RowChecker {
    public static boolean rowExists(String tableName, String columnName, Object columnValue) {
//        table name should be entity name eg: Employee not employee from database table
        SessionFactory sessionFactory = InitialSessionFactory.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        boolean isRowExists = false;
        int rows = 0;
        try {
            transaction.begin();
            String hql = "from "+tableName+" as dep where dep."+columnName+" =:value";
            Query<Object> query = session.createQuery(hql);
            query.setParameter("value", columnValue);
            rows = query.list().size();
            transaction.commit();

        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
        if (rows >= 1) {
            isRowExists = true;
        }else{
            isRowExists = false;
        }
        return isRowExists;
    }
}
