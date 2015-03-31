/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author raunakshakya
 */
public class HibernateUtils {

    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    public HibernateUtils() {
        sessionFactory = new Configuration().configure("com/hospitalmgmt/system/hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void commitTransaction() {
        if (transaction != null) {
            transaction.commit();
        }
    }

    public void rollbackTransaction() {
        if (transaction != null) {
            transaction.rollback();
        }
    }

    public void closeSession() {
        session.close();
    }

}
