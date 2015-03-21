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
public final class StaffAdd extends JInternalFrame implements ActionListener {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    JLabel mainTitle, subTitle, subprofTitle,
            lblfullname, lbladdress, lblcontact, lbldob, lbldobformat, lblgender,
            lbldept, lblstatus, lblpost, lbldoj, lbldojformat, lblshiftfron, lblshiftto;
    JTextField txtfullname, txtcontact, txtdob, txtgender;
    JTextField txtDept, txtpost, txtdoj, txtshiftfrom, txtshiftto;
    TextArea txtaddress;
    JCheckBox chkboxStatus;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnAdd, btnClear;
    int staffStatus;

    public StaffAdd() {
        super(LayoutUtils.NEW_STAFF_TITLE);

        Container con = getContentPane();

        //Patient's Personal Information...
        mainTitle = new JLabel(LayoutUtils.ADD_STAFF_TITLE);
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

        //Professional Information...
        subprofTitle = new JLabel(LayoutUtils.PROFESSIONAL_INFORMATION_LABEL);
        subprofTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subprofTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.LOWER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subprofTitle);

        //Department...
        lbldept = new JLabel(LayoutUtils.DEPARTMENT_LABEL);
        lbldept.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldept);
        txtDept = new JTextField(30);
        txtDept.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtDept);

        //Post...
        lblpost = new JLabel(LayoutUtils.POST_LABEL);
        lblpost.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblpost);
        txtpost = new JTextField(30);
        txtpost.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtpost);

        //Status...
        lblstatus = new JLabel(LayoutUtils.STATUS_LABEL);
        lblstatus.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 390, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblstatus);
        chkboxStatus = new JCheckBox();
        chkboxStatus.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 390, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(chkboxStatus);
        
        //Shift From...
        lblshiftfron = new JLabel(LayoutUtils.SHIFT_FROM_LABEL);
        lblshiftfron.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftfron);
        txtshiftfrom = new JTextField(100);
        txtshiftfrom.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftfrom);

        //Shift To...
        lblshiftto = new JLabel(LayoutUtils.SHIFT_TO_LABEL);
        lblshiftto.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftto);
        txtshiftto = new JTextField(100);
        txtshiftto.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftto);

        //Date of Join...
        lbldoj = new JLabel(LayoutUtils.DATE_OF_JOIN_LABEL);
        lbldoj.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 390, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 390, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdoj);
        lbldojformat = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldojformat.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 410, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(lbldojformat);
        
        //Button to submit information...
        btnAdd = new JButton("ADD");
        btnAdd.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnAdd);

        //Button to clear information...
        btnClear = new JButton("CLEAR");
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
            txtDept.setText("");
            txtdob.setText("");
            cbmf.setSelectedCheckbox(null);
            txtaddress.setText("");
            txtpost.setText("");
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
            String department = txtDept.getText().trim();
            String post = txtpost.getText().trim();
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
                staffStatus = 1;
            } else {
                staffStatus = 0;
            }

            if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || department.isEmpty() || post.isEmpty() 
                    || shiftto.isEmpty() || shiftfrom.isEmpty() || dob.isEmpty() || doj.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter all the field data correctly!!!");
            } else {
                try {
                    stmt = conn.prepareStatement("INSERT INTO staff_table(staff_fullname, staff_address, "
                            + "staff_contact, staff_dateofbirth, staff_dateofjoin, "
                            + "staff_department, staff_post, staff_workshiftfrom, staff_workshiftto, staff_gender, staff_isactive) VALUES('" + fullname + "', '" + address + "', '" + contact + "', '"
                            + dob + "', '" + doj + "', '" + department + "', '" + post + "', '" + shiftfrom + "', '" + shiftto + "', '" + gender + "', '" + staffStatus + "');");

                    int success = stmt.executeUpdate();
                    if (success > 0) {
                        JOptionPane.showMessageDialog(new JFrame(), "New Staff Has Been Added!!!", "Insert Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error in inserting Doctor Data!!!");
                }
            }
        }
    }

}
