/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import com.hospitalmgmt.utils.HibernateUtils;
import java.util.HashMap;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import static com.hospitalmgmt.system.HospitalMgmtSystem.logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author raunakshakya
 */
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * creates a doctor instance
     *
     * @param adminDto
     */
    public static void create(HashMap adminDto) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            logger.debug("Creating admin instance");
            Session session = hibernateUtils.getSession();
            Admin admin = bindNewAdminAttributes(adminDto);
            Integer adminId = (Integer) session.save(admin);
            session.flush();
            hibernateUtils.commitTransaction();
            logger.debug("Admin instance created with " + adminId);
        } catch (HibernateException hibernateException) {
            logger.debug("Rolling back transaction:\n" + hibernateException.getMessage());
            hibernateUtils.rollbackTransaction();
        } finally {
            logger.debug("Closing session instance");
            hibernateUtils.closeSession();
        }
    }

    private static Admin bindNewAdminAttributes(HashMap adminDto) {
        String username = (String) adminDto.get("username");
        String password = (String) adminDto.get("password");
        Admin admin = new Admin(username, password);
        return admin;
    }

    public static List<Admin> findAll() {
        logger.debug("Listing admins");

        HibernateUtils hibernateUtils = new HibernateUtils();
        //Criteria query = hibernateUtils.getSession().createCriteria(Admin.class);
        List admins = hibernateUtils.getSession().createQuery("FROM Admin").list();
        return admins; //query.list();
    }

}
