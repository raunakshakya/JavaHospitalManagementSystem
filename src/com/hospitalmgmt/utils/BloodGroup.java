/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.utils;

/**
 *
 * @author raunakshakya
 */
public enum BloodGroup {

    A_NEGATIVE("A -ve"),
    A_POSITIVE("A +ve"),
    B_NEGATIVE("B -ve"),
    B_POSITIVE("B +ve"),
    AB_NEGATIVE("AB -ve"),
    AB_POSITIVE("AB +ve"),
    O_NEGATIVE("O +ve");

    String name;

    BloodGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static BloodGroup getBloodGroupFromName(String name) {
        BloodGroup bloodGroup = null;
        if (name != null) {
            for (BloodGroup b : BloodGroup.values()) {
                if (name.equalsIgnoreCase(b.name)) {
                    bloodGroup = b;
                }
            }
        }
        if (bloodGroup == null) {
            throw new IllegalArgumentException("No constant with name " + name + " found");
        } else {
            return bloodGroup;
        }
    }

}
