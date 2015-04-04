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
public class PatientView extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

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
        super(messages.getString("label.view.patient.information"));

        // PERSONAL INFORMATION
        mainTitle = new JLabel(messages.getString("label.view.patient.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 20);
        add(mainTitle);

        //Get the Patient Number...
        lblInsertPNo = new JLabel(messages.getString("label.insert.patient.no"));
        lblInsertPNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertPNo.setBounds(40, 70, 200, 25);
        add(lblInsertPNo);

        lblPatNo = new JLabel(messages.getString("label.patient.number"));
        lblPatNo.setBounds(40, 100, 150, 25);
        add(lblPatNo);

        txtpatno = new JTextField(30);
        txtpatno.setBounds(140, 100, 160, 25);
        add(txtpatno);

        btnSubmit = new JButton(messages.getString("common.search"));
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtpatno.getText().trim().equals("")) {
                    Integer patientId = Integer.parseInt(txtpatno.getText().trim());
                    Patient patient = Patient.findById(patientId);

                    String fullName = patient.getFullName();
                    String address = patient.getAddress();
                    String contact = patient.getContact();
                    String bloodgroup = patient.getBloodgroup().getName();
                    String history = patient.getHistory();
                    String dateOfBirth = patient.getDateOfBirth().toString();
                    String problem = patient.getCurrentProblem();
                    String room = patient.getRoomNumber().toString();
                    String dateOfJoin = patient.getDateOfAdmission().toString();
                    String gender = patient.getGender().getName();
                    Integer attendingDoctorId = patient.getAttendingDoctor();
                    Doctor doctor = Doctor.findById(attendingDoctorId);
                    
                    txtfullname.setText(fullName);
                    txtcontact.setText(contact);
                    txtdob.setText(dateOfBirth);
                    txtaddress.setText(address);
                    txthistory.setText(history);
                    txtcurrentproblem.setText(problem);
                    txtbloodgroup.setText(bloodgroup);
                    txtroomno.setText(room);
                    txtdoa.setText(dateOfJoin);
                    txtgender.setText(gender);
                    txtdoctor.setText(doctor.getFullName());
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID!!!");
                }
            }
        });

        btnClear = new JButton(messages.getString("common.clear.all"));
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClearTheTextFields();
            }
        });

        //Personal Information Title...
        subTitle = new JLabel(messages.getString("personal.information.title"));
        subTitle.setFont(new Font("Arial", Font.BOLD, 20));
        subTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 160, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subTitle);

        lblfullname = new JLabel(messages.getString("label.full.name"));
        lblfullname.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 200, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 200, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtfullname.setEditable(false);
        add(txtfullname);

        lbladdress = new JLabel(messages.getString("label.address"));
        lbladdress.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 240, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 240, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, 100);
        txtaddress.setEditable(false);
        add(txtaddress);

        lblcontact = new JLabel(messages.getString("label.contact"));
        lblcontact.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 200, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 200, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtcontact.setEditable(false);
        add(txtcontact);

        lblgender = new JLabel(messages.getString("label.gender"));
        lblgender.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 240, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblgender);
        cbmf = new CheckboxGroup();
        cbm = new Checkbox(Gender.MALE.getName(), cbmf, false);
        cbf = new Checkbox(Gender.FEMALE.getName(), cbmf, false);
        cbm.setBounds(670, 240, 70, 25);
        add(cbm);
        cbf.setBounds(740, 240, 80, 25);
        add(cbf);

        lbldob = new JLabel(messages.getString("label.date.of.birth"));
        lbldob.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 280, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 280, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtdob.setEditable(false);
        add(txtdob);
        lbldobformat1 = new JLabel(messages.getString("label.date.format"));
        lbldobformat1.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, 160, 25);
        add(lbldobformat1);

        //Medical Information Title...
        submedTitle = new JLabel(messages.getString("medical.information.title"));
        submedTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submedTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 360, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(submedTitle);

        lblbloodgroup = new JLabel(messages.getString("label.blood.group"));
        lblbloodgroup.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 400, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblbloodgroup);
        choiceBG = new Choice();
        choiceBG.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 400, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceBG.addItem(messages.getString("label.select.blood.group"));
        for (BloodGroup bloodgroup : BloodGroup.values()) {
            choiceBG.addItem(bloodgroup.getName());
        }
        add(choiceBG);

        lblhistory = new JLabel(messages.getString("label.history"));
        lblhistory.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 440, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblhistory);
        txthistory = new TextArea();
        txthistory.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 440, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        txthistory.setEditable(false);
        add(txthistory);

        lblroomno = new JLabel(messages.getString("label.room.number"));
        lblroomno.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 560, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblroomno);
        txtroomno = new JTextField(30);
        txtroomno.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 560, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtroomno.setEditable(false);
        add(txtroomno);

        lbldoa = new JLabel(messages.getString("label.date.of.admission"));
        lbldoa.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 400, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoa);
        txtdoa = new JTextField(40);
        txtdoa.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 400, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtdoa.setEditable(false);
        add(txtdoa);
        lbldobformat2 = new JLabel(messages.getString("label.date.format"));
        lbldobformat2.setBounds(810, 400, 160, 20);
        //add(lbldobformat2);

        lblcurrentproblem = new JLabel(messages.getString("label.problem"));
        lblcurrentproblem.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 440, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcurrentproblem);
        txtcurrentproblem = new TextArea();
        txtcurrentproblem.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 440, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        txtcurrentproblem.setEditable(false);
        add(txtcurrentproblem);

        lbldoctor = new JLabel(messages.getString("label.attending.doctor"));
        lbldoctor.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 560, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoctor);
        choiceDoc = new Choice();
        choiceDoc.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 560, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        choiceDoc.addItem(messages.getString("label.attending.doctor.select"));
        add(choiceDoc);

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
        txtpatno.setText("");
        txtroomno.setText("");
        txtdoa.setText("");
        txtgender.setText("");
        txtbloodgroup.setText("");
    }

}
