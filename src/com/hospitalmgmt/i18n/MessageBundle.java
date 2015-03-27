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
        
        {"personal.information", "Personal Information"},
        {"professional.information", "Professional Information"}
    };

}
