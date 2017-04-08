package com.hibernate.test.experiments;

import com.hibernate.test.user.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DetachedObjects {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserDetails user = (UserDetails) session.get(UserDetails.class, 1);

        session.getTransaction().commit();
        session.close();

        user.setUsername("Updated username after session close");

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        user.setUsername("Change after update");
        session.getTransaction().commit();
        session.close();

    }

}
