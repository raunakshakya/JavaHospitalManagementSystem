/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.system.utils.LayoutUtils;
import com.hospitalmgmt.system.utils.DBConnectionUtils;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Raunak Shakya
 */
public class PatientView extends JInternalFrame {

    Connection conn = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;

    JLabel mainTitle, subTitle, lblfullname, lbladdress, lblcontact, submedTitle, lblbloodgroup, lbldob, lblhistory, lblcurrentproblem, lblPatNo, lblInsertPNo, lblroomno, lbldoa, lblgender, lrtype, lbldf1, lbldf2, lbldoctor;
    JTextField txtfullname, txtcontact, txtpatno, txtbloodgroup, txtroomno, txtdoa, txtgender, tfrtype, txtdoctor, txtdob;
    TextArea txtaddress, txthistory, txtcurrentproblem;
    JButton btnSubmit, btnClear, bback;

    public PatientView() {
        super(LayoutUtils.VIEW_PATIENT_TITLE);

        // PERSONAL INFORMATION
        mainTitle = new JLabel(LayoutUtils.VIEW_PATIENT_TITLE);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 20);
        add(mainTitle);

        //Get the Patient Number...
        lblInsertPNo = new JLabel("Insert Patient Number");
        lblInsertPNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertPNo.setBounds(40, 70, 160, 25);
        add(lblInsertPNo);

        lblPatNo = new JLabel("Patient No.:");
        lblPatNo.setBounds(40, 100, 150, 25);
        add(lblPatNo);

        txtpatno = new JTextField(30);
        txtpatno.setBounds(140, 100, 160, 25);
        add(txtpatno);

