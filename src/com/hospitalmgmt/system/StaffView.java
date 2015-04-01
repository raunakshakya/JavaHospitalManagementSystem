/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Staff;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.DBConnectionUtils;
import com.hospitalmgmt.utils.Gender;
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
import java.sql.SQLException;
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
public class StaffView extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

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

        btnSubmit = new JButton(messages.getString("common.search"));
        btnSubmit.setBounds(320, 98, 100, 30);
        add(btnSubmit);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtstaffid.getText().trim().equals("")) {
                    Integer staffId = Integer.parseInt(txtstaffid.getText());
                    Staff staff = Staff.findById(staffId);
                    if (staff == null) {
                        String fullName = staff.getFullName();
                        String address = staff.getAddress();
                        String contact = staff.getContact();
                        String gender = staff.getGender().getName();
                        String department = staff.getDepartment();
                        String post = staff.getPost();
                        String dateOfBirth = staff.getDateOfBirth().toString();
                        String dateOfJoin = staff.getDateOfJoin().toString();
                        String shiftFrom = staff.getShiftFrom().toString();
                        String shiftTo = staff.getShiftTo().toString();

                        txtfullname.setText(fullName);
                        txtaddress.setText(address);
                        txtcontact.setText(contact);
                        txtdepartment.setText(department);
                        txtworkfrom.setText(shiftFrom);
                        txtworkto.setText(shiftTo);
                        txtdob.setText(dateOfBirth);
                        txtdoj.setText(dateOfJoin);
                        txtgender.setText(gender);
                        txtpost.setText(post);
                    } else {
                        doClearTheTextFields();
                        JOptionPane.showMessageDialog(null, "Staff Record Not Found!!!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Staff ID!!!");
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

        lblsubTitle = new JLabel(messages.getString("personal.information.title"));
        lblsubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblsubTitle.setBounds(40, 150, 200, 30);
        add(lblsubTitle);

        lblfullname = new JLabel(messages.getString("label.full.name"));
        lblfullname.setBounds(60, 200, 70, 25);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(200, 200, 200, 25);
        txtfullname.setEditable(false);
        add(txtfullname);

        lbladdress = new JLabel(messages.getString("label.address"));
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 100);
        txtaddress.setEditable(false);
        add(txtaddress);

        lblcontact = new JLabel(messages.getString("label.contact"));
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(660, 200, 200, 25);
        txtcontact.setEditable(false);
        add(txtcontact);

        lblgender = new JLabel(messages.getString("label.gender"));
        lblgender.setBounds(540, 240, 60, 25);
        add(lblgender);
        cbmf = new CheckboxGroup();
        cbm = new Checkbox(Gender.MALE.getName(), cbmf, false);
        cbf = new Checkbox(Gender.FEMALE.getName(), cbmf, false);
        cbm.setBounds(660, 240, 60, 25);
        add(cbm);
        cbf.setBounds(720, 240, 60, 25);
        add(cbf);

        lbldob = new JLabel(messages.getString("label.date.of.birth"));
        lbldob.setBounds(540, 280, 120, 25);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(660, 280, 200, 25);
        txtdob.setEditable(false);
        add(txtdob);
        lbldf1 = new JLabel(messages.getString("label.date.format"));
        lbldf1.setBounds(780, 310, 100, 25);
        add(lbldf1);

        //Professional Title...
        lblprofTitle = new JLabel(messages.getString("professional.information.title"));
        lblprofTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblprofTitle.setBounds(40, 400, 300, 30);
        add(lblprofTitle);

        lbldepartment = new JLabel(messages.getString("label.department"));
        lbldepartment.setBounds(60, 450, 100, 25);
        add(lbldepartment);
        txtdepartment = new TextArea();
        txtdepartment.setBounds(200, 450, 200, 130);
        txtdepartment.setEditable(false);
        add(txtdepartment);

        lbldoj = new JLabel(messages.getString("label.date.of.join"));
        lbldoj.setBounds(60, 600, 100, 25);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(200, 600, 200, 25);
        txtdoj.setEditable(false);
        add(txtdoj);
        lbldf2 = new JLabel(messages.getString("label.date.format"));
        lbldf2.setBounds(320, 630, 100, 20);
        add(lbldf2);

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

        lblpost = new JLabel("Job Post");
        lblpost.setBounds(540, 530, 80, 25);
        add(lblpost);
        txtpost = new TextArea();
        txtpost.setBounds(660, 530, 200, 100);
        txtpost.setEditable(false);
        add(txtpost);

        setSize(LayoutUtils.INNER_WINDOW_WIDTH, LayoutUtils.INNER_WINDOW_HEIGHT);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(LayoutUtils.SUB_WINDOW_X_COORDINATE, LayoutUtils.SUB_WINDOW_Y_COORDINATE);
        setLayout(null);
    }

    public void doClearTheTextFields() {
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
