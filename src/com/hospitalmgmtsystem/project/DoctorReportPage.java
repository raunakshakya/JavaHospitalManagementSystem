/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmtsystem.project;

import javax.swing.JInternalFrame;

/**
 *
 * @author Raunak Shakya
 */
public class DoctorReportPage extends JInternalFrame {

    public DoctorReportPage() {
        
        DoctorDBData frame = new DoctorDBData();
        frame.setDefaultCloseOperation(1);
        frame.pack();
        frame.setVisible(true);

    }

}
