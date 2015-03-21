/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmtsystem.project;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
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
public final class DoctorAdd extends JInternalFrame implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, subTitle, subprofTitle,
            lblfullname, lbladdress, lblcontact, lbldob, lbldobformat, lblgender,
            lblspec, lbldoj, lbldojformat, lblshiftfron, lblshiftto, lbltimeformat1, lbltimeformat2;
    JTextField txtfullname, txtcontact, txtdob, txtgender;
    JTextField txtdoj, txtshiftfrom, txtshiftto;
    TextArea txtaddress, txtSpec;
    JCheckBox chkboxStatus;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnAdd, btnClear;
    int doctorStatus;

    public DoctorAdd() {
        super("New Doctor Information");

        Container con = getContentPane();

        //Patient's Personal Information...
        mainTitle = new JLabel("Add Doctor Information");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(LayoutUtils.HEADING_X_COORDINATE, LayoutUtils.HEADING_Y_COORDINATE, LayoutUtils.HEADING_HORIZONTAL_LENGTH, LayoutUtils.HEADING_VERTICAL_LENGTH);
        add(mainTitle);

        subTitle = new JLabel("Personal Information");
        subTitle.setFont(new Font("Arial", Font.BOLD, 20));
        subTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.UPPER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subTitle);

        lblfullname = new JLabel("Full Name :");
        lblfullname.setBounds(100, 100, 70, 25);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(220, 100, 250, 25);
        add(txtfullname);

        lbladdress = new JLabel("Address :");
        lbladdress.setBounds(100, 140, 70, 25);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(220, 140, 250, 100);
        add(txtaddress);

        //Patient's Date Of Birth...
        lbldob = new JLabel("Date of Birth :");
        lbldob.setBounds(540, 100, 120, 25);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(670, 100, 120, 25);
        add(txtdob);
        lbldobformat = new JLabel("(dd-mm-yyyy)");
        lbldobformat.setBounds(800, 100, 100, 25);
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
        lblcontact.setBounds(540, 180, 50, 25);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(670, 180, 120, 25);
        add(txtcontact);

        //Professional Information...
        subprofTitle = new JLabel("Professional Information");
        subprofTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subprofTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.LOWER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subprofTitle);

        //Specialization...
        lblspec = new JLabel("Specialization:");
        lblspec.setBounds(100, 310, 100, 25);
        add(lblspec);
        txtSpec = new TextArea();
        txtSpec.setBounds(220, 310, 250, 100);
        add(txtSpec);

        chkboxStatus = new JCheckBox("Status");
        chkboxStatus.setBounds(220, 430, 100, 25);
        add(chkboxStatus);

        //Date of Join...
        lbldoj = new JLabel("Date Of Join :");
        lbldoj.setBounds(540, 310, 120, 25);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(670, 310, 120, 25);
        add(txtdoj);
        lbldojformat = new JLabel("(dd-mm-yyyy)");
        lbldojformat.setBounds(800, 310, 100, 25);
        add(lbldojformat);

        //Shift From...
        lblshiftfron = new JLabel("Shift From :");
        lblshiftfron.setBounds(540, 350, 100, 25);
        add(lblshiftfron);
        txtshiftfrom = new JTextField(100);
        txtshiftfrom.setBounds(670, 350, 120, 25);
        add(txtshiftfrom);
        lbltimeformat1 = new JLabel("(hh-mm-ss)");
        lbltimeformat1.setBounds(800, 350, 100, 25);
        add(lbltimeformat1);

        //Shift To...
        lblshiftto = new JLabel("Shift To :");
        lblshiftto.setBounds(540, 390, 100, 25);
        add(lblshiftto);
        txtshiftto = new JTextField(100);
        txtshiftto.setBounds(670, 390, 120, 25);
        add(txtshiftto);
        lbltimeformat2 = new JLabel("(hh-mm-ss)");
        lbltimeformat2.setBounds(800, 390, 100, 25);
        add(lbltimeformat2);

        //Button to submit information...
        btnAdd = new JButton("ADD");
        btnAdd.setBounds(330, 520, 100, 30);
        add(btnAdd);

        //Button to clear information...
        btnClear = new JButton("CLEAR");
        btnClear.setBounds(460, 520, 100, 30);
        add(btnClear);

        //Database Connection...
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/HospitalMgmtSystemDB", "postgres", "postgres");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
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
            txtSpec.setText("");
            txtdob.setText("");
            cbmf.setSelectedCheckbox(null);
            txtaddress.setText("");
            chkboxStatus.setSelected(false);
            txtshiftfrom.setText("");
            txtcontact.setText("");
            txtshiftto.setText("");
            txtdoj.setText("");

        }
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String fullname = txtfullname.getText().trim();
            String address = txtaddress.getText().trim();
            String contact = txtcontact.getText().trim();
            String specialization = txtSpec.getText().trim();
            String shiftto = txtshiftto.getText().trim();
            String shiftfrom = txtshiftfrom.getText().trim();
            String dob = txtdob.getText().trim();
            String doj = txtdoj.getText().trim();
            String gender = "";
            if (cbm.getState() == true) {
                gender = "M";
            }
            if (cbf.getState() == true) {
                gender = "F";
            }

            if (chkboxStatus.isSelected()) {
                doctorStatus = 1;
            } else {
                doctorStatus = 0;
            }

            if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || specialization.isEmpty() || shiftto.isEmpty()
                    || shiftfrom.isEmpty() || dob.isEmpty() || doj.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter all the field data correctly!!!");
            } else {
                try {
                    stmt = conn.prepareStatement("INSERT INTO doctor_table(doctor_fullname, doctor_address, "
                            + "doctor_contact, doctor_dateofbirth, doctor_dateofjoin, "
                            + "doctor_specialization, doctor_workshiftfrom, doctor_workshiftto, doctor_gender, doctor_isactive) VALUES('" + fullname + "', '" + address + "', '" + contact + "', '"
                            + dob + "', '" + doj + "', '" + specialization + "', '" + shiftfrom + "', '" + shiftto + "', '" + gender + "', '" + doctorStatus + "');");

                    int success = stmt.executeUpdate();
                    if (success > 0) {
                        JOptionPane.showMessageDialog(null, "New Doctor Has Been Added!!!", "Insert Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error in inserting Doctor Data!!!");
                }
            }
        }
    }
}
