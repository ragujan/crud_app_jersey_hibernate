package com.seblacko.rag.util.hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class LoadTable {
    public static List<Object[]> loadAll(String tableName){
        SessionFactory factory = InitialSessionFactory.getSessionFactory();
        Session session = factory.openSession();
        String hql = "from "+tableName;
        Query query =  session.createQuery(hql,Object[].class);
        List<Object[]> resultList = query.getResultList();
        return resultList;
    }
    public static List<Object[]> loadMultiple(String tableName){
        SessionFactory factory = InitialSessionFactory.getSessionFactory();
        Session session = factory.openSession();
        String hql = "from "+tableName;
        Query query =  session.createQuery(hql,Object[].class);
        List<Object[]> resultList = query.getResultList();
        return resultList;
    }
}
