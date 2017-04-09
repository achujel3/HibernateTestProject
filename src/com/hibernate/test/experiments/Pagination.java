package com.hibernate.test.experiments;

import com.hibernate.test.user.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Pagination {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        for(int i = 1; i <= 10; i++) {
//            UserDetails user = new UserDetails();
//            user.setUsername("Username " + i);
//            session.save(user);
//        }

        List<UserDetails> users = getListOfUsers(session, 2, 2);
        List<String> usersString = getStringListOfUsernames(session, 2, 2);

        session.getTransaction().commit();
        session.close();

        for (UserDetails user : users) {
            System.out.println(user.toString());
        }

        for (String u : usersString) {
            System.out.println(u);
        }

    }

    public static List<UserDetails> getListOfUsers(Session session, int firstResult, int maxResult) {
        Query query = session.createQuery("from USER_DETAILS where userId > 5");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.list();
    }

    public static List<String> getStringListOfUsernames(Session session, int firstResult, int maxResult) {
        Query query = session.createQuery("select username from USER_DETAILS where userId > 5");
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.list();
    }

}
