/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.utils;

import com.hospitalmgmt.models.Admin;
import com.hospitalmgmt.models.Doctor;
import com.hospitalmgmt.models.Patient;
import com.hospitalmgmt.models.Staff;
import static com.hospitalmgmt.system.HospitalMgmtSystem.logger;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author raunakshakya
 */
public class Bootstrap {

    public Bootstrap() {
        logger.info("Bootstrap loading started");

        bootstrapAdmin();
        bootstrapDoctor();
        bootstrapStaff();
        bootstrapPatient();
    }

    private void bootstrapAdmin() {
        logger.info("Bootstrapping admin instances started");

        HashMap adminDto1 = new HashMap();
        adminDto1.put("username", "u1");
        adminDto1.put("password", "u1");
        Admin.create(adminDto1);

        HashMap adminDto2 = new HashMap();
        adminDto2.put("username", "u2");
        adminDto2.put("password", "u2");
        Admin.create(adminDto2);
    }

    private void bootstrapDoctor() {
        logger.debug("Bootstrapping doctor instances started");

        HashMap doctorDto1 = new HashMap();
        doctorDto1.put("fullName", "Bob Stalmach");
        doctorDto1.put("address", "Stockholm");
        doctorDto1.put("contact", "977-1-4222456");
        doctorDto1.put("status", "ACTIVE");
        doctorDto1.put("gender", "MALE");
        doctorDto1.put("dateOfJoin", new Date());
        doctorDto1.put("specialization", "Cardiology");
        doctorDto1.put("shiftFrom", new Date());
        doctorDto1.put("shiftTo", new Date());
        Doctor.create(doctorDto1);

        HashMap doctorDto2 = new HashMap();
        doctorDto2.put("fullName", "Filip Rachunek");
        doctorDto2.put("address", "Gothenburg");
        doctorDto2.put("contact", "977-1-4555789");
        doctorDto2.put("status", "ACTIVE");
        doctorDto2.put("gender", "MALE");
        doctorDto2.put("dateOfJoin", new Date());
        doctorDto2.put("specialization", "Nerve Pyschiatry");
        doctorDto2.put("shiftFrom", new Date());
        doctorDto2.put("shiftTo", new Date());
        Doctor.create(doctorDto2);
    }

    private void bootstrapStaff() {
        logger.debug("Bootstrapping staff instances started");

        HashMap staffDto1 = new HashMap();
        staffDto1.put("fullName", "Bob Stalmach");
        staffDto1.put("address", "Stockholm");
        staffDto1.put("contact", "977-1-4222456");
        staffDto1.put("status", "ACTIVE");
        staffDto1.put("gender", "MALE");
        staffDto1.put("dateOfJoin", new Date());
        staffDto1.put("shiftFrom", new Date());
        staffDto1.put("shiftTo", new Date());
        Staff.create(staffDto1);

        HashMap staffDto2 = new HashMap();
        staffDto2.put("fullName", "Filip Rachunek");
        staffDto2.put("address", "Gothenburg");
        staffDto2.put("contact", "977-1-4555789");
        staffDto2.put("status", "ACTIVE");
        staffDto2.put("gender", "MALE");
        staffDto2.put("dateOfJoin", new Date());
        staffDto2.put("specialization", "Nerve Pyschiatry");
        staffDto2.put("shiftFrom", new Date());
        staffDto2.put("shiftTo", new Date());
        Staff.create(staffDto2);
    }

    private void bootstrapPatient() {
        logger.debug("Bootstrapping patient instances started");

        HashMap patientDto1 = new HashMap();
        patientDto1.put("fullName", "Bob Stalmach");
        patientDto1.put("address", "Stockholm");
        patientDto1.put("contact", "977-1-4222456");
        patientDto1.put("status", "ACTIVE");
        patientDto1.put("gender", "MALE");
        patientDto1.put("dateOfAdmission", new Date());
        patientDto1.put("problem", "Pain in throat");
        patientDto1.put("history", "Nothing");
        patientDto1.put("dateOfBirth", new Date());
        patientDto1.put("doctorId", 1);
        Patient.create(patientDto1);

        HashMap patientDto2 = new HashMap();
        patientDto2.put("fullName", "Filip Rachunek");
        patientDto2.put("address", "Gothenburg");
        patientDto2.put("contact", "977-1-4555789");
        patientDto2.put("status", "ACTIVE");
        patientDto2.put("gender", "MALE");
        patientDto1.put("dateOfAdmission", new Date());
        patientDto1.put("problem", "Pain in heart");
        patientDto1.put("history", "Nothing");
        patientDto1.put("dateOfBirth", new Date());
        patientDto2.put("doctorId", 2);
        Patient.create(patientDto2);
    }

}
