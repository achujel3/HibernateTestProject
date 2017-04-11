package com.hibernate.test.experiments;

import com.hibernate.test.user.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaApi {


    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<UserDetails> criteria = builder.createQuery(UserDetails.class);

        Root<UserDetails> root = criteria.from(UserDetails.class);

        criteria.where(builder.or(builder.between(root.get("userId"), 1, 3), builder.between(root.get("userId"), 7, 10)));

        Query query = session.createQuery(criteria);

        List<UserDetails> users = query.getResultList();

        session.getTransaction().commit();
        session.close();

        for (UserDetails u : users)
            System.out.println(u.toString());
    }


}
