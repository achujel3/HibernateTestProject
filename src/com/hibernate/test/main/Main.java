package com.hibernate.test.main;

import com.hibernate.test.user.Address;
import com.hibernate.test.user.UserDetails;
import com.hibernate.test.vehicle.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {


    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("First vehicle");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setVehicleName("Second vehicle");

        UserDetails user1 = new UserDetails();
        user1.setUsername("First user");
        Address address = new Address();
        address.setCity("New york");
        address.setStreet("6th ave");
        address.setPincode("69584");
        address.setHouse("12");

        user1.setHomeAddress(address);
        user1.setVehicle(vehicle);

        user1.getVehicles().add(vehicle);
        user1.getVehicles().add(vehicle2);

        UserDetails user2 = new UserDetails();
        user2.setUsername("Second user");
        Address address2 = new Address();
        address2.setCity("LA");
        address2.setStreet("South central");
        address2.setPincode("69584");
        address2.setHouse("15");
        user2.setOfficeAddress(address2);

        user1.getListOfAddresses().add(address);
        user1.getListOfAddresses().add(address2);

        vehicle.getUsers().add(user1);
        vehicle2.getUsers().add(user1);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user1);
        session.save(user2);
        session.save(vehicle);
        session.save(vehicle2);
        session.getTransaction().commit();
        session.close();

        user1 = null;

        session = sessionFactory.openSession();
        session.beginTransaction();
        // This way we can get objects by their PK
        user1 = (UserDetails) session.get(UserDetails.class, 1);
        session.close();
        System.out.println(user1.getListOfAddresses().size());



    }

}