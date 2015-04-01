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
import java.awt.event.*;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.swing.*;

/**
 *
 * @author Raunak Shakya
 */
public class StaffAdd extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

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

        //Patient's Personal Information...
        mainTitle = new JLabel(messages.getString("label.add.staff.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(LayoutUtils.HEADING_X_COORDINATE, LayoutUtils.HEADING_Y_COORDINATE, LayoutUtils.HEADING_HORIZONTAL_LENGTH, LayoutUtils.HEADING_VERTICAL_LENGTH);
        add(mainTitle);

        subTitle = new JLabel(messages.getString("personal.information.title"));
        subTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.UPPER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subTitle);

        lblfullname = new JLabel(messages.getString("label.full.name"));
        lblfullname.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, LayoutUtils.LABEL_LEFT_Y_COORDINATE, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblfullname);
        txtfullname = new JTextField(30);
        txtfullname.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 100, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtfullname);

        lbladdress = new JLabel(messages.getString("label.address"));
        lbladdress.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 140, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbladdress);
        txtaddress = new TextArea();
        txtaddress.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 140, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, 100);
        add(txtaddress);

        //Patient's Date Of Birth...
        lbldob = new JLabel(messages.getString("label.date.of.birth"));
        lbldob.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 100, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldob);
        txtdob = new JTextField(15);
        txtdob.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 100, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdob);
        lbldobformat = new JLabel(messages.getString("label.date.format"));
        lbldobformat.setBounds(800, 100, 100, 20);
        add(lbldobformat);

        //Gender...
        lblgender = new JLabel(messages.getString("label.gender"));
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
        lblcontact = new JLabel(messages.getString("label.contact"));
        lblcontact.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 180, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblcontact);
        txtcontact = new JTextField(30);
        txtcontact.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 180, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtcontact);

        //Professional Information...
        subprofTitle = new JLabel(messages.getString("professional.information.title"));
        subprofTitle.setFont(new Font("Arial", Font.BOLD, 18));
        subprofTitle.setBounds(LayoutUtils.SUB_HEADING_X_COORDINATE, LayoutUtils.LOWER_SUB_HEADING_Y_COORDINATE, LayoutUtils.SUB_HEADING_HORIZONTAL_LENGTH, LayoutUtils.SUB_HEADING_VERTICAL_LENGTH);
        add(subprofTitle);

        //Department...
        lbldept = new JLabel(messages.getString("label.department"));
        lbldept.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldept);
        txtDept = new JTextField(30);
        txtDept.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtDept);

        //Post...
        lblpost = new JLabel(messages.getString("label.post"));
        lblpost.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblpost);
        txtpost = new JTextField(30);
        txtpost.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtpost);

        //Status...
        lblstatus = new JLabel(messages.getString("label.status"));
        lblstatus.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 390, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblstatus);
        chkboxStatus = new JCheckBox();
        chkboxStatus.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 390, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(chkboxStatus);

        //Shift From...
        lblshiftfron = new JLabel(messages.getString("label.shift.from"));
        lblshiftfron.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftfron);
        txtshiftfrom = new JTextField(100);
        txtshiftfrom.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftfrom);

        //Shift To...
        lblshiftto = new JLabel(messages.getString("label.shift.to"));
        lblshiftto.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftto);
        txtshiftto = new JTextField(100);
        txtshiftto.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftto);

        //Date of Join...
        lbldoj = new JLabel(messages.getString("label.date.of.join"));
        lbldoj.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 390, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 390, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdoj);
        lbldojformat = new JLabel(messages.getString("label.date.format"));
        lbldojformat.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 420, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(lbldojformat);

        //Button to submit information...
        btnAdd = new JButton(messages.getString("common.add"));
        btnAdd.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullname = txtfullname.getText().trim();
                String address = txtaddress.getText().trim();
                String contact = txtcontact.getText().trim();
                String department = txtDept.getText().trim();
                String post = txtpost.getText().trim();
                String shiftto = txtshiftto.getText().trim();
                String shiftfrom = txtshiftfrom.getText().trim();
                String dob = txtdob.getText().trim();
                String doj = txtdoj.getText().trim();
                String gender = (cbm.getState() == true) ? "Male" : ((cbf.getState() == true) ? "Female" : null);
                staffStatus = (chkboxStatus.isSelected()) ? 1 : 0;

                if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || department.isEmpty() || post.isEmpty()
                        || shiftto.isEmpty() || shiftfrom.isEmpty() || dob.isEmpty() || doj.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter all the field data correctly!!!");
                } else {
                    HashMap staffDto = new HashMap();
                    staffDto.put("fullName", fullname);
                    staffDto.put("address", address);
                    staffDto.put("contact", contact);
                    staffDto.put("post", post);
                    staffDto.put("status", staffStatus);
                    staffDto.put("gender", gender);
                    staffDto.put("dateOfBirth", dob);
                    staffDto.put("dateOfJoin", doj);
                    staffDto.put("department", department);
                    staffDto.put("shiftFrom", shiftfrom);
                    staffDto.put("shiftTo", shiftto);
                    staffDto.put("shiftFrom", shiftfrom);
                    staffDto.put("shiftTo", shiftto);

                    Staff.create(staffDto);
                }
            }
        });

        //Button to clear information...
        btnClear = new JButton(messages.getString("common.clear"));
        btnClear.setBounds(LayoutUtils.INNER_WINDOW_BUTTON_X_COORDINATE + 120, LayoutUtils.INNER_WINDOW_BUTTON_Y_COORDINATE, LayoutUtils.INNER_WINDOW_BUTTON_WIDTH, LayoutUtils.INNER_WINDOW_BUTTON_HEIGHT);
        add(btnClear);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClearTheTextFields();
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
