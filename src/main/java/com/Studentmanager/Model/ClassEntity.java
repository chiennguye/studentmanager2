package com.Studentmanager.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate; // Sử dụng java.time.LocalDate thay vì Joda-Time

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_code", unique = true, nullable = false)
    private String classcode;

    @Column(name = "class_name")
    private String classname;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startdate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClasscode() {
        return this.classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getClassname() {
        return this.classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getStartdate() {
        return this.startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", classcode='" + getClasscode() + "'" +
                ", classname='" + getClassname() + "'" +
                ", teacherId='" + getTeacherId() + "'" +
                ", startdate='" + getStartdate() + "'" +
                "}";
    }

}
