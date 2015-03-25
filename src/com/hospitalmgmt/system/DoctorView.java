/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.DBConnectionUtils;
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

    Connection conn = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;

    JLabel mainTitle, lblInsertDNo, lblsubTitle, lblfullname, lbladdress, lblcontact, lblgender, lbldob, lbldateformat1,
            lblprofTitle, lblspecialization, lbldoctorid, lwork, lblworkfrom, lblworkto, lbldoj, lbldateformat2, lblpatientlist;
    JTextField txtfullname, txtcontact, txtgender, txtdob, txtdoctorid, txtworkfrom, txtworkto, txtdoj;
    TextArea txtaddress, txtspecialization, txtpatientlist;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnSubmit, btnClear, bback;

    public DoctorView() {
        super(LayoutUtils.VIEW_DOCTOR_TITLE);

        mainTitle = new JLabel(LayoutUtils.DOCTOR_INFORMATION_LABEL);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        lblInsertDNo = new JLabel(LayoutUtils.INSERT_DOCTOR_ID_LABEL);
        lblInsertDNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertDNo.setBounds(40, 70, 200, 25);
        add(lblInsertDNo);

        lbldoctorid = new JLabel(LayoutUtils.DOCTOR_ID_LABEL);
        lbldoctorid.setBounds(40, 100, 100, 25);
        add(lbldoctorid);
        txtdoctorid = new JTextField(30);
        txtdoctorid.setBounds(140, 100, 160, 25);
        add(txtdoctorid);

        btnSubmit = new JButton(LayoutUtils.SEARCH_BUTTON_LABEL);
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton(LayoutUtils.CLEAR_ALL_BUTTON_LABEL);
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);

        lblsubTitle = new JLabel(LayoutUtils.PERSONAL_INFORMATION_LABEL);
        lblsubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblsubTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 160, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(lblsubTitle);

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
        cbm = new Checkbox("Male", cbmf, false);
        cbf = new Checkbox("Female", cbmf, false);
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
        lbldateformat1 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldateformat1.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, 160, 25);
        add(lbldateformat1);

        //Professional Title...
        lblprofTitle = new JLabel(LayoutUtils.PROFESSIONAL_INFORMATION_LABEL);
        lblprofTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblprofTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, 400, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(lblprofTitle);

        lblspecialization = new JLabel(LayoutUtils.SPECIALIZATION_LABEL);
        lblspecialization.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 450, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblspecialization);
        txtspecialization = new TextArea();
        txtspecialization.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 450, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        txtspecialization.setEditable(false);
        add(txtspecialization);

        lbldoj = new JLabel(LayoutUtils.DATE_OF_JOIN_LABEL);
        lbldoj.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 600, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 600, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        txtdoj.setEditable(false);
        add(txtdoj);
        lbldateformat2 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldateformat2.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 630, 160, 20);
        add(lbldateformat2);

        lblworkfrom = new JLabel(LayoutUtils.SHIFT_FROM_LABEL);
        lblworkfrom.setBounds(540, 450, 80, 25);
        add(lblworkfrom);
        txtworkfrom = new JTextField(30);
        txtworkfrom.setBounds(660, 450, 200, 25);
        txtworkfrom.setEditable(false);
        add(txtworkfrom);

        lblworkto = new JLabel(LayoutUtils.SHIFT_TO_LABEL);
        lblworkto.setBounds(540, 490, 80, 25);
        add(lblworkto);
        txtworkto = new JTextField(30);
        txtworkto.setBounds(660, 490, 200, 25);
        txtworkto.setEditable(false);
        add(txtworkto);

        lblpatientlist = new JLabel(LayoutUtils.PATIENT_LIST_TITLE);
        lblpatientlist.setBounds(540, 530, 80, 25);
        add(lblpatientlist);

        txtpatientlist = new TextArea();
        txtpatientlist.setBounds(660, 530, 200, 100);
        add(txtpatientlist);

        //Database Connection...
        try {
            Class.forName(DBConnectionUtils.DB_DRIVER);
            conn = DriverManager.getConnection(DBConnectionUtils.DB_CONNECTION_URL, DBConnectionUtils.DB_USERNAME, DBConnectionUtils.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the Database!!!");
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

    class clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
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

    public void actionPerformed(ActionEvent ae) {
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (!txtdoctorid.getText().trim().equals("")) {
                    Integer num = Integer.parseInt(txtdoctorid.getText());
                    String name, addr, contact, spec, workf, workt, gender, dob, doj;

                    stmt1 = conn.prepareStatement("SELECT * FROM doctor_table WHERE doctor_id=?");
                    stmt1.setInt(1, num);
                    ResultSet rs1 = stmt1.executeQuery();

                    if (rs1.next()) {
                        name = rs1.getString("doctor_fullname");
                        addr = rs1.getString("doctor_address");
                        contact = rs1.getString("doctor_contact");
                        gender = rs1.getString("doctor_gender");
                        dob = rs1.getString("doctor_dateofbirth");
                        doj = rs1.getString("doctor_dateofjoin");
                        spec = rs1.getString("doctor_specialization");
                        workf = rs1.getString("doctor_workshiftfrom");
                        workt = rs1.getString("doctor_workshiftto");

                        txtfullname.setText(name);
                        txtaddress.setText(addr);
                        txtcontact.setText(contact);
                        txtspecialization.setText(spec);
                        txtworkfrom.setText(workf);
                        txtworkto.setText(workt);
                        txtdob.setText(dob);
                        txtdoj.setText(doj);
                        txtgender.setText(gender);

                        stmt2 = conn.prepareStatement("SELECT patient_id, patient_fullname FROM patient_table WHERE patient_doctorid=?");
                        stmt2.setInt(1, num);
                        rs2 = stmt2.executeQuery();
                        ResultSetMetaData rsmt = rs2.getMetaData();
                        int ctr = rsmt.getColumnCount();
                        while (rs2.next()) {
                            for (int i = 1; i <= ctr; i++) {
                                txtpatientlist.append(rs2.getString(i) + " ");
                            }
                            txtpatientlist.append("\n");
                        }
                    } else {
                        txtdoctorid.setText("");
                        txtfullname.setText("");
                        txtaddress.setText("");
                        txtcontact.setText("");
                        txtworkfrom.setText("");
                        txtworkto.setText("");
                        txtspecialization.setText("");
                        txtpatientlist.setText("");
                        JOptionPane.showMessageDialog(null, "Doctor Reocrd Not Found!!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Doctor ID!!!");
                }
            } catch (SQLException sq) {
                JOptionPane.showMessageDialog(null, "Error in retrieving Doctor Data!!!");
            }
        }
    }

}
