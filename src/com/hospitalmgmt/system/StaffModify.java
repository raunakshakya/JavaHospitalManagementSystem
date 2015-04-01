/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Staff;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.Gender;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
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
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Raunak Shakya
 */
public class StaffModify extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    JLabel mainTitle, lblInsertSNo, lblsubTitle, lblfullname, lbladdress, lblcontact, lblgender, lbldob, lbldf1,
            lblprofTitle, lbldepartment, lblstaffid, lwork, lblworkfrom, lblworkto, lbldoj, lbldf2, lblpost;
    JTextField txtfullname, txtcontact, txtgender, txtdob, txtstaffid, txtworkfrom, txtworkto, txtdoj;
    TextArea txtaddress, txtdepartment, txtpost;
    JButton btnSubmit, btnClear, btnModify;
    Checkbox cbm, cbf;
    CheckboxGroup cbmf;

    public StaffModify() {
        super(LayoutUtils.MODIFY_STAFF_TITLE);

        mainTitle = new JLabel(messages.getString("label.staff.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(350, 25, 400, 30);
        add(mainTitle);

        lblInsertSNo = new JLabel(messages.getString("label.insert.staff.id"));
        lblInsertSNo.setFont(new Font("Arial", Font.BOLD, 14));
        lblInsertSNo.setBounds(40, 70, 160, 25);
        add(lblInsertSNo);

        lblstaffid = new JLabel(messages.getString("label.staff.id"));
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
                if (!txtstaffid.getText().isEmpty()) {
                    Integer staffId = Integer.parseInt(txtstaffid.getText().trim());
                    Staff staff = Staff.findById(staffId);
                    if (staff == null) {
                        JOptionPane.showMessageDialog(null, "Staff Record Not Found!!!");
                    }
                    String fullName = staff.getFullName();
                    String address = staff.getAddress();
                    String contact = staff.getContact();
                    String gender = staff.getGender().getName();
                    String department = staff.getDepartment();
                    String post = staff.getPost();
                    String shiftFrom = staff.getShiftFrom().toString();
                    String shiftTo = staff.getShiftTo().toString();
                    String dateOfBirth = staff.getDateOfBirth().toString();
                    String dateOfJoin = staff.getDateOfJoin().toString();

                    txtfullname.setText(fullName);
                    txtaddress.setText(address);
                    txtcontact.setText(contact);
                    txtdepartment.setText(department);
                    txtpost.setText(post);
                    txtworkfrom.setText(shiftFrom);
                    txtworkto.setText(shiftTo);
                    txtdob.setText(dateOfBirth);
                    txtdoj.setText(dateOfJoin);
                    switch (gender) {
                        case "Male":
                            cbm.setState(true);
                            break;
                        case "Female":
                            cbf.setState(true);
                            break;
                        default:
                            throw new RuntimeException("Incorrect gender");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "First Enter the Staff ID......");
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

        btnModify = new JButton(messages.getString("update.staff.label"));
        btnModify.setBounds(540, 98, 150, 30);
        add(btnModify);
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer staffId = Integer.parseInt(txtstaffid.getText().trim());
                if (staffId == null) {
                    JOptionPane.showMessageDialog(null, "First Enter the Patient ID...");
                } else {
                    Staff staff = Staff.findById(staffId);

                    String fullName = txtfullname.getText().trim();
                    String address = txtaddress.getText().trim();
                    String contact = txtcontact.getText().trim();
                    String department = txtdepartment.getText().trim();
                    String shiftFrom = txtworkfrom.getText().trim();
                    String shiftTo = txtworkto.getText().trim();
                    String dateOfBirth = txtdob.getText().trim();
                    String dateOfJoin = txtdoj.getText().trim();
                    String gender = (cbm.getState() == true) ? "Male" : ((cbf.getState() == true) ? "Female" : null);

                    HashMap staffDto = new HashMap();
                    staffDto.put("fullName", fullName);
                    staffDto.put("address", address);
                    staffDto.put("contact", contact);
                    staffDto.put("gender", gender);
                    staffDto.put("department", department);
                    staffDto.put("shiftFrom", shiftFrom);
                    staffDto.put("shiftTo", shiftTo);
                    staffDto.put("dateOfBirth", dateOfBirth);
                    staffDto.put("dateOfJoin", dateOfJoin);

                    Staff.update(staff.getId(), staffDto);

                    JOptionPane.showMessageDialog(new JFrame(), "Data Modified successfully!", "Done!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
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
        add(txtfullname);

        lbladdress = new JLabel(messages.getString("label.address"));
        lbladdress.setBounds(60, 240, 80, 25);
        add(lbladdress);

        txtaddress = new TextArea();
        txtaddress.setBounds(200, 240, 200, 100);
        add(txtaddress);

        lblcontact = new JLabel(messages.getString("label.contact"));
        lblcontact.setBounds(540, 200, 60, 25);
        add(lblcontact);

        txtcontact = new JTextField(30);
        txtcontact.setBounds(660, 200, 200, 25);
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
        txtdob.setBounds(660, 280, 200, 25);
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
        add(txtdepartment);

        lbldoj = new JLabel(messages.getString("label.date.of.join"));
        lbldoj.setBounds(60, 600, 100, 25);
        add(lbldoj);

        txtdoj = new JTextField(40);
        txtdoj.setBounds(200, 600, 200, 25);
        add(txtdoj);

        lbldf2 = new JLabel(messages.getString("label.date.format"));
        lbldf2.setBounds(320, 630, 100, 20);
        add(lbldf2);

        lblworkfrom = new JLabel(messages.getString("label.shift.from"));
        lblworkfrom.setBounds(540, 450, 80, 25);
        add(lblworkfrom);

        txtworkfrom = new JTextField(30);
        txtworkfrom.setBounds(660, 450, 200, 25);
        add(txtworkfrom);

        lblworkto = new JLabel(messages.getString("label.shift.to"));
        lblworkto.setBounds(540, 490, 80, 25);
        add(lblworkto);

        txtworkto = new JTextField(30);
        txtworkto.setBounds(660, 490, 200, 25);
        add(txtworkto);

        lblpost = new JLabel("Job Post");
        lblpost.setBounds(540, 530, 80, 25);
        add(lblpost);

        txtpost = new TextArea();
        txtpost.setBounds(660, 530, 200, 100);
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
        txtfullname.setText("");
        txtcontact.setText("");
        txtstaffid.setText("");
        txtworkfrom.setText("");
        txtworkto.setText("");
        txtaddress.setText("");
        txtdepartment.setText("");
        txtpost.setText("");
        txtdob.setText("");
        txtdoj.setText("");
        cbmf.setSelectedCheckbox(null);
    }

}
