/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.utils;

import static com.hospitalmgmt.system.HospitalMgmtSystem.logger;
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
        try {
            sessionFactory = new Configuration().configure("com/hospitalmgmt/datasource/hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
        } catch (Exception exception) {
            logger.error("HibernateUtils instance could not be initialized:\n" + exception.getMessage());
        }
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
        if (session != null) {
            session.close();
        }
    }

}
