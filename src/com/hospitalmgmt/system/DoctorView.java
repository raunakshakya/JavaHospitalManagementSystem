/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Doctor;
import com.hospitalmgmt.models.Patient;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.DBConnectionUtils;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
public class DoctorView extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    JLabel mainTitle, lblInsertDNo, lblsubTitle, lblfullname, lbladdress, lblcontact, lblgender, 
            lbldob, lbldateformat1, lblprofTitle, lblspecialization, lbldoctorid, lwork, 
            lblworkfrom, lblworkto, lbldoj, lbldateformat2, lblpatientlist;
    JTextField txtfullname, txtcontact, txtgender, txtdob, txtdoctorid, txtworkfrom, txtworkto, txtdoj;
    TextArea txtaddress, txtspecialization, txtpatientlist;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnSubmit, btnClear, bback;

    public DoctorView() {
        super(LayoutUtils.VIEW_DOCTOR_TITLE);

        mainTitle = new JLabel(messages.getString("label.doctor.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        lblInsertDNo = new JLabel(messages.getString("label.insert.doctor.id"));
        lblInsertDNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertDNo.setBounds(40, 70, 200, 25);
        add(lblInsertDNo);

        lbldoctorid = new JLabel(messages.getString("label.doctor.id"));
        lbldoctorid.setBounds(40, 100, 100, 25);
        add(lbldoctorid);
        txtdoctorid = new JTextField(30);
        txtdoctorid.setBounds(140, 100, 160, 25);
        add(txtdoctorid);

        btnSubmit = new JButton(messages.getString("common.search"));
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!txtdoctorid.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "First Enter the Doctor ID!!!");
                    return;
                }

                Integer id = Integer.parseInt(txtdoctorid.getText());
                Doctor doctor = Doctor.findById(id);
                if (doctor == null) {
                    doClearTheTextFields();
                    JOptionPane.showMessageDialog(null, "Doctor Reocrd Not Found!!!");
                }

                doFillTheTextFields(doctor);
                ArrayList<Patient> patients = Patient.findAllByDoctor(id);
                for (Patient patient : patients) {
                    txtpatientlist.append(patient.getFullName() + " ");
                    txtpatientlist.append("\n");
                }
            }
        });

        btnClear = new JButton(messages.getString("common.clear.all"));
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doClearTheTextFields();
            }
        });

        lblsubTitle = new JLabel(messages.getString("personal.information.title"));
        lblsubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblsubTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 160, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(lblsubTitle);

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
        cbm = new Checkbox("Male", cbmf, false);
        cbf = new Checkbox("Female", cbmf, false);
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
        lbldateformat1 = new JLabel(messages.getString("label.date.format"));
        lbldateformat1.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, 160, 25);
        add(lbldateformat1);

        //Professional Title...
        lblprofTitle = new JLabel(messages.getString("professional.information.title"));
        lblprofTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblprofTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 400, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(lblprofTitle);

        lblspecialization = new JLabel(messages.getString("label.specialization"));
        lblspecialization.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 450, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblspecialization);
        txtspecialization = new TextArea();
        txtspecialization.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 450, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        txtspecialization.setEditable(false);
        add(txtspecialization);

        lbldoj = new JLabel(messages.getString("label.date.of.join"));
        lbldoj.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 600, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 600, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtdoj.setEditable(false);
        add(txtdoj);
        lbldateformat2 = new JLabel(messages.getString("label.date.format"));
        lbldateformat2.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 630, 160, 20);
        add(lbldateformat2);

        lblworkfrom = new JLabel(messages.getString("label.shift.from"));
        lblworkfrom.setBounds(540, 450, 80, 25);
        add(lblworkfrom);
        txtworkfrom = new JTextField(30);
        txtworkfrom.setBounds(660, 450, 200, 25);
        txtworkfrom.setEditable(false);
        add(txtworkfrom);

        lblworkto = new JLabel(messages.getString("label.shift.to"));
        lblworkto.setBounds(540, 490, 80, 25);
        add(lblworkto);
        txtworkto = new JTextField(30);
        txtworkto.setBounds(660, 490, 200, 25);
        txtworkto.setEditable(false);
        add(txtworkto);

        lblpatientlist = new JLabel(messages.getString("label.patient.list"));
        lblpatientlist.setBounds(540, 530, 80, 25);
        add(lblpatientlist);

        txtpatientlist = new TextArea();
        txtpatientlist.setBounds(660, 530, 200, 100);
        add(txtpatientlist);

        setSize(LayoutUtils.INNER_WINDOW_WIDTH, LayoutUtils.INNER_WINDOW_HEIGHT);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(LayoutUtils.SUB_WINDOW_X_COORDINATE, LayoutUtils.SUB_WINDOW_Y_COORDINATE);
        setLayout(null);
    }

    public void doFillTheTextFields(Doctor doctor) {
        txtfullname.setText(doctor.getFullName());
        txtaddress.setText(doctor.getAddress());
        txtcontact.setText(doctor.getContact());
        txtspecialization.setText(doctor.getSpecialization());
        txtworkfrom.setText(doctor.getShiftFrom().toString());
        txtworkto.setText(doctor.getShiftTo().toString());
        txtdob.setText(doctor.getDateOfBirth().toString());
        txtdoj.setText(doctor.getDateOfJoin().toString());
        txtgender.setText(doctor.getGender().getName());
    }

    public void doClearTheTextFields() {
        txtdoctorid.setText("");
        txtfullname.setText("");
        txtaddress.setText("");
        txtcontact.setText("");
        txtworkfrom.setText("");
        txtworkto.setText("");
        txtspecialization.setText("");
        txtpatientlist.setText("");
        txtdob.setText("");
        txtdoj.setText("");
        txtgender.setText("");
    }

}
