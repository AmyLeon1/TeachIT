package com.amyleon.teachIT.model;


import org.hibernate.annotations.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

//serializable to make it easier to convert class to different types of String
//allows it to connect to database we configure
@Entity
@Table
public class Teacher implements Serializable, UserDetails {
    @Id //makes it the primary key
    @SequenceGenerator(
            name ="teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enabled;


    public Teacher(String firstName,
                   String lastName, String userName, String email, String password,
                   AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //default constructor
    public Teacher(){

    }

    public Teacher(long id, String firstName, String lastName, String userName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(String toString) {
    }


}

