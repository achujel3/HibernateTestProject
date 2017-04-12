package com.hibernate.test.experiments;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HqlTest {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        for(int i = 1; i <= 10; i++) {
//            UserDetails user = new UserDetails();
//            user.setUsername("Username " + i);
//            session.save(user);
//        }

        Query query = session.createQuery("from USER_DETAILS where userId > 5");
        List users = query.list();


        session.getTransaction().commit();
        session.close();

        System.out.println("The size of users: " + users.size());

    }

}
