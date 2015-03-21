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
        super(LayoutUtils.NEW_PATIENT_TITLE);

        Container con = getContentPane();

        //Patient's Personal Information...
        mainTitle = new JLabel(LayoutUtils.ADD_PATIENT_TITLE);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(LayoutUtils.HEADING_X_COORDINATE, LayoutUtils.HEADING_Y_COORDINATE, LayoutUtils.HEADING_HORIZONTAL_LENGTH, LayoutUtils.HEADING_VERTICAL_LENGTH);
        add(mainTitle);

        subTitle = new JLabel(LayoutUtils.PERSONAL_INFORMATION_LABEL);
        subTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.UPPER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subTitle);

        lblfullname = new JLabel(LayoutUtils.FULL_NAME_LABEL);
        lblfullname.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, LayoutUtils.LABEL_LEFT_Y_COORDINATE, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 100, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtfullname);

        lbladdress = new JLabel(LayoutUtils.ADDRESS_LABEL);
        lbladdress.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 140, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 140, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, 100);
        //txtaddress.setLineWrap(true);
        add(txtaddress);

        //Patient's Date Of Birth...
        lbldob = new JLabel(LayoutUtils.DATE_OF_BIRTH_LABEL);
        lbldob.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 100, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 100, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdob);
        lbldobformat = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldobformat.setBounds(800, 100, 100, 20);
        add(lbldobformat);

        //Gender...
        lblgender = new JLabel(LayoutUtils.GENDER_LABEL);
        lblgender.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 140, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblgender);
        cbmf = new CheckboxGroup();
        cbm = new Checkbox("Male", cbmf, false);
        cbf = new Checkbox("Female", cbmf, false);
        cbm.setBounds(670, 140, 70, 25);
        add(cbm);
        cbf.setBounds(740, 140, 80, 25);
        add(cbf);

        //Telephone...
        lblcontact = new JLabel(LayoutUtils.CONTACT_LABEL);
        lblcontact.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 180, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 180, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtcontact);

        //Medical Information...
        submedTitle = new JLabel(LayoutUtils.MEDICAL_INFORMATION_LABEL);
        submedTitle.setFont(new Font("Arial", Font.BOLD, 18));
        submedTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.LOWER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(submedTitle);

        //BLood Group...
        lblbloodgroup = new JLabel(LayoutUtils.BLOOD_GROUP_LABEL);
        lblbloodgroup.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblbloodgroup);
        choiceBG = new Choice();
        choiceBG.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceBG.addItem(LayoutUtils.BLOOD_GROUP_SELECT_LABEL);
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
        lblhistory = new JLabel(LayoutUtils.HISTORY_LABEL);
        lblhistory.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblhistory);
        txthistory = new TextArea();
        txthistory.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        //txthistory.setLineWrap(true);
        add(txthistory);

        //Room...
        lblroom = new JLabel(LayoutUtils.ROOM_NUMBER_LABEL);
        lblroom.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 480, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblroom);
        txtroom = new JTextField(30);
        txtroom.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 480, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtroom);

        //Date of Admission...
        lbldoa = new JLabel(LayoutUtils.DATE_OF_ADMISSION_LABEL);
        lbldoa.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoa);
        txtdoa = new JTextField(40);
        txtdoa.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdoa);
        lbldoaformat = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldoaformat.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 410, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        //add(lbldoaformat);

        //Current Problem...
        lblcurrentprob = new JLabel(LayoutUtils.CURRENT_PROBLEM_LABEL);
        lblcurrentprob.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcurrentprob);
        txtcurrentprob = new TextArea();
        txtcurrentprob.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        //txtcurrentprob.setLineWrap(true);
        add(txtcurrentprob);

        //Treating Doctor...
        lbldoctor = new JLabel(LayoutUtils.ATTENDING_DOCTOR_LABEL);
        lbldoctor.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 480, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoctor);

        choiceDoc = new Choice();
        choiceDoc.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 480, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceDoc.addItem(LayoutUtils.ATTENDING_DOCTOR_SELECT_LABEL);
        add(choiceDoc);

        //Button to submit information...
        btnAdd = new JButton(LayoutUtils.ADD_BUTTON_LABEL);
        btnAdd.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE + 80, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnAdd);

        //Button to clear information...
        btnClear = new JButton(LayoutUtils.CLEAR_BUTTON_LABEL);
        btnClear.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE + 120, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE + 80, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
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
                choiceDoc.addItem(rs.getString("doctor_fullname"));
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
        setLocation(LayoutUtils.SUB_WINDOW_X_COORDINATE, LayoutUtils.SUB_WINDOW_Y_COORDINATE);
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
