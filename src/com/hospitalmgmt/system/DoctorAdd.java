/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.DBConnectionUtils;
import com.hospitalmgmt.utils.Gender;
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
            lblfullname, lbladdress, lblcontact, lbldob, lbldobformat, lblgender, lblstatus,
            lblspec, lbldoj, lbldojformat, lblshiftfrom, lblshiftto, lbltimeformat1, lbltimeformat2;
    JTextField txtfullname, txtcontact, txtdob, txtgender;
    JTextField txtdoj, txtshiftfrom, txtshiftto;
    TextArea txtaddress, txtSpec;
    JCheckBox chkboxStatus;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnAdd, btnClear;
    int doctorStatus;

    public DoctorAdd() {
        super(LayoutUtils.NEW_DOCTOR_TITLE);

        Container con = getContentPane();

        //Patient's Personal Information...
        mainTitle = new JLabel(LayoutUtils.ADD_DOCTOR_TITLE);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(LayoutUtils.HEADING_X_COORDINATE, LayoutUtils.HEADING_Y_COORDINATE, LayoutUtils.HEADING_HORIZONTAL_LENGTH, LayoutUtils.HEADING_VERTICAL_LENGTH);
        add(mainTitle);

        subTitle = new JLabel(LayoutUtils.PERSONAL_INFORMATION_LABEL);
        subTitle.setFont(new Font("Arial", Font.BOLD, 20));
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
        txtaddress.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 140, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
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
        cbm = new Checkbox(Gender.MALE.getName(), cbmf, false);
        cbf = new Checkbox(Gender.FEMALE.getName(), cbmf, false);
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

        //Professional Information...
        subprofTitle = new JLabel(LayoutUtils.PROFESSIONAL_INFORMATION_LABEL);
        subprofTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subprofTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.LOWER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subprofTitle);

        //Specialization...
        lblspec = new JLabel(LayoutUtils.SPECIALIZATION_LABEL);
        lblspec.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblspec);
        txtSpec = new TextArea();
        txtSpec.setBounds(220, 310, 250, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        add(txtSpec);
        
        //Status...
        lblstatus = new JLabel(LayoutUtils.STATUS_LABEL);
        lblstatus.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 420, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblstatus);
        chkboxStatus = new JCheckBox();
        chkboxStatus.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 420, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(chkboxStatus);

        //Date of Join...
        lbldoj = new JLabel(LayoutUtils.DATE_OF_JOIN_LABEL);
        lbldoj.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdoj);
        lbldojformat = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldojformat.setBounds(800, 310, 100, 25);
        //add(lbldojformat);

        //Shift From...
        lblshiftfrom = new JLabel(LayoutUtils.SHIFT_FROM_LABEL);
        lblshiftfrom.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftfrom);
        txtshiftfrom = new JTextField(100);
        txtshiftfrom.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftfrom);
        lbltimeformat1 = new JLabel(LayoutUtils.TIME_FORMAT_LABEL);
        lbltimeformat1.setBounds(800, 350, 100, 25);
        //add(lbltimeformat1);

        //Shift To...
        lblshiftto = new JLabel(LayoutUtils.SHIFT_TO_LABEL);
        lblshiftto.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 390, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftto);
        txtshiftto = new JTextField(100);
        txtshiftto.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 390, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftto);
        lbltimeformat2 = new JLabel(LayoutUtils.TIME_FORMAT_LABEL);
        lbltimeformat2.setBounds(800, 390, 100, 25);
        //add(lbltimeformat2);

        //Button to submit information...
        btnAdd = new JButton(LayoutUtils.ADD_BUTTON_LABEL);
        btnAdd.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnAdd);

        //Button to clear information...
        btnClear = new JButton(LayoutUtils.CLEAR_BUTTON_LABEL);
        btnClear.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE + 120, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnClear);

        //Database Connection...
        try {
            Class.forName(DBConnectionUtils.DB_DRIVER);
            conn = DriverManager.getConnection(DBConnectionUtils.DB_CONNECTION_URL, DBConnectionUtils.DB_USERNAME, DBConnectionUtils.DB_PASSWORD);
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
