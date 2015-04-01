/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import com.hospitalmgmt.utils.EmployeeStatus;
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
public class Doctor extends Employee {

    private String specialization;
    private Date dateOfJoin;
    private Date shiftFrom;
    private Date shiftTo;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
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

    /**
     * gets the doctor instance with the given id
     *
     * @param id
     * @return
     */
    public static Doctor findById(Integer id) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Doctor doctor = (Doctor) session.get(Doctor.class, id);
            hibernateUtils.commitTransaction();
            return doctor;
        } catch (HibernateException hibernateException) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
        return null;
    }

    /**
     * creates a doctor instance
     *
     * @param doctorDto
     */
    public static void create(HashMap doctorDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Doctor doctor = new Doctor();
            bindDoctorAttributes(doctor, doctorDto);
            session.save(doctor);
            hibernateUtils.commitTransaction();
        } catch (HibernateException hibernateException) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    /**
     * updates the doctor instance matching the given id with the new values
     *
     * @param id
     * @param doctorDto
     */
    public static void update(Integer id, HashMap doctorDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Doctor doctor = (Doctor) session.get(Doctor.class, id);
            bindDoctorAttributes(doctor, doctorDto);
            session.update(doctor);
            hibernateUtils.commitTransaction();
        } catch (HibernateException e) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    /**
     * deletes the doctor instance matching the given id
     *
     * @param id
     */
    public static void deleteDoctor(Integer id) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Doctor doctor = (Doctor) session.get(Doctor.class, id);
            session.delete(doctor);
            hibernateUtils.commitTransaction();
        } catch (HibernateException e) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    private static void bindDoctorAttributes(Doctor doctor, HashMap doctorDto) {
        doctor.setFullName((String) doctorDto.get("fullName"));
        doctor.setAddress((String) doctorDto.get("address"));
        doctor.setContact((String) doctorDto.get("contact"));
        String status = (String) doctorDto.get("status");
        doctor.setStatus(EmployeeStatus.valueOf(status));
        String gender = (String) doctorDto.get("gender");
        doctor.setGender(Gender.valueOf(gender));
        doctor.setDateOfBirth((Date) doctorDto.get("dateOfBirthd"));
        doctor.setDateOfJoin((Date) doctorDto.get("dateOfJoin"));
        doctor.setShiftFrom((Date) doctorDto.get("shiftFrom"));
        doctor.setShiftTo((Date) doctorDto.get("shiftTo"));
        doctor.setSpecialization((String) doctorDto.get("specialization"));
    }
    
    public static ArrayList<Doctor> findAll() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        //retrieve all the active doctors

        return doctorList;
    }

}
