package com.panel;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.util.DB;

public class DepartmentPanel extends JPanel implements ActionListener {
    JTextField depIdTxt = new JTextField();
    JTextField depNameTxt = new JTextField();
    JTextField descriptionTxt = new JTextField();

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");

    JTable table;
    DefaultTableModel model;

    public DepartmentPanel() {
        setLayout(null);

        // Table columns
        String[] columns = { "DepartmentID", "DepartmentName", "Description" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 200, 800, 300);
        add(sp);

        int y = 20;
        addField("Department ID", depIdTxt, y);
        depIdTxt.setEditable(false);
        y += 30;
        addField("Department Name", depNameTxt, y);
        y += 30;
        addField("Description", descriptionTxt, y);

        addButtons();
        loadDepartments();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    depIdTxt.setText(model.getValueAt(row, 0).toString());
                    depNameTxt.setText(model.getValueAt(row, 1).toString());
                    descriptionTxt.setText(model.getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void addField(String label, JTextField field, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(20, y, 150, 25);
        field.setBounds(180, y, 200, 25);
        add(l);
        add(field);
    }

    private void addButtons() {
        addBtn.setBounds(450, 20, 100, 30);
        updateBtn.setBounds(450, 60, 100, 30);
        deleteBtn.setBounds(450, 100, 100, 30);
        loadBtn.setBounds(450, 140, 100, 30);

        add(addBtn);
        add(updateBtn);
        add(deleteBtn);
        add(loadBtn);

        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try (Connection con = DB.getConnection()) {
            if (e.getSource() == addBtn) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO department (DepartmentName, Description) VALUES (?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, depNameTxt.getText());
                ps.setString(2, descriptionTxt.getText());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    depIdTxt.setText(String.valueOf(keys.getInt(1)));
                }

                JOptionPane.showMessageDialog(this, "Department Added!");
                loadDepartments();

            } else if (e.getSource() == updateBtn) {
                if (depIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a department to update!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE department SET DepartmentName=?, Description=? WHERE DepartmentID=?");
                ps.setString(1, depNameTxt.getText());
                ps.setString(2, descriptionTxt.getText());
                ps.setInt(3, Integer.parseInt(depIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Department Updated!");
                loadDepartments();

            } else if (e.getSource() == deleteBtn) {
                if (depIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a department to delete!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement("DELETE FROM department WHERE DepartmentID=?");
                ps.setInt(1, Integer.parseInt(depIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Department Deleted!");
                loadDepartments();

            } else if (e.getSource() == loadBtn) {
                loadDepartments();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadDepartments() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM department");
            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("DepartmentID"),
                        rs.getString("DepartmentName"),
                        rs.getString("Description")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading departments: " + ex.getMessage());
        }
    }
}
