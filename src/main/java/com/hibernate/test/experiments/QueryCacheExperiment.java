package com.hibernate.test.experiments;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class QueryCacheExperiment {

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from USER_DETAILS user where user.userId = 1");
        query.setCacheable(true);
        List users = query.list();

        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        Query query2 = session2.createQuery("from USER_DETAILS user where user.userId = 1");
        query2.setCacheable(true);
        users = query2.list();

        session2.getTransaction().commit();
        session2.close();


    }

}
