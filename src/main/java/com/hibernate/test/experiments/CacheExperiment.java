package com.hibernate.test.experiments;

import com.hibernate.test.user.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CacheExperiment {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserDetails user = (UserDetails) session.get(UserDetails.class, 1);

        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        UserDetails user2 = (UserDetails) session2.get(UserDetails.class, 1);

        session2.getTransaction().commit();
        session2.close();

    }

}
