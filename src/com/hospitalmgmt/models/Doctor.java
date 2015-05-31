/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import static com.hospitalmgmt.system.HospitalMgmtSystem.logger;
import com.hospitalmgmt.utils.EmployeeStatus;
import com.hospitalmgmt.utils.Gender;
import com.hospitalmgmt.utils.HibernateUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author raunakshakya
 */
@Entity
@DiscriminatorValue("doctor")
public class Doctor extends Employee {

    private String specialization;
    private Date dateOfJoin;
    private Date shiftFrom;
    private Date shiftTo;

    public Doctor(String fullName, String address, Date dateOfBirth, Gender gender, String contact,
            EmployeeStatus employeeStatus, String specialization, Date dateOfJoin, Date shiftFrom, Date shiftTo) {
        super(fullName, address, dateOfBirth, gender, contact, employeeStatus);
        this.specialization = specialization;
        this.dateOfJoin = dateOfJoin;
        this.shiftFrom = shiftFrom;
        this.shiftTo = shiftTo;
    }

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
        logger.debug("Searching doctor instance with " + id);

        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Doctor doctor = (Doctor) session.get(Doctor.class, id);
            hibernateUtils.commitTransaction();
            return doctor;
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
     * creates a doctor instance
     *
     * @param doctorDto
     */
    public static void create(HashMap doctorDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            logger.debug("Creating doctor instance");
            Session session = hibernateUtils.getSession();
            Doctor doctor = bindDoctorAttributes(doctorDto);
            Integer doctorId = (Integer) session.save(doctor);
            hibernateUtils.commitTransaction();
            logger.debug("Doctor instance created with " + doctorId);
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
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
            logger.debug("Updating doctor instance with id: " + id);
            Session session = hibernateUtils.getSession();
            Doctor doctor = (Doctor) session.get(Doctor.class, id);
            bindNewDoctorAttributes(doctor, doctorDto);
            session.update(doctor);
            hibernateUtils.commitTransaction();
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
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
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
            hibernateUtils.closeSession();
        }
    }

    private static void bindNewDoctorAttributes(Doctor doctor, HashMap doctorDto) {
        doctor.setFullName((String) doctorDto.get("fullName"));
        doctor.setAddress((String) doctorDto.get("address"));
        doctor.setContact((String) doctorDto.get("contact"));
        String status = (String) doctorDto.get("status");
        doctor.setStatus(EmployeeStatus.valueOf(status));
        String gender = (String) doctorDto.get("gender");
        doctor.setGender(Gender.valueOf(gender));
        doctor.setDateOfBirth((Date) doctorDto.get("dateOfBirth"));
        doctor.setDateOfJoin((Date) doctorDto.get("dateOfJoin"));
        doctor.setShiftFrom((Date) doctorDto.get("shiftFrom"));
        doctor.setShiftTo((Date) doctorDto.get("shiftTo"));
        doctor.setSpecialization((String) doctorDto.get("specialization"));
    }

    private static Doctor bindDoctorAttributes(HashMap doctorDto) {
        String fullName = (String) doctorDto.get("fullName");
        String address = (String) doctorDto.get("address");
        Date dateOfBirth = (Date) doctorDto.get("dateOfBirth");
        String genderValue = (String) doctorDto.get("gender");
        Gender gender = Gender.valueOf(genderValue);
        String contact = (String) doctorDto.get("contact");
        String status = (String) doctorDto.get("status");
        EmployeeStatus employeeStatus = EmployeeStatus.valueOf(status);
        String specialization = (String) doctorDto.get("specialization");
        Date dateOfJoin = (Date) doctorDto.get("dateOfJoin");
        Date shiftFrom = (Date) doctorDto.get("shiftFrom");
        Date shiftTo = (Date) doctorDto.get("shiftTo");

        return new Doctor(fullName, address, dateOfBirth, gender, contact, employeeStatus,
                specialization, dateOfJoin, shiftFrom, shiftTo);
    }

    /**
     * retrieve all the active doctors
     *
     * @return
     */
    public static List<Doctor> findAll() {
        logger.debug("Listing doctors");

        HibernateUtils hibernateUtils = new HibernateUtils();
        //Criteria query = hibernateUtils.getSession().createCriteria(Doctor.class);
        List doctors = hibernateUtils.getSession().createQuery("FROM Doctor").list();
        return doctors; //query.list();
    }

}
