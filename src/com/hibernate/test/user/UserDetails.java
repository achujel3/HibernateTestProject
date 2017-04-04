package com.hibernate.test.user;

import javax.persistence.*;

@Entity (name = "USER_DETAILS")
public class UserDetails {

    @Id
    @Column (name = "USER_ID")
    @GeneratedValue
    private int userId;
    @Column (name="USER_NAME")
    private String username;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name="HOME_STREET")),
            @AttributeOverride(name = "pincode", column = @Column(name="HOME_PINCODE")),
            @AttributeOverride(name = "city", column = @Column(name="HOME_CITY")),
            @AttributeOverride(name = "house", column = @Column(name="HOME_HOUSE"))
    })
    private Address homeAddress;
    private Address officeAddress;

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
}
