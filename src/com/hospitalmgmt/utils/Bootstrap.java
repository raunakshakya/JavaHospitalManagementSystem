/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.utils;

import com.hospitalmgmt.models.Doctor;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author raunakshakya
 */
public class Bootstrap {

    public Bootstrap() {
        bootstrapDoctor();
        bootstrapStaff();
        bootstrapPatient();
    }

    private void bootstrapDoctor() {
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

    }

    private void bootstrapPatient() {

    }

}
