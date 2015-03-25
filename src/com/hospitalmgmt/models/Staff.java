/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import java.util.Date;

/**
 *
 * @author raunakshakya
 */
public class Staff extends Employee {

    String department;
    String post;
    String status;
    Date shiftFrom;
    Date shiftTo;
    Date dateOfJoin;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getShiftFrom() {
        return shiftFrom;
    }

    public void setShiftFrom(Date shiftFrom) {
        this.shiftFrom = shiftFrom;
    }

    public Date getShiftTo() {
        return shiftTo;
    }

    public void setShiftTo(Date shiftTo) {
        this.shiftTo = shiftTo;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

}