        btnSubmit = new JButton(LayoutUtils.SEARCH_BUTTON_LABEL);
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton(LayoutUtils.CLEAR_ALL_BUTTON_LABEL);
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);

        //Personal Information Title...
        subTitle = new JLabel(LayoutUtils.PERSONAL_INFORMATION_LABEL);
        subTitle.setFont(new Font("Arial", Font.BOLD, 20));
        subTitle.setBounds(40, 160, 200, 30);
        add(subTitle);

        lblfullname = new JLabel(LayoutUtils.FULL_NAME_LABEL);
        lblfullname.setBounds(60, 200, 80, 25);
        add(lblfullname);

        txtfullname = new JTextField(30);
        txtfullname.setBounds(200, 200, 200, 25);
        txtfullname.setEditable(false);
        add(txtfullname);

        lbladdress = new JLabel(LayoutUtils.ADDRESS_LABEL);
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);

        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 90);
        txtaddress.setEditable(false);
        add(txtaddress);

        lblcontact = new JLabel(LayoutUtils.CONTACT_LABEL);
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);

        txtcontact = new JTextField(30);
        txtcontact.setBounds(680, 200, 200, 25);
        txtcontact.setEditable(false);
        add(txtcontact);

        lblgender = new JLabel(LayoutUtils.GENDER_LABEL);
        lblgender.setBounds(540, 240, 60, 25);
        add(lblgender);

        txtgender = new JTextField(50);
        txtgender.setBounds(680, 240, 200, 25);
        txtgender.setEditable(false);
        add(txtgender);

        lbldob = new JLabel(LayoutUtils.DATE_OF_BIRTH_LABEL);
        lbldob.setBounds(540, 280, 120, 25);
        add(lbldob);

        txtdob = new JTextField(15);
        txtdob.setBounds(680, 280, 200, 25);
        txtdob.setEditable(false);
        add(txtdob);

        lbldf1 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldf1.setBounds(800, 310, 100, 25);
        add(lbldf1);

        //Medical Information Title...
        submedTitle = new JLabel("Medical Information");
        submedTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submedTitle.setBounds(40, 360, 200, 15);
        add(submedTitle);

        lblbloodgroup = new JLabel("Blood Group :");
        lblbloodgroup.setBounds(60, 400, 80, 25);
        add(lblbloodgroup);

        txtbloodgroup = new JTextField(30);
        txtbloodgroup.setBounds(200, 400, 200, 25);
        txtbloodgroup.setEditable(false);
        add(txtbloodgroup);

        lblhistory = new JLabel("History :");
        lblhistory.setBounds(60, 440, 80, 25);
        add(lblhistory);

        txthistory = new TextArea();
        txthistory.setBounds(200, 440, 200, 100);
        txthistory.setEditable(false);
        add(txthistory);

        lblroomno = new JLabel("Room No.:");
        lblroomno.setBounds(60, 560, 80, 25);
        add(lblroomno);

        txtroomno = new JTextField(30);
        txtroomno.setBounds(200, 560, 200, 25);
        txtroomno.setEditable(false);
        add(txtroomno);

        lbldoa = new JLabel("Date Of Admission :");
        lbldoa.setBounds(540, 400, 120, 25);
        add(lbldoa);

        txtdoa = new JTextField(40);
        txtdoa.setBounds(680, 400, 125, 20);
        txtdoa.setEditable(false);
        add(txtdoa);

        lbldf2 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldf2.setBounds(810, 400, 100, 20);
        add(lbldf2);

        lblcurrentproblem = new JLabel("Current Problem :");
        lblcurrentproblem.setBounds(540, 440, 100, 25);
        add(lblcurrentproblem);

        txtcurrentproblem = new TextArea();
        txtcurrentproblem.setBounds(680, 440, 200, 100);
        txtcurrentproblem.setEditable(false);
        add(txtcurrentproblem);

        lbldoctor = new JLabel("Attending Doctor :");
        lbldoctor.setBounds(540, 560, 100, 25);
        add(lbldoctor);

        txtdoctor = new JTextField(100);
        txtdoctor.setBounds(680, 560, 200, 25);
        txtdoctor.setEditable(false);
        add(txtdoctor);

        //Database Connection...
        try {
            Class.forName(DBConnectionUtils.DB_DRIVER);
            conn = DriverManager.getConnection(DBConnectionUtils.DB_CONNECTION_URL, DBConnectionUtils.DB_USERNAME, DBConnectionUtils.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database!!!");
        }

        btnClear.addActionListener(new clear());
        btnSubmit.addActionListener(new submit());

        setSize(LayoutUtils.INNER_WINDOW_WIDTH, LayoutUtils.INNER_WINDOW_HEIGHT);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(LayoutUtils.SUB_WINDOW_X_COORDINATE, LayoutUtils.SUB_WINDOW_Y_COORDINATE);
        setLayout(null);
    }

    public void actionPerformed(ActionEvent ae) {
    }

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtfullname.setText("");
            txtcontact.setText("");
            txtdob.setText("");
            txtaddress.setText("");
            txthistory.setText("");
            txtcurrentproblem.setText("");
            txtpatno.setText("");
            txtroomno.setText("");
            txtdoa.setText("");
            txtgender.setText("");
            txtbloodgroup.setText("");
        }
    }

    class submit implements ActionListener {

        Integer num, no = 0;
        String name, addr, contact, blgr, hist, dob, current, room, dateadd, rtype, mf, docid, docname;

        @Override
        public void actionPerformed(ActionEvent ae) {

            try {
                if (!txtpatno.getText().trim().equals("")) {
                    num = Integer.parseInt(txtpatno.getText().trim());
                    stmt1 = conn.prepareStatement("SELECT * FROM patient_table WHERE patient_id=?");
                    stmt1.setInt(1, num);
                    rs1 = stmt1.executeQuery();
                    if (rs1.next()) {
                        name = rs1.getString("patient_fullname");
                        addr = rs1.getString("patient_address");
                        contact = rs1.getString("patient_contact");
                        hist = rs1.getString("patient_history");
                        dob = rs1.getString("patient_dateofbirth");
                        current = rs1.getString("patient_currentproblem");
                        blgr = rs1.getString("patient_bloodgroup");
                        room = rs1.getString("patient_roomid");
                        dateadd = rs1.getString("patient_doa");
                        mf = rs1.getString("patient_gender");
                        docid = rs1.getString("patient_doctorid");

                        txtfullname.setText(name);
                        txtcontact.setText(contact);
                        txtdob.setText(dob);
                        txtaddress.setText(addr);
                        txthistory.setText(hist);
                        txtcurrentproblem.setText(current);
                        txtbloodgroup.setText(blgr);
                        txtroomno.setText(room);
                        txtdoa.setText(dateadd);
                        txtgender.setText(mf);

                        stmt2 = conn.prepareStatement("SELECT * FROM doctor_table WHERE doctor_id=?");
                        stmt2.setInt(1, Integer.parseInt(docid));
                        rs2 = stmt2.executeQuery();
                        if (rs2.next()) {
                            docname = rs2.getString("doctor_fullname");
                        }
                        txtdoctor.setText(docname);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Patient Record Found!!!");
                        txtpatno.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID!!!");
                }
            } catch (SQLException sq) {
                JOptionPane.showMessageDialog(null, "Error in Retrieving Patient Data!!!");
            }
        }
    }
}
