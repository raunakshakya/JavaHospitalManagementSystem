/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.i18n;

import java.util.ListResourceBundle;

/**
 *
 * @author raunakshakya
 */
public class MessageBundle extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private final Object[][] contents = {
        {"application.title", "Vayodha Hospital Management System"},
        {"application.name", "Hospital Management System"},
        {"current.version.title", "Current Version"},
        {"help.contents.title", "Help Contents"},
        {"license.title", "License"},
        {"footer.title", "Developer: Raunak Shakya @NIIT Kantipath 2014"},
        
        {"personal.information.title", "Personal Information"},
        {"professional.information.title", "Professional Information"},
        {"medical.information.title", "Medical Information"},
        {"patient.billing.information.title", "Patient Billing Information"},
        
        {"label.full.name", "Full Name :"},
        {"label.address", "Address :"},
        {"label.date.of.birth", "Date Of Birth :"},
        {"label.date.format", "(dd-mm-yyyy)"},
        {"label.gender", "Gender :"},
        {"label.contact", "Contact :"},
        {"label.blood.group", "Blood Group :"},
        {"label.select.blood.group", "-----Select Blood Group-----"},
        {"label.history", "History :"},
        {"label.room.number", "Room No.:"},
        {"label.date.of.admission", "Admitted On:"},
        {"label.problem", "Problem :"},
        {"label.attending.doctor", "Doctor :"},
        {"label.attending.doctor.select", "-----Select Doctor-----"},
        {"label.specialization", "Specialization :"},
        {"label.status", "Status"},
        {"label.date.of.join", "Date Of Join :"},
        {"label.shift.from", "Shift From :"},
        {"label.shift.to", "Shift To :"},
        {"label.time.format", "(hh-mm-ss)"},
        {"label.department", "Department :"},
        {"label.post", "Post :"},
        {"label.insert.patient.no", "Insert Patient Number"},
        {"label.patient.number", "Patient No.:"},
        
        {"label.new.patient.information", "New Patient Information"},
        {"label.add.patient.information", "Add Patient Information"},
        {"label.modify.patient.information", "Modify Patient Information"},
        {"label.view.patient.information", "View Patient Information"},
        {"label.patient.list", "Patient List"},
        
        {"label.new.doctor.information", "New Doctor Information"},
        {"label.add.doctor.information", "Add Doctor Information"},
        {"label.modify.doctor.information", "Modify Doctor Information"},
        {"label.view.doctor.information", "View Doctor Information"},
        {"label.doctor.information", "Doctor Information"},
        {"label.insert.doctor.id", "Insert Doctor Identity :"},
        {"label.doctor.id", "Doctor ID :"},
        
        {"label.new.staff.information", "New Staff Information"},
        {"label.add.staff.information", "Add Staff Information"},
        {"label.modify.staff.information", "Modify Staff Information"},
        {"label.view.staff.information", "View Staff Information"},
        {"label.staff.information", "Staff Information"},
        {"label.insert.staff.id", "Insert Staff Identity :"},
        {"label.staff.id", "Staff ID :"},
            
        {"common.login", "LOGIN"},
        {"common.cancel", "CANCEL"},
        {"common.add", "ADD"},
        {"common.clear", "CLEAR"},
        {"common.search", "SEARCH"},
        {"common.clear.all", "CLEAR ALL"},
        {"update.doctor.label", "UPDATE DOCTOR"},
        {"update.staff.label", "UPDATE STAFF"}
    };

}
