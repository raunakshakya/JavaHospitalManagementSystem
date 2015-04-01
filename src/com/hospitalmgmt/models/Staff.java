/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import com.hospitalmgmt.utils.Gender;
import com.hospitalmgmt.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author raunakshakya
 */
public class Staff extends Employee {

    String department;
    String post;
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

    /**
     * gets the staff instance with the given id
     *
     * @param id
     * @return
     */
    public static Staff findById(Integer id) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Staff staff = (Staff) session.get(Staff.class, id);
            hibernateUtils.commitTransaction();
            return staff;
        } catch (HibernateException hibernateException) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
        return null;
    }

    /**
     * creates a staff instance
     *
     * @param staffDto
     */
    public static void create(HashMap staffDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Staff staff = new Staff();
            bindStaffAttributes(staff, staffDto);
            session.save(staff);
            hibernateUtils.commitTransaction();
        } catch (HibernateException hibernateException) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    /**
     * updates the staff instance matching the given id with the new values
     *
     * @param id
     * @param staffDto
     */
    public static void update(Integer id, HashMap staffDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Staff staff = (Staff) session.get(Staff.class, id);
            bindStaffAttributes(staff, staffDto);
            session.update(staff);
            hibernateUtils.commitTransaction();
        } catch (HibernateException e) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    /**
     * deletes the staff instance matching the given id
     *
     * @param id
     */
    public static void delete(Integer id) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Staff staff = (Staff) session.get(Staff.class, id);
            session.delete(staff);
            hibernateUtils.commitTransaction();
        } catch (HibernateException e) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    private static void bindStaffAttributes(Staff staff, HashMap staffDto) {
        staff.setFullName((String) staffDto.get("fullName"));
        staff.setAddress((String) staffDto.get("address"));
        staff.setContact((String) staffDto.get("contact"));
        String genderValue = (String) staffDto.get("gender");
        staff.setGender(Gender.valueOf(genderValue));
        staff.setDateOfBirth((Date) staffDto.get("dateOfBirthd"));
        //staff.setDateOfJoin((Date) staffDto.get("dateOfJoin"));
        //staff.setShiftFrom((Date) staffDto.get("shiftFrom"));
        //staff.setShiftTo((Date) staffDto.get("shiftTo"));
        //staff.setSpecialization((String) staffDto.get("specialization"));
    }
    
    public static ArrayList<Staff> findAll() {
        ArrayList<Staff> staffList = new ArrayList<>();
        //retrieve all the active staffs

        return staffList;
    }

}
