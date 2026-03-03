package com.assignment.studentmanagement.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class Student {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
    private Integer adminId;

    public Student() {}

    public Student(Integer id, String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDate enrollmentDate, Integer adminId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.adminId = adminId;
    }

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth, LocalDate enrollmentDate, Integer adminId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.adminId = adminId;
    }

    //Getters
    public Integer getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public Integer getAdminId() {
        return adminId;
    }



}
