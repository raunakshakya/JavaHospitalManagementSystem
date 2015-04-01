/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Doctor;
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
public final class DoctorAdd extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

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
    int status;

    public DoctorAdd() {
        super(LayoutUtils.NEW_DOCTOR_TITLE);

        //Patient's Personal Information...
        mainTitle = new JLabel(messages.getString("label.add.doctor.information"));
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setBounds(LayoutUtils.HEADING_X_COORDINATE, LayoutUtils.HEADING_Y_COORDINATE, LayoutUtils.HEADING_HORIZONTAL_LENGTH, LayoutUtils.HEADING_VERTICAL_LENGTH);
        add(mainTitle);

        subTitle = new JLabel(messages.getString("personal.information.title"));
        subTitle.setFont(new Font("Arial", Font.BOLD, 20));
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
        txtaddress.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 140, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
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

        //Specialization...
        lblspec = new JLabel(messages.getString("label.specialization"));
        lblspec.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblspec);
        txtSpec = new TextArea();
        txtSpec.setBounds(220, 310, 250, LayoutUtils.TEXTAREA_VERTICAL_LENGTH);
        add(txtSpec);

        //Status...
        lblstatus = new JLabel(messages.getString("label.status"));
        lblstatus.setBounds(LayoutUtils.LABEL_LEFT_X_COORDINATE, 420, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblstatus);
        chkboxStatus = new JCheckBox();
        chkboxStatus.setBounds(LayoutUtils.TEXTFIELD_LEFT_X_COORDINATE, 420, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(chkboxStatus);

        //Date of Join...
        lbldoj = new JLabel(messages.getString("label.date.of.join"));
        lbldoj.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 310, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lbldoj);
        txtdoj = new JTextField(40);
        txtdoj.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 310, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtdoj);
        lbldojformat = new JLabel(messages.getString("label.date.format"));
        lbldojformat.setBounds(800, 310, 100, 25);
        //add(lbldojformat);

        //Shift From...
        lblshiftfrom = new JLabel(messages.getString("label.shift.from"));
        lblshiftfrom.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 350, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftfrom);
        txtshiftfrom = new JTextField(100);
        txtshiftfrom.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 350, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftfrom);
        lbltimeformat1 = new JLabel(messages.getString("label.time.format"));
        lbltimeformat1.setBounds(800, 350, 100, 25);
        //add(lbltimeformat1);

        //Shift To...
        lblshiftto = new JLabel(messages.getString("label.shift.to"));
        lblshiftto.setBounds(LayoutUtils.LABEL_RIGHT_X_COORDINATE, 390, LayoutUtils.LABEL_LEFT_HORIZONTAL_LENGTH, LayoutUtils.LABEL_LEFT_VERTICAL_LENGTH);
        add(lblshiftto);
        txtshiftto = new JTextField(100);
        txtshiftto.setBounds(LayoutUtils.TEXTFIELD_RIGHT_X_COORDINATE, 390, LayoutUtils.TEXTFIELD_HORIZONTAL_LENGTH, LayoutUtils.TEXTFIELD_VERTICAL_LENGTH);
        add(txtshiftto);
        lbltimeformat2 = new JLabel(messages.getString("label.time.format"));
        lbltimeformat2.setBounds(800, 390, 100, 25);
        //add(lbltimeformat2);

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
                String specialization = txtSpec.getText().trim();
                String shiftto = txtshiftto.getText().trim();
                String shiftfrom = txtshiftfrom.getText().trim();
                String dob = txtdob.getText().trim();
                String doj = txtdoj.getText().trim();
                String gender = (cbm.getState() == true) ? "M" : ((cbf.getState() == true) ? "F" : null);
                status = chkboxStatus.isSelected() ? 1 : 0;

                if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || specialization.isEmpty() || shiftto.isEmpty()
                        || shiftfrom.isEmpty() || dob.isEmpty() || doj.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter all the field data correctly!!!");
                } else {
                    HashMap doctorDto = new HashMap();
                    doctorDto.put("fullName", fullname);
                    doctorDto.put("address", address);
                    doctorDto.put("contact", contact);
                    doctorDto.put("status", status);
                    doctorDto.put("gender", gender);
                    doctorDto.put("dateOfBirth", dob);
                    doctorDto.put("dateOfJoin", doj);
                    doctorDto.put("specialization", specialization);
                    doctorDto.put("shiftFrom", shiftfrom);
                    doctorDto.put("shiftTo", shiftto);

                    Doctor.create(doctorDto);
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
