/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import com.hospitalmgmt.utils.BloodGroup;
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
public class Patient extends Employee {

    private BloodGroup bloodgroup;
    private String history;
    private Date dateOfAdmission;
    private String currentProblem;
    private Integer roomNumber;
    private Doctor attendingDoctor;

    public BloodGroup getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(BloodGroup bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public String getCurrentProblem() {
        return currentProblem;
    }

    public void setCurrentProblem(String currentProblem) {
        this.currentProblem = currentProblem;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Doctor getAttendingDoctor() {
        return attendingDoctor;
    }

    public void setAttendingDoctor(Doctor attendingDoctor) {
        this.attendingDoctor = attendingDoctor;
    }

    /**
     * gets the patient instance with the given id
     *
     * @param id
     * @return
     */
    public static Patient findById(Integer id) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Patient patient = (Patient) session.get(Patient.class, id);
            hibernateUtils.commitTransaction();
            return patient;
        } catch (HibernateException hibernateException) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
        return null;
    }

    /**
     * creates a patient instance
     *
     * @param patientDto
     */
    public static void create(HashMap patientDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Patient patient = new Patient();
            bindPatientAttributes(patient, patientDto);
            session.save(patient);
            hibernateUtils.commitTransaction();
        } catch (HibernateException hibernateException) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    /**
     * updates the patient instance matching the given id with the new values
     *
     * @param id
     * @param patientDto
     */
    public static void updatePatient(Integer id, HashMap patientDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Patient patient = (Patient) session.get(Patient.class, id);
            bindPatientAttributes(patient, patientDto);
            session.update(patient);
            hibernateUtils.commitTransaction();
        } catch (HibernateException e) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    /**
     * deletes the patient instance matching the given id
     *
     * @param id
     */
    public static void deletePatient(Integer id) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Patient patient = (Patient) session.get(Patient.class, id);
            session.delete(patient);
            hibernateUtils.commitTransaction();
        } catch (HibernateException e) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    private static void bindPatientAttributes(Patient patient, HashMap patientDto) {
        patient.setFullName((String) patientDto.get("fullName"));
        patient.setAddress((String) patientDto.get("address"));
        patient.setContact((String) patientDto.get("contact"));
        String genderValue = (String) patientDto.get("gender");
        patient.setGender(Gender.valueOf(genderValue));
        patient.setDateOfBirth((Date) patientDto.get("dateOfBirthd"));
        //patient.setDateOfJoin((Date) patientDto.get("dateOfJoin"));
        //patient.setShiftFrom((Date) patientDto.get("shiftFrom"));
        //patient.setShiftTo((Date) patientDto.get("shiftTo"));
        //patient.setSpecialization((String) patientDto.get("specialization"));
    }
    
    public static ArrayList<Patient> findAllByDoctor(Integer id) {
        Doctor doctor = Doctor.findById(id);
        if (doctor == null) {
            throw new RuntimeException("Doctor with given id " + id + " not found!");
        }
        
        ArrayList<Patient> patientList = new ArrayList<>();
        //retrieve all the patients for the given doctor
        
        return patientList;
    }

}
