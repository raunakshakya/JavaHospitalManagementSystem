/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.utils.BloodGroup;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.DBConnectionUtils;
import com.hospitalmgmt.utils.Gender;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
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

    JLabel mainTitle, subTitle, lblfullname, lbladdress, lblcontact, submedTitle, lblbloodgroup, lbldob, lblhistory, lblcurrentproblem, lblPatNo, lblInsertPNo, lblroomno, lbldoa, lblgender, lrtype, lbldobformat1, lbldobformat2, lbldoctor;
    JTextField txtfullname, txtcontact, txtpatno, txtbloodgroup, txtroomno, txtdoa, txtgender, tfrtype, txtdoctor, txtdob;
    TextArea txtaddress, txthistory, txtcurrentproblem;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    Choice choiceBG, choiceDoc;
    JButton btnSubmit, btnClear, bback;

    public PatientView() {
        super(LayoutUtils.VIEW_PATIENT_TITLE);

        // PERSONAL INFORMATION
        mainTitle = new JLabel(LayoutUtils.VIEW_PATIENT_TITLE);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 20);
        add(mainTitle);

        //Get the Patient Number...
        lblInsertPNo = new JLabel(LayoutUtils.INSERT_PATIENT_NO_LABEL);
        lblInsertPNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertPNo.setBounds(40, 70, 200, 25);
        add(lblInsertPNo);

        lblPatNo = new JLabel(LayoutUtils.DOCTOR_NO_LABEL);
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
        subTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 160, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subTitle);

        lblfullname = new JLabel(LayoutUtils.FULL_NAME_LABEL);
        lblfullname.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 200, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 200, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtfullname.setEditable(false);
        add(txtfullname);

        lbladdress = new JLabel(LayoutUtils.ADDRESS_LABEL);
        lbladdress.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 240, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 240, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, 100);
        txtaddress.setEditable(false);
        add(txtaddress);

        lblcontact = new JLabel(LayoutUtils.CONTACT_LABEL);
        lblcontact.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 200, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 200, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtcontact.setEditable(false);
        add(txtcontact);

        lblgender = new JLabel(LayoutUtils.GENDER_LABEL);
        lblgender.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 240, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblgender);
        cbmf = new CheckboxGroup();
        cbm = new Checkbox(Gender.MALE.getName(), cbmf, false);
        cbf = new Checkbox(Gender.FEMALE.getName(), cbmf, false);
        cbm.setBounds(670, 240, 70, 25);
        add(cbm);
        cbf.setBounds(740, 240, 80, 25);
        add(cbf);

        lbldob = new JLabel(LayoutUtils.DATE_OF_BIRTH_LABEL);
        lbldob.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 280, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 280, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtdob.setEditable(false);
        add(txtdob);
        lbldobformat1 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldobformat1.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, 160, 25);
        add(lbldobformat1);

        //Medical Information Title...
        submedTitle = new JLabel(LayoutUtils.MEDICAL_INFORMATION_LABEL);
        submedTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submedTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 360, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(submedTitle);

        lblbloodgroup = new JLabel(LayoutUtils.BLOOD_GROUP_LABEL);
        lblbloodgroup.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 400, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblbloodgroup);
        choiceBG = new Choice();
        choiceBG.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 400, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceBG.addItem(LayoutUtils.BLOOD_GROUP_SELECT_LABEL);
        for (BloodGroup bloodgroup : BloodGroup.values()) {
            choiceBG.addItem(bloodgroup.getName());
        }
        add(choiceBG);

        lblhistory = new JLabel(LayoutUtils.HISTORY_LABEL);
        lblhistory.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 440, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblhistory);
        txthistory = new TextArea();
        txthistory.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 440, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        txthistory.setEditable(false);
        add(txthistory);

        lblroomno = new JLabel(LayoutUtils.ROOM_NUMBER_LABEL);
        lblroomno.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 560, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblroomno);
        txtroomno = new JTextField(30);
        txtroomno.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 560, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtroomno.setEditable(false);
        add(txtroomno);

        lbldoa = new JLabel(LayoutUtils.DATE_OF_ADMISSION_LABEL);
        lbldoa.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE,400, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoa);
        txtdoa = new JTextField(40);
        txtdoa.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 400, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtdoa.setEditable(false);
        add(txtdoa);
        lbldobformat2 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldobformat2.setBounds(810, 400, 160, 20);
        //add(lbldobformat2);

        lblcurrentproblem = new JLabel(LayoutUtils.CURRENT_PROBLEM_LABEL);
        lblcurrentproblem.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 440, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcurrentproblem);
        txtcurrentproblem = new TextArea();
        txtcurrentproblem.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 440, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        txtcurrentproblem.setEditable(false);
        add(txtcurrentproblem);

        lbldoctor = new JLabel(LayoutUtils.ATTENDING_DOCTOR_LABEL);
        lbldoctor.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 560, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoctor);
        choiceDoc = new Choice();
        choiceDoc.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 560, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceDoc.addItem(LayoutUtils.ATTENDING_DOCTOR_SELECT_LABEL);
        add(choiceDoc);

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
