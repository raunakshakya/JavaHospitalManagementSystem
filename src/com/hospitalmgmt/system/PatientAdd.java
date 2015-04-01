/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Doctor;
import com.hospitalmgmt.models.Patient;
import com.hospitalmgmt.utils.BloodGroup;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.Gender;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.swing.*;

/**
 *
 * @author Raunak Shakya
 */
public final class PatientAdd extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

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
    Choice choiceBG, choiceDoc;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnAdd, btnClear;
    int patientStatus;

    public PatientAdd() {
        super(messages.getString("label.new.patient.information"));

        Container con = getContentPane();

        //Patient's Personal Information...
        mainTitle = new JLabel(messages.getString("label.add.patient.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(LayoutUtils.HEADING_X_COORDINATE, LayoutUtils.HEADING_Y_COORDINATE, LayoutUtils.HEADING_HORIZONTAL_LENGTH, LayoutUtils.HEADING_VERTICAL_LENGTH);
        add(mainTitle);

        subTitle = new JLabel(messages.getString("personal.information.title"));
        subTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.UPPER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subTitle);

        lblfullname = new JLabel(messages.getString("label.full.name"));
        lblfullname.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, LayoutUtils.LABEL_LEFT_Y_COORDINATE, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 100, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtfullname);

        lbladdress = new JLabel(messages.getString("label.address"));
        lbladdress.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 140, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 140, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, 100);
        //txtaddress.setLineWrap(true);
        add(txtaddress);

        //Contact...
        lblcontact = new JLabel(messages.getString("label.contact"));
        lblcontact.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 100, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 100, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtcontact);

        //Gender...
        lblgender = new JLabel(messages.getString("label.gender"));
        lblgender.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 140, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblgender);
        cbmf = new CheckboxGroup();
        cbm = new Checkbox(Gender.MALE.getName(), cbmf, false);
        cbf = new Checkbox(Gender.FEMALE.getName(), cbmf, false);
        cbm.setBounds(670, 140, 70, 25);
        add(cbm);
        cbf.setBounds(740, 140, 80, 25);
        add(cbf);

        //Patient's Date Of Birth...
        lbldob = new JLabel(messages.getString("label.date.of.birth"));
        lbldob.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 180, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 180, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdob);
        lbldobformat = new JLabel(messages.getString("label.date.format"));
        lbldobformat.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 210, 160, 20);
        add(lbldobformat);

        //Medical Information...
        submedTitle = new JLabel(messages.getString("medical.information.title"));
        submedTitle.setFont(new Font("Arial", Font.BOLD, 18));
        submedTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.LOWER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(submedTitle);

        //BLood Group...
        lblbloodgroup = new JLabel(messages.getString("label.blood.group"));
        lblbloodgroup.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblbloodgroup);
        choiceBG = new Choice();
        choiceBG.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceBG.addItem(messages.getString("label.select.blood.group"));
        for (BloodGroup bloodgroup : BloodGroup.values()) {
            choiceBG.addItem(bloodgroup.getName());
        }
        add(choiceBG);

        //Medical History...
        lblhistory = new JLabel(messages.getString("label.history"));
        lblhistory.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblhistory);
        txthistory = new TextArea();
        txthistory.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        //txthistory.setLineWrap(true);
        add(txthistory);

        //Room...
        lblroom = new JLabel(messages.getString("label.room.number"));
        lblroom.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 480, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblroom);
        txtroom = new JTextField(30);
        txtroom.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 480, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtroom);

        //Date of Admission...
        lbldoa = new JLabel(messages.getString("label.date.of.admission"));
        lbldoa.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoa);
        txtdoa = new JTextField(40);
        txtdoa.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdoa);
        lbldoaformat = new JLabel(messages.getString("label.date.format"));
        lbldoaformat.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 410, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        //add(lbldoaformat);

        //Current Problem...
        lblcurrentprob = new JLabel(messages.getString("label.problem"));
        lblcurrentprob.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcurrentprob);
        txtcurrentprob = new TextArea();
        txtcurrentprob.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        //txtcurrentprob.setLineWrap(true);
        add(txtcurrentprob);

        //Treating Doctor...
        lbldoctor = new JLabel(messages.getString("label.attending.doctor"));
        lbldoctor.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 480, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoctor);
        choiceDoc = new Choice();
        choiceDoc.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 480, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceDoc.addItem(messages.getString("label.attending.doctor.select"));
        add(choiceDoc);

        //Button to submit information...
        btnAdd = new JButton(messages.getString("common.add"));
        btnAdd.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE + 80, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String fullname = txtfullname.getText().trim();
                String address = txtaddress.getText().trim();
                String contact = txtcontact.getText().trim();
                String currentproblem = txtcurrentprob.getText().trim();
                String roomno = txtroom.getText().trim();
                String bloodgroup = (choiceBG.getSelectedIndex() != 0) ? choiceBG.getSelectedItem() : null;
                String history = txthistory.getText().trim();
                String dob = txtdob.getText().trim();
                String doa = txtdoa.getText().trim();
                String doctorname = (choiceDoc.getSelectedIndex() != 0) ? choiceDoc.getSelectedItem() : null;
                String gender = (cbm.getState() == true) ? "MALE" : ((cbf.getState() == true) ? "FEMALE" : null);

                if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || currentproblem.isEmpty() || roomno.isEmpty()
                        || bloodgroup.isEmpty() || dob.isEmpty() || doa.isEmpty() || gender.isEmpty() || doctorname.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter all the field data correctly!!!");
                } else {
                    int doctorid = 0;
                    try {
                        stmt = conn.prepareStatement("SELECT doctor_id FROM doctor_table WHERE doctor_fullname='"
                                + doctorname + "'");
                        rs = stmt.executeQuery();
                        if (rs.next()) {
                            doctorid = rs.getInt("doctor_id");
                        }
                    } catch (SQLException exc) {
                        JOptionPane.showMessageDialog(null, "Error retrieving Doctor ID from doctor_table!!!");
                    }

                    if (doctorid != 0) {
                        HashMap patientDto = new HashMap();
                        patientDto.put("fullName", fullname);
                        patientDto.put("address", address);
                        patientDto.put("contact", contact);
                        patientDto.put("gender", gender);
                        patientDto.put("bloodgroup", bloodgroup);
                        patientDto.put("dateOfBirth", dob);
                        patientDto.put("dateOfJoin", doa);
                        patientDto.put("currentproblem", currentproblem);
                        patientDto.put("history", history);
                        patientDto.put("roomno", roomno);
                        patientDto.put("doctorid", doctorid);

                        Patient.create(patientDto);
                        JOptionPane.showMessageDialog(null, "New Patient Has Been Added!!!", "Insert Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        //Button to clear information...
        btnClear = new JButton(messages.getString("common.clear"));
        btnClear.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE + 120, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE + 80, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClearTheTextFields();
            }
        });

        ArrayList<Doctor> doctors = Doctor.findAll();
        for (Doctor doctor : doctors) {
            choiceDoc.addItem(doctor.getFullName());
        }

        setSize(LayoutUtils.INNER_WINDOW_WIDTH, LayoutUtils.INNER_WINDOW_HEIGHT);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(LayoutUtils.SUB_WINDOW_X_COORDINATE, LayoutUtils.SUB_WINDOW_Y_COORDINATE);
        setLayout(null);
    }

    public void doClearTheTextFields() {
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
