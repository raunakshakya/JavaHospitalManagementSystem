/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.system.utils.LayoutUtils;
import com.hospitalmgmt.system.utils.DBConnectionUtils;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Raunak Shakya
 */
public final class PatientAdd extends JInternalFrame implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, subTitle, submedTitle,
            lblfullname, lbladdress, lblcontact, lbldob, lbldobformat, lblgender,
            lblbloodgroup, lblhistory, lblcurrentprob, lblroom, lbldoa, lbldoaformat, lbldoctor;
    JTextField txtfullname, txtcontact, txtdob;
    JTextField txtroom, txtdoa, txtdoctor;
    TextArea txtaddress, txthistory, txtcurrentprob;
    JCheckBox chkboxStatus;
    Choice choiceBG;
    Choice choiceDoc;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnAdd, btnClear;
    int patientStatus;

    public PatientAdd() {
        super(LayoutUtils.ADD_PATIENT_TITLE);

        Container con = getContentPane();

        //Patient's Personal Information...
        mainTitle = new JLabel(LayoutUtils.ADD_PATIENT_TITLE);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(LayoutUtils.HEADING_X_COORDINATE, LayoutUtils.HEADING_Y_COORDINATE, LayoutUtils.HEADING_HORIZONTAL_LENGTH, LayoutUtils.HEADING_VERTICAL_LENGTH);
        add(mainTitle);

        subTitle = new JLabel("Personal Information");
        subTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subTitle.setBounds(40, 70, 200, 20);
        add(subTitle);

        lblfullname = new JLabel("Full Name :");
        lblfullname.setBounds(100, 100, 70, 20);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(220, 100, 200, 20);
        add(txtfullname);

        lbladdress = new JLabel("Address :");
        lbladdress.setBounds(100, 140, 70, 20);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(220, 140, 200, 100);
        add(txtaddress);

        //Patient's Date Of Birth...
        lbldob = new JLabel("Date of Birth :");
        lbldob.setBounds(540, 100, 120, 20);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(670, 100, 120, 20);
        add(txtdob);
        lbldobformat = new JLabel("(dd-mm-yyyy)");
        lbldobformat.setBounds(800, 100, 100, 20);
        add(lbldobformat);

        //Gender...
        lblgender = new JLabel("Gender :");
        lblgender.setBounds(540, 140, 50, 25);
        add(lblgender);
        cbmf = new CheckboxGroup();
        cbm = new Checkbox("Male", cbmf, false);
        cbf = new Checkbox("Female", cbmf, false);
        cbm.setBounds(670, 140, 70, 25);
        add(cbm);
        cbf.setBounds(740, 140, 80, 25);
        add(cbf);

        //Telephone...
        lblcontact = new JLabel("Contact :");
        lblcontact.setBounds(540, 180, 50, 20);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(670, 180, 200, 20);
        add(txtcontact);

        //Medical Information...
        submedTitle = new JLabel("Medical Information");
        submedTitle.setFont(new Font("Arial", Font.BOLD, 18));
        submedTitle.setBounds(40, 275, 200, 20);
        add(submedTitle);

        //BLood Group...
        lblbloodgroup = new JLabel("Blood Group :");
        lblbloodgroup.setBounds(100, 310, 80, 20);
        add(lblbloodgroup);
        choiceBG = new Choice();
        choiceBG.setBounds(220, 310, 200, 20);
        choiceBG.addItem("-----Select Blood Group-----");
        choiceBG.addItem("A -ve");
        choiceBG.addItem("A +ve");
        choiceBG.addItem("B -ve");
        choiceBG.addItem("B +ve");
        choiceBG.addItem("AB -ve");
        choiceBG.addItem("AB +ve");
        choiceBG.addItem("O +ve");
        choiceBG.addItem("O -ve");
        add(choiceBG);

        //Medical History...
        lblhistory = new JLabel("History :");
        lblhistory.setBounds(100, 350, 50, 20);
        add(lblhistory);
        txthistory = new TextArea();
        txthistory.setBounds(220, 350, 200, 100);
        add(txthistory);

        //Room...
        lblroom = new JLabel("Room No.:");
        lblroom.setBounds(100, 510, 60, 20);
        add(lblroom);
        txtroom = new JTextField(30);
        txtroom.setBounds(220, 510, 200, 20);
        add(txtroom);

        //Date of Admission...
        lbldoa = new JLabel("Date Of Admission :");
        lbldoa.setBounds(540, 310, 120, 20);
        add(lbldoa);
        txtdoa = new JTextField(40);
        txtdoa.setBounds(670, 310, 120, 20);
        add(txtdoa);
        lbldoaformat = new JLabel("(dd-mm-yyyy)");
        lbldoaformat.setBounds(800, 310, 100, 20);
        add(lbldoaformat);

        //Current Problem...
        lblcurrentprob = new JLabel("Current Problem :");
        lblcurrentprob.setBounds(540, 350, 100, 20);
        add(lblcurrentprob);
        txtcurrentprob = new TextArea();
        txtcurrentprob.setBounds(670, 350, 200, 140);
        add(txtcurrentprob);

        //Treating Doctor...
        lbldoctor = new JLabel("Attending Doctor :");
        lbldoctor.setBounds(540, 510, 130, 20);
        add(lbldoctor);

        choiceDoc = new Choice();
        choiceDoc.setBounds(670, 510, 200, 20);
        choiceDoc.addItem("-----Select Doctor-----");
        add(choiceDoc);

//        txtdoctor = new JTextField(100);
//        txtdoctor.setBounds(670, 510, 200, 20);
//        add(txtdoctor);
        //Button to submit information...
        btnAdd = new JButton("ADD");
        btnAdd.setBounds(330, 575, 100, 30);
        add(btnAdd);

        //Button to clear information...
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(460, 575, 100, 30);
        add(btnClear);

        //Database Connection...
        try {
            Class.forName(DBConnectionUtils.DB_DRIVER);
            conn = DriverManager.getConnection(DBConnectionUtils.DB_CONNECTION_URL, DBConnectionUtils.DB_USERNAME, DBConnectionUtils.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the Database!!!");
        }

        try {
            stmt = conn.prepareStatement("SELECT * FROM doctor_table");
            rs = stmt.executeQuery();
            while (rs.next()) {
                choiceDoc.addItem(rs.getString("doctor_fullname").toString());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in populating Doctors!!!");
        }

        btnClear.addActionListener(new clear());
        btnAdd.addActionListener(new submit());

        setSize(LayoutUtils.INNER_WINDOW_WIDTH, LayoutUtils.INNER_WINDOW_HEIGHT);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(200, 100);
        setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtfullname.setText("");
            txtdob.setText("");
            txtaddress.setText("");
            txthistory.setText("");
            txtcurrentprob.setText("");
            txtcontact.setText("");
            txtroom.setText("");
            txtdoa.setText("");
            cbmf.setSelectedCheckbox(null);
            choiceBG.select(0);
            choiceDoc.select(0);
            
        }
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String fullname = txtfullname.getText().trim();
            String address = txtaddress.getText().trim();
            String contact = txtcontact.getText().trim();
            String currentproblem = txtcurrentprob.getText().trim();
            String roomno = txtroom.getText().trim();

            int bgindex = choiceBG.getSelectedIndex();
            String bloodgroup = "";
            if (bgindex != 0) {
                bloodgroup = choiceBG.getSelectedItem();
            }

            String history = txthistory.getText().trim();
            String dob = txtdob.getText().trim();
            String doa = txtdoa.getText().trim();

            int docindex = choiceDoc.getSelectedIndex();
            String doctorname = "";
            if (docindex != 0) {
                doctorname = choiceDoc.getSelectedItem();
            }

            String gender = "";
            if (cbm.getState() == true) {
                gender = "M";
            }
            if (cbf.getState() == true) {
                gender = "F";
            }

            if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || currentproblem.isEmpty() || roomno.isEmpty()
                    || bloodgroup.isEmpty() || dob.isEmpty() || doa.isEmpty() || gender.isEmpty() || doctorname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter all the field data correctly!!!");
            } else {
                int doctorid = 0;

                try {
                    System.out.println(doctorname);
                    stmt = conn.prepareStatement("SELECT doctor_id FROM doctor_table WHERE doctor_fullname='"
                            + doctorname + "'");
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        doctorid = rs.getInt("doctor_id");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error retrieving Doctor ID from doctor_table!!!");
                }

                if (doctorid != 0) {
                    try {
                        stmt = conn.prepareStatement("INSERT INTO patient_table(patient_fullname, patient_address, "
                                + "patient_contact, patient_bloodgroup, patient_history, patient_dateofbirth, "
                                + "patient_currentproblem, patient_roomid, patient_doa, patient_gender, patient_doctorid) "
                                + "VALUES('" + fullname + "', '" + address + "', '" + contact + "', '"
                                + bloodgroup + "', '" + history + "', '" + dob + "', '" + currentproblem + "', '"
                                + roomno + "', '" + doa + "', '" + gender + "', '" + doctorid + "');");

                        int success = stmt.executeUpdate();
                        if (success > 0) {
                            JOptionPane.showMessageDialog(null, "New Patient Has Been Added!!!", "Insert Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error in inserting New Patient!!!", "Insert Failure", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}
