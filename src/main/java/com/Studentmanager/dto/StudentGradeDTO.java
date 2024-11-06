package com.Studentmanager.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentGradeDTO {
    @Id
    private Long id;
    private String studentcode;
    private String lastname;
    private String firstname;
    private String classname;
    private String coursename;
    private Float midletest;
    private Float finaltest;
    private Float averageScore;
    private String classification;

    public StudentGradeDTO(String studentcode, String lastname, String firstname, String classname, String coursename,
            Float midletest,
            Float finaltest, Float averageScore, String classification) {
        this.studentcode = studentcode;
        this.lastname = lastname;
        this.firstname = firstname;
        this.classname = classname;
        this.coursename = coursename != null ? coursename : "null";
        this.midletest = (midletest != null) ? midletest : 0.0f; // Giá trị mặc định
        this.finaltest = (finaltest != null) ? finaltest : 0.0f; // Giá trị mặc định
        this.averageScore = (averageScore != null) ? averageScore : 0.0f; // Giá trị mặc định
        this.classification = classification != null ? classification : "No grade"; // Giá trị mặc định
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

    public String getClassname() {
        return this.classname; // Sửa phương thức getter
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getCoursename() {
        return this.coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public Float getMidletest() {
        return this.midletest;
    }

    public void setMidletest(Float midletest) {
        this.midletest = midletest;
    }

    public Float getFinaltest() {
        return this.finaltest;
    }

    public void setFinaltest(Float finaltest) {
        this.finaltest = finaltest;
    }

    public Float getAverageScore() {
        return this.averageScore;
    }

    public void setAverageScore(Float averageScore) {
        this.averageScore = averageScore;
    }

    public String getClassification() {
        return this.classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    // Thêm phương thức tính toán điểm trung bình nếu cần
    public void calculateAverageScore() {
        this.averageScore = (this.midletest + this.finaltest) / 2;
    }

    public String getStudentcode() {
        return this.studentcode;
    }

    public void setStudentcode(String studentcode) {
        this.studentcode = studentcode;
    }

}
