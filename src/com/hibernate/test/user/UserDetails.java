package com.hibernate.test.user;

import com.hibernate.test.vehicle.Vehicle;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "USER_DETAILS")
//@org.hibernate.annotations.Entity(selectBeforeUpdate = true)
@NamedQuery(name = "UserDetails.byId", query = "from USER_DETAILS where userId > :minUserId and username = :username")
@SelectBeforeUpdate
public class UserDetails {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private int userId;
    @Column(name = "USER_NAME")
    private String username;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
            @AttributeOverride(name = "pincode", column = @Column(name = "HOME_PINCODE")),
            @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
            @AttributeOverride(name = "house", column = @Column(name = "HOME_HOUSE"))
    })
    private Address homeAddress;
    private Address officeAddress;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
    @GenericGenerator(name = "sequence", strategy = "sequence")
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @CollectionId(columns = {@Column(name = "ADDRESS_ID")}, generator = "sequence", type = @Type(type = "long"))
    private Collection<Address> listOfAddresses = new ArrayList<>();

    @OneToOne
    @JoinTable(name = "VEHICLE_ID")
    private Vehicle vehicle;

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Vehicle> vehicles = new ArrayList<>();

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Collection<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Collection<Address> getListOfAddresses() {
        return listOfAddresses;
    }

    public void setListOfAddresses(Collection<Address> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return this.getUserId() + ". " + this.getUsername();
    }
}
