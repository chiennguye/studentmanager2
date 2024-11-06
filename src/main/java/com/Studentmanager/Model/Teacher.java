package com.Studentmanager.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "teacher_code", unique = true, nullable = false)
    private String teachercode;

    @Column(name = " last_name")
    private String lastname;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_Number")
    private String numberphone;

    @Column(name = "address")
    private String address;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeachercode() {
        return this.teachercode;
    }

    public void setTeachercode(String teachercode) {
        this.teachercode = teachercode;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberphone() {
        return this.numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", teachercode='" + getTeachercode() + "'" +
                ", lastname='" + getLastname() + "'" +
                ", firstname='" + getFirstname() + "'" +
                ", email='" + getEmail() + "'" +
                ", numberphone='" + getNumberphone() + "'" +
                ", address='" + getAddress() + "'" +
                "}";
    }

}
