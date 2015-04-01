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
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Raunak Shakya
 */
public class PatientModify extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, subTitle, submedTitle, lblInsertPNo, lblPatNo, lblfullname, lbladdress, lblcontact,
            lblMedTitle, lblbloodgroup, lbldob, lblhistory, lblcurrentproblem, lblpatientno, lblroomno,
            lbldoa, lblgender, lbldf1, lbldf2, lbldoctor;
    JTextField txtpatno, txtfullname, txtcontact, txtdob, txtroomno, txtdoa, txtdoctor;
    TextArea txtaddress, txthistory, txtcurrentproblem;
    JButton btnSubmit, btnClear, btnModify;
    Choice choiceBG;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;

    public PatientModify() {
        super(messages.getString("label.modify.patient.information"));

        //Main Title
        mainTitle = new JLabel(messages.getString("label.modify.patient.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        //Get the Patient Number...
        lblInsertPNo = new JLabel(messages.getString("label.insert.patient.no"));
        lblInsertPNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertPNo.setBounds(40, 70, 160, 25);
        add(lblInsertPNo);

        lblPatNo = new JLabel("Patient No.:");
        lblPatNo.setBounds(40, 100, 150, 25);
        add(lblPatNo);
        txtpatno = new JTextField(30);
        txtpatno.setBounds(140, 100, 160, 25);
        add(txtpatno);

        btnSubmit = new JButton(messages.getString("common.search"));
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton(messages.getString("common.clear.all"));
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClearTheTextFields();
            }
        });

        btnModify = new JButton("UPDATE PATIENT");
        btnModify.setBounds(540, 98, 140, 30);
        add(btnModify);

        //Personal Information Title...
        subTitle = new JLabel(messages.getString("personal.information.title"));
        subTitle.setFont(new Font("Arial", Font.BOLD, 20));
        subTitle.setBounds(40, 160, 200, 30);
        add(subTitle);

        lblfullname = new JLabel(messages.getString("label.full.name"));
        lblfullname.setBounds(60, 200, 80, 25);
        add(lblfullname);

        txtfullname = new JTextField(30);
        txtfullname.setBounds(200, 200, 200, 25);
        add(txtfullname);

        lbladdress = new JLabel(messages.getString("label.address"));
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);

        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 90);
        add(txtaddress);

        lblcontact = new JLabel(messages.getString("label.contact"));
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);

        txtcontact = new JTextField(30);
        txtcontact.setBounds(680, 200, 200, 25);
        add(txtcontact);

        lblgender = new JLabel(messages.getString("label.gender"));
        lblgender.setBounds(540, 240, 60, 25);
        add(lblgender);

        cbmf = new CheckboxGroup();
        cbm = new Checkbox(Gender.MALE.getName(), cbmf, false);
        cbf = new Checkbox(Gender.FEMALE.getName(), cbmf, false);
        cbm.setBounds(680, 240, 60, 25);
        add(cbm);
        cbf.setBounds(740, 240, 60, 25);
        add(cbf);

        lbldob = new JLabel(messages.getString("label.date.of.birth"));
        lbldob.setBounds(540, 280, 120, 25);
        add(lbldob);

        txtdob = new JTextField(15);
        txtdob.setBounds(680, 280, 200, 25);
        add(txtdob);

        lbldf1 = new JLabel(messages.getString("label.date.format"));
        lbldf1.setBounds(800, 310, 100, 25);
        add(lbldf1);

        //Medical Information Title...
        submedTitle = new JLabel(messages.getString("medical.information.title"));
        submedTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submedTitle.setBounds(40, 360, 200, 15);
        add(submedTitle);

        lblbloodgroup = new JLabel(messages.getString("label.blood.group"));
        lblbloodgroup.setBounds(60, 400, 80, 25);
        add(lblbloodgroup);

        choiceBG = new Choice();
        choiceBG.setBounds(200, 400, 80, 25);
        for (BloodGroup bloodgroup : BloodGroup.values()) {
            choiceBG.addItem(bloodgroup.getName());
        }
        add(choiceBG);

        lblhistory = new JLabel(messages.getString("label.history"));
        lblhistory.setBounds(60, 440, 80, 25);
        add(lblhistory);

        txthistory = new TextArea();
        txthistory.setBounds(200, 440, 200, 100);
        add(txthistory);

        lblroomno = new JLabel(messages.getString("label.room.number"));
        lblroomno.setBounds(60, 560, 80, 25);
        add(lblroomno);

        txtroomno = new JTextField(30);
        txtroomno.setBounds(200, 560, 200, 25);
        add(txtroomno);

        lbldoa = new JLabel(messages.getString("label.date.of.admission"));
        lbldoa.setBounds(540, 400, 120, 25);
        add(lbldoa);

        txtdoa = new JTextField(40);
        txtdoa.setBounds(680, 400, 125, 20);
        add(txtdoa);

        lbldf2 = new JLabel(messages.getString("label.date.format"));
        lbldf2.setBounds(810, 400, 100, 20);
        add(lbldf2);

        lblcurrentproblem = new JLabel(messages.getString("label.problem"));
        lblcurrentproblem.setBounds(540, 440, 100, 25);
        add(lblcurrentproblem);

        txtcurrentproblem = new TextArea();
        txtcurrentproblem.setBounds(680, 440, 200, 100);
        add(txtcurrentproblem);

        lbldoctor = new JLabel(messages.getString("label.attending.doctor"));
        lbldoctor.setBounds(540, 560, 100, 25);
        add(lbldoctor);

        txtdoctor = new JTextField(100);
        txtdoctor.setBounds(680, 560, 200, 25);
        add(txtdoctor);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtpatno.getText().isEmpty()) {
                    Integer patientId = Integer.parseInt(txtpatno.getText());
                    Patient patient = Patient.findById(patientId);
                    txtfullname.setText(patient.getFullName());
                    txtcontact.setText(patient.getContact());
                    txtdob.setText(patient.getDateOfBirth().toString());
                    txtaddress.setText(patient.getAddress());
                    txthistory.setText(patient.getHistory());
                    txtcurrentproblem.setText(patient.getCurrentProblem());
                    choiceBG.select(patient.getBloodgroup().getName());
                    txtroomno.setText(patient.getRoomNumber().toString());
                    txtdoa.setText(patient.getDateOfAdmission().toString());
                    txtdoctor.setText(patient.getAttendingDoctor().getFullName());
                    switch (patient.getGender().getName()) {
                        case "Male":
                            cbm.setState(true);
                            break;
                        case "Female":
                            cbf.setState(true);
                            break;
                        default:
                            throw new RuntimeException("Gender is not correct");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID......");
                }
            }
        });

        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer patientId = Integer.parseInt(txtpatno.getText());
                if (patientId == null) {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID...");
                } else {
                    Patient patient = Patient.findById(patientId);
                    Doctor doctor = patient.getAttendingDoctor();

                    String fullName = txtfullname.getText().trim();
                    String address = txtaddress.getText().trim();
                    String contact = txtcontact.getText().trim();
                    String bloodgroup = choiceBG.getSelectedItem().trim();
                    String history = txthistory.getText().trim();
                    String dateOfBirth = txtdob.getText().trim();
                    String problem = txtcurrentproblem.getText().trim();
                    String roomNumber = txtroomno.getText().trim();
                    String dateOfJoin = txtdoa.getText().trim();
                    String gender = (cbm.getState() == true) ? "Male" : ((cbf.getState() == true) ? "Female" : null);

                    HashMap patientDto = new HashMap();
                    patientDto.put("fullName", fullName);
                    patientDto.put("address", address);
                    patientDto.put("contact", contact);
                    patientDto.put("gender", gender);
                    patientDto.put("bloodgroup", bloodgroup);
                    patientDto.put("dateOfBirth", dateOfBirth);
                    patientDto.put("dateOfJoin", dateOfJoin);
                    patientDto.put("currentproblem", problem);
                    patientDto.put("history", history);
                    patientDto.put("roomno", roomNumber);
                    patientDto.put("doctorid", doctor.getId());

                    Patient.update(patient.getId(), patientDto);
                }
            }
        });

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
        txtcontact.setText("");
        txtdob.setText("");
        txtaddress.setText("");
        txthistory.setText("");
        txtcurrentproblem.setText("");
        txtcontact.setText("");
        txtroomno.setText("");
        txtdoa.setText("");
        txtdoctor.setText("");
        txtpatno.setText("");
        cbmf.setSelectedCheckbox(null);
    }

}
