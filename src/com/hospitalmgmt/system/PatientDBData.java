/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospitalmgmt.system;

import com.hospitalmgmt.utils.DBConnectionUtils;
import com.hospitalmgmt.utils.MessageUtils;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Raunak Shakya
 */
public class PatientDBData extends JInternalFrame {

    public static final ResourceBundle messages = MessageUtils.MESSAGES;

    static Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public PatientDBData() {
        super(messages.getString("label.view.patient.information"));

        Container con = getContentPane();

        Vector columnNames = new Vector();
        Vector data = new Vector();

        try {
            try {
                Class.forName(DBConnectionUtils.DB_DRIVER);
                conn = DriverManager.getConnection(DBConnectionUtils.DB_CONNECTION_URL, DBConnectionUtils.DB_USERNAME, DBConnectionUtils.DB_PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }

            //  Read data from a table
            stmt = conn.prepareStatement("Select * from patient_table");
            ResultSet resultSet = stmt.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(md.getColumnName(i));
            }

            //  Get row data
            while (resultSet.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(resultSet.getObject(i));
                }
                data.addElement(row);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has occurred!!!");
        }

        //  Create table with database data
        JTable table = new JTable(data, columnNames);

        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        table.getColumnModel().getColumn(1).setPreferredWidth(110);
        table.getColumnModel().getColumn(2).setPreferredWidth(115);
        table.getColumnModel().getColumn(3).setPreferredWidth(105);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        table.getColumnModel().getColumn(6).setPreferredWidth(130);
        table.getColumnModel().getColumn(7).setPreferredWidth(155);
        table.getColumnModel().getColumn(8).setPreferredWidth(90);
        table.getColumnModel().getColumn(9).setPreferredWidth(110);
        table.getColumnModel().getColumn(10).setPreferredWidth(105);
        table.getColumnModel().getColumn(11).setPreferredWidth(110);
        table.getColumnModel().getColumn(12).setPreferredWidth(110);

        table.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        con.add(scrollPane);

        setSize(900, 400);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(true);
        setLocation(50, 50);
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

}
