/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.models.Staff;
import com.hospitalmgmt.utils.LayoutUtils;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Raunak Shakya
 */
public class StaffDBData extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    public StaffDBData() {
        super(LayoutUtils.VIEW_STAFF_TITLE);

        Container container = getContentPane();

        String columnNames[] = {"Name", "Address", "Contact", "Gender", "Status", "Date of Birth",
            "Date of Join", "Department", "Post", "Shift from", "Shift to"};
        String data[][] = new String[11][];

        List<Staff> staffs = Staff.findAll();
        int i = 0;
        for (Staff staff : staffs) {
            data[0][i] = staff.getFullName();
            data[1][i] = staff.getAddress();
            data[2][i] = staff.getContact();
            data[3][i] = staff.getGender().getName();
            data[4][i] = staff.getEmployeeStatus().name();
            data[5][i] = staff.getDateOfBirth().toString();
            data[6][i] = staff.getDateOfJoin().toString();
            data[7][i] = staff.getDepartment();
            data[8][i] = staff.getPost();
            data[9][i] = staff.getShiftFrom().toString();
            data[10][i] = staff.getShiftTo().toString();
            i++;
        }

        //  Create table with database data
        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        table.getColumnModel().getColumn(1).setPreferredWidth(105);
        table.getColumnModel().getColumn(2).setPreferredWidth(105);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(130);
        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        table.getColumnModel().getColumn(7).setPreferredWidth(150);
        table.getColumnModel().getColumn(8).setPreferredWidth(130);
        table.getColumnModel().getColumn(9).setPreferredWidth(110);
        table.getColumnModel().getColumn(10).setPreferredWidth(125);
        table.getColumnModel().getColumn(11).setPreferredWidth(125);

        table.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        container.add(scrollPane);

        setSize(900, 400);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(50, 50);
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

}
