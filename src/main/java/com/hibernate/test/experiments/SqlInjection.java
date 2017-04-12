package com.hibernate.test.experiments;

import com.hibernate.test.user.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class SqlInjection {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        int minUserId = 5;
        String username = "Username 10";

        Query query = session.getNamedQuery("UserDetails.byId");
        query.setParameter("minUserId", minUserId);
        query.setParameter("username", username);
        List<UserDetails> users = query.list();


        session.getTransaction().commit();
        session.close();

        for (UserDetails user : users) {
            System.out.println(user.toString());
        }

    }

}
