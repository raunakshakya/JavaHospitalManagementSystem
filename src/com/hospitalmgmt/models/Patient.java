/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import static com.hospitalmgmt.system.HospitalMgmtSystem.logger;
import com.hospitalmgmt.utils.BloodGroup;
import com.hospitalmgmt.utils.Gender;
import com.hospitalmgmt.utils.HibernateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author raunakshakya
 */
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    
    @Column(name = "id")
    private Integer id;
    
    private String fullName;
    private String address;
    private Date dateOfBirth;
    private Gender gender;
    private String contact;
    private BloodGroup bloodgroup;
    private String history;
    private Date dateOfAdmission;
    private String problem;
    private Integer roomNumber;
    private Integer doctorId;

    public Patient(String fullName, String address, Date dateOfBirth, Gender gender, String contact,
            BloodGroup bloodgroup, String history, Date dateOfAdmission, String problem,
            Integer roomNumber, Integer doctorId) {
        this.fullName = fullName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.contact = contact;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.history = history;
        this.dateOfAdmission = dateOfAdmission;
        this.problem = problem;
        this.roomNumber = roomNumber;
        this.doctorId = doctorId;
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

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * gets the patient instance with the given id
     *
     * @param id
     * @return
     */
    public static Patient findById(Integer id) {
        logger.debug("Searching patient instance with " + id);

        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Patient patient = (Patient) session.get(Patient.class, id);
            hibernateUtils.commitTransaction();
            logger.debug("Patient instance found");
            return patient;
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
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
            logger.debug("Creating patient instance");
            Session session = hibernateUtils.getSession();
            Patient patient = bindNewPatientAttributes(patientDto);
            Integer patientId = (Integer) session.save(patient);
            hibernateUtils.commitTransaction();
            logger.debug("Patient instance created with " + patientId);
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
            JOptionPane.showMessageDialog(null, "Error in inserting New Patient!!!", "Insert Failure", JOptionPane.ERROR_MESSAGE);
        } finally {
            logger.debug("Closing session instance");
            hibernateUtils.closeSession();
        }
    }

    /**
     * updates the patient instance matching the given id with the new values
     *
     * @param id
     * @param patientDto
     */
    public static void update(Integer id, HashMap patientDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            logger.debug("Updating patient instance with id: " + id);
            Session session = hibernateUtils.getSession();
            Patient patient = (Patient) session.get(Patient.class, id);
            bindPatientAttributes(patient, patientDto);
            session.update(patient);
            hibernateUtils.commitTransaction();
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
            JOptionPane.showMessageDialog(null, "Patient Record Not Found!!!");
        } finally {
            logger.debug("Closing session instance");
            hibernateUtils.closeSession();
        }
    }

    /**
     * deletes the patient instance matching the given id
     *
     * @param id
     */
    public static void delete(Integer id) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Patient patient = (Patient) session.get(Patient.class, id);
            session.delete(patient);
            hibernateUtils.commitTransaction();
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
            hibernateUtils.closeSession();
        }
    }

    private static void bindPatientAttributes(Patient patient, HashMap patientDto) {
        patient.setFullName((String) patientDto.get("fullName"));
        patient.setAddress((String) patientDto.get("address"));
        patient.setContact((String) patientDto.get("contact"));
        patient.setDateOfBirth((Date) patientDto.get("dateOfBirth"));
        patient.setDateOfAdmission((Date) patientDto.get("dateOfAdmission"));
        patient.setProblem((String) patientDto.get("problem"));
        patient.setHistory((String) patientDto.get("history"));
        patient.setRoomNumber((Integer) patientDto.get("roomNumber"));
        Integer doctorId = (Integer) patientDto.get("doctorId");
        patient.setDoctorId(doctorId);
        String gender = (String) patientDto.get("gender");
        patient.setGender(Gender.valueOf(gender));
    }

    private static Patient bindNewPatientAttributes(HashMap patientDto) {
        String fullName = (String) patientDto.get("fullName");
        String address = (String) patientDto.get("address");
        Date dateOfBirth = (Date) patientDto.get("dateOfBirth");
        String contact = (String) patientDto.get("contact");
        Date dateOfAdmission = (Date) patientDto.get("dateOfAdmission");
        String problem = (String) patientDto.get("problem");
        String history = (String) patientDto.get("history");
        String bloodgroupValue = (String) patientDto.get("bloodGroup");
        BloodGroup bloodGroup = BloodGroup.getBloodGroupFromName(bloodgroupValue);
        String genderValue = (String) patientDto.get("gender");
        Gender gender = (Gender.valueOf(genderValue));
        Integer roomNumber = (Integer) patientDto.get("roomNumber");
        Integer doctorId = (Integer) patientDto.get("doctorId");

        return new Patient(fullName, address, dateOfBirth, gender, contact, bloodGroup, history, dateOfAdmission, problem, roomNumber, doctorId);
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
