/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import com.hospitalmgmt.utils.EmployeeStatus;
import com.hospitalmgmt.utils.Gender;
import java.util.Date;

/**
 *
 * @author raunakshakya
 */
public class Employee {

    private Integer id;
    private String fullName;
    private String address;
    private Date dateOfBirth;
    private Gender gender;
    private String contact;
    private EmployeeStatus employeeStatus;

    public Employee(String fullName, String address, Date dateOfBirth, Gender gender,
            String contact, EmployeeStatus employeeStatus) {
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contact = contact;
        this.employeeStatus = employeeStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

}
