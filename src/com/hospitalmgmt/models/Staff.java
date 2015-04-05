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

    public Staff(String fullName, String address, Date dateOfBirth, Gender gender, String contact,
            EmployeeStatus employeeStatus, String department, String post, Date shiftFrom, Date shiftTo, Date dateOfJoin) {
        super(fullName, address, dateOfBirth, gender, contact, employeeStatus);
        this.department = department;
        this.post = post;
        this.shiftFrom = shiftFrom;
        this.shiftTo = shiftTo;
        this.dateOfJoin = dateOfJoin;
    }

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
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
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
            Staff staff = bindStaffAttributes(staffDto);
            session.save(staff);
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
     * updates the staff instance matching the given id with the new values
     *
     * @param id
     * @param staffDto
     */
    public static void update(Integer id, HashMap staffDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            logger.debug("Updating staff instance with id: " + id);
            Session session = hibernateUtils.getSession();
            Staff staff = (Staff) session.get(Staff.class, id);
            bindNewStaffAttributes(staff, staffDto);
            session.update(staff);
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
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
            hibernateUtils.closeSession();
        }
    }

    private static void bindNewStaffAttributes(Staff staff, HashMap staffDto) {
        staff.setFullName((String) staffDto.get("fullName"));
        staff.setAddress((String) staffDto.get("address"));
        staff.setContact((String) staffDto.get("contact"));
        String genderValue = (String) staffDto.get("gender");
        staff.setGender(Gender.valueOf(genderValue));
        staff.setDateOfBirth((Date) staffDto.get("dateOfBirth"));
        staff.setDateOfJoin((Date) staffDto.get("dateOfJoin"));
        staff.setShiftFrom((Date) staffDto.get("shiftFrom"));
        staff.setShiftTo((Date) staffDto.get("shiftTo"));
        staff.setDepartment((String) staffDto.get("department"));
    }

    private static Staff bindStaffAttributes(HashMap staffDto) {
        String fullName = (String) staffDto.get("fullName");
        String address = (String) staffDto.get("address");
        Date dateOfBirth = (Date) staffDto.get("dateOfBirth");
        String genderValue = (String) staffDto.get("gender");
        Gender gender = Gender.valueOf(genderValue);
        String contact = (String) staffDto.get("contact");
        String status = (String) staffDto.get("status");
        EmployeeStatus employeeStatus = EmployeeStatus.valueOf(status);
        String post = (String) staffDto.get("post");
        Date shiftFrom = (Date) staffDto.get("shiftFrom");
        Date shiftTo = (Date) staffDto.get("shiftTo");
        Date dateOfJoin = (Date) staffDto.get("dateOfJoin");
        String department = (String) staffDto.get("department");

        return new Staff(fullName, address, dateOfBirth, gender, contact, employeeStatus,
                department, post, shiftFrom, shiftTo, dateOfJoin);
    }

    public static List<Staff> findAll() {
        logger.debug("Listing staffs");

        HibernateUtils hibernateUtils = new HibernateUtils();
        //Criteria query = hibernateUtils.getSession().createCriteria(Staff.class);
        List staffs = hibernateUtils.getSession().createQuery("FROM Staff").list();
        return staffs; //query.list();
    }

}
