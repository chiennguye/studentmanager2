package com.Studentmanager.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "courses_id")
    private Course course;

    @Column(name = "midle_test")
    private float midletest;

    @Column(name = "final_test")
    private float finaltest;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getMidletest() {
        return this.midletest;
    }

    public void setMidletest(float midletest) {
        this.midletest = midletest;
    }

    public float getFinaltest() {
        return this.finaltest;
    }

    public void setFinaltest(float finaltest) {
        this.finaltest = finaltest;
    }

}
