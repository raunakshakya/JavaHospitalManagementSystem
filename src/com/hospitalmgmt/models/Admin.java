/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.models;

import com.hospitalmgmt.utils.HibernateUtils;
import java.util.List;
import org.hibernate.Criteria;

/**
 *
 * @author raunakshakya
 */

public class Admin {

    Integer id;
    String username;
    String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static List<Admin> findAll() {
        HibernateUtils hibernateUtils = new HibernateUtils();
        Criteria query = hibernateUtils.getSession().createCriteria(Admin.class);
        return query.list();
    }
    
}
