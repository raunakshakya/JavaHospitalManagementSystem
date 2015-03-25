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
public class StaffView extends JInternalFrame {

    Connection conn = null;
    PreparedStatement stmt1 = null;
    PreparedStatement stmt2 = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;

    JLabel mainTitle, lblInsertSNo, lblsubTitle, lblfullname, lbladdress, lblcontact, lblgender, lbldob, lbldf1,
            lblprofTitle, lbldepartment, lblstaffid, lwork, lblworkfrom, lblworkto, lbldoj, lbldf2, lblpost;
    JTextField txtfullname, txtcontact, txtgender, txtdob, txtstaffid, txtworkfrom, txtworkto, txtdoj;
    TextArea txtaddress, txtdepartment, txtpost;
    CheckboxGroup cbmf;
    Checkbox cbm, cbf;
    JButton btnSubmit, btnClear;

    public StaffView() {
        super("View Staff Information");

        mainTitle = new JLabel("Staff Information");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        lblInsertSNo = new JLabel("Insert Staff Identity :");
        lblInsertSNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertSNo.setBounds(40, 70, 160, 25);
        add(lblInsertSNo);

        lblstaffid = new JLabel("Staff ID :");
        lblstaffid.setBounds(40, 100, 100, 25);
        add(lblstaffid);

        txtstaffid = new JTextField(30);
        txtstaffid.setBounds(140, 100, 160, 25);
        add(txtstaffid);

        btnSubmit = new JButton(LayoutUtils.SEARCH_BUTTON_LABEL);
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);

        btnClear = new JButton(LayoutUtils.CLEAR_ALL_BUTTON_LABEL);
        btnClear.setBounds(430, 98, 100, 30);
        add(btnClear);

        lblsubTitle = new JLabel(LayoutUtils.PERSONAL_INFORMATION_LABEL);
        lblsubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblsubTitle.setBounds(40, 150, 200, 30);
        add(lblsubTitle);

        lblfullname = new JLabel(LayoutUtils.FULL_NAME_LABEL);
        lblfullname.setBounds(60, 200, 70, 25);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(200, 200, 200, 25);
        txtfullname.setEditable(false);
        add(txtfullname);

        lbladdress = new JLabel(LayoutUtils.ADDRESS_LABEL);
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 100);
        txtaddress.setEditable(false);
        add(txtaddress);

        lblcontact = new JLabel(LayoutUtils.CONTACT_LABEL);
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(660, 200, 200, 25);
        txtcontact.setEditable(false);
        add(txtcontact);

        lblgender = new JLabel(LayoutUtils.GENDER_LABEL);
        lblgender.setBounds(540, 240, 60, 25);
        add(lblgender);
        cbmf = new CheckboxGroup();
        cbm = new Checkbox(Gender.MALE.getName(), cbmf, false);
        cbf = new Checkbox(Gender.FEMALE.getName(), cbmf, false);
        cbm.setBounds(660, 240, 60, 25);
        add(cbm);
        cbf.setBounds(720, 240, 60, 25);
        add(cbf);

        lbldob = new JLabel(LayoutUtils.DATE_OF_BIRTH_LABEL);
        lbldob.setBounds(540, 280, 120, 25);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(660, 280, 200, 25);
        txtdob.setEditable(false);
        add(txtdob);
        lbldf1 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldf1.setBounds(780, 310, 100, 25);
        add(lbldf1);

        //Professional Title...
        lblprofTitle = new JLabel(LayoutUtils.PROFESSIONAL_INFORMATION_LABEL);
        lblprofTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblprofTitle.setBounds(40, 400, 300, 30);
        add(lblprofTitle);

        lbldepartment = new JLabel(LayoutUtils.DEPARTMENT_LABEL);
        lbldepartment.setBounds(60, 450, 100, 25);
        add(lbldepartment);
        txtdepartment = new TextArea();
        txtdepartment.setBounds(200, 450, 200, 130);
        txtdepartment.setEditable(false);
        add(txtdepartment);

        lbldoj = new JLabel(LayoutUtils.DATE_OF_JOIN_LABEL);
        lbldoj.setBounds(60, 600, 100, 25);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(200, 600, 200, 25);
        txtdoj.setEditable(false);
        add(txtdoj);
        lbldf2 = new JLabel(LayoutUtils.DATE_FORMAT_LABEL);
        lbldf2.setBounds(320, 630, 100, 20);
        add(lbldf2);

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

        lblpost = new JLabel("Job Post");
        lblpost.setBounds(540, 530, 80, 25);
        add(lblpost);
        txtpost = new TextArea();
        txtpost.setBounds(660, 530, 200, 100);
        txtpost.setEditable(false);
        add(txtpost);

        //Database Connection...
        try {
            Class.forName(DBConnectionUtils.DB_DRIVER);
            conn = DriverManager.getConnection(DBConnectionUtils.DB_CONNECTION_URL, DBConnectionUtils.DB_USERNAME, DBConnectionUtils.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
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
            txtstaffid.setText("");
            txtfullname.setText("");
            txtaddress.setText("");
            txtcontact.setText("");
            txtworkfrom.setText("");
            txtworkto.setText("");
            txtdepartment.setText("");
            txtpost.setText("");
            txtdoj.setText("");
            txtdob.setText("");
            txtgender.setText("");
        }
    }

    public void actionPerformed(ActionEvent ae) {
    }

    class submit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (!txtstaffid.getText().trim().equals("")) {
                    Integer num = Integer.parseInt(txtstaffid.getText());
                    String name, addr, contact, dept, post, workf, workt, gender, dob, doj;

                    stmt1 = conn.prepareStatement("SELECT * FROM staff_table WHERE staff_id=?");
                    stmt1.setInt(1, num);
                    ResultSet rs1 = stmt1.executeQuery();

                    if (rs1.next()) {
                        name = rs1.getString("staff_fullname");
                        addr = rs1.getString("staff_address");
                        contact = rs1.getString("staff_contact");
                        gender = rs1.getString("staff_gender");
                        dob = rs1.getString("staff_dateofbirth");
                        doj = rs1.getString("staff_dateofjoin");
                        dept = rs1.getString("staff_department");
                        workf = rs1.getString("staff_workshiftfrom");
                        workt = rs1.getString("staff_workshiftto");
                        post = rs1.getString("staff_post");

                        txtfullname.setText(name);
                        txtaddress.setText(addr);
                        txtcontact.setText(contact);
                        txtdepartment.setText(dept);
                        txtworkfrom.setText(workf);
                        txtworkto.setText(workt);
                        txtdob.setText(dob);
                        txtdoj.setText(doj);
                        txtgender.setText(gender);
                        txtpost.setText(post);

                    } else {
                        txtstaffid.setText("");
                        txtfullname.setText("");
                        txtaddress.setText("");
                        txtcontact.setText("");
                        txtworkfrom.setText("");
                        txtworkto.setText("");
                        txtdepartment.setText("");
                        txtpost.setText("");
                        txtdoj.setText("");
                        txtdob.setText("");
                        txtgender.setText("");
                        JOptionPane.showMessageDialog(null, "Staff Record Not Found!!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Staff ID!!!");
                }
            } catch (SQLException sq) {
                JOptionPane.showMessageDialog(null, "Error in retrieving Staff Data!!!");
            }
        }
    }

}
