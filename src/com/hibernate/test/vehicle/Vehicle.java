package com.hibernate.test.vehicle;

import com.hibernate.test.user.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private int vehicleId;
    private String vehicleName;
    @ManyToMany
    private Collection<UserDetails> users = new ArrayList<>();

    public Collection<UserDetails> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDetails> users) {
        this.users = users;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
