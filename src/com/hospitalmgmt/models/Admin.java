/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import com.hospitalmgmt.utils.HibernateUtils;
import com.hospitalmgmt.utils.LoggerUtils;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;

/**
 *
 * @author raunakshakya
 */
public class Admin {

    Integer id;
    String username;
    String pass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * creates a doctor instance
     *
     * @param adminDto
     */
    public static void create(HashMap adminDto) {
        Logger log = LoggerUtils.logger;

        HibernateUtils hibernateUtils = new HibernateUtils();
        try {
            Session session = hibernateUtils.getSession();
            Admin admin = new Admin();
            bindAdminAttributes(admin, adminDto);
            session.save(admin);
            hibernateUtils.commitTransaction();
            log.debug("> Admin created with " + admin.id);
        } catch (HibernateException hibernateException) {
            hibernateUtils.rollbackTransaction();
        } finally {
            hibernateUtils.closeSession();
        }
    }

    private static void bindAdminAttributes(Admin admin, HashMap adminDto) {
        admin.setUsername((String) adminDto.get("username"));
        admin.setPass((String) adminDto.get("password"));
    }

    public static List<Admin> findAll() {
        HibernateUtils hibernateUtils = new HibernateUtils();
        Criteria query = hibernateUtils.getSession().createCriteria(Admin.class);
        return query.list();
    }

}
