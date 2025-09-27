package com.panel;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.util.DB;

public class CoursePanel extends JPanel implements ActionListener {

    JTextField courseIdTxt = new JTextField();
    JTextField courseNameTxt = new JTextField();
    JTextField creditsTxt = new JTextField();
    JTextField depIdTxt = new JTextField();

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");

    JTable table;
    DefaultTableModel model;

    public CoursePanel() {
        setLayout(null);

        // Table columns
        String[] columns = { "CourseID", "CourseName", "Credits", "DepartmentID" };
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 250, 800, 300);
        add(sp);

        // Form fields
        int y = 20;
        addField("Course ID", courseIdTxt, y);
        courseIdTxt.setEditable(false);
        y += 30;
        addField("Course Name", courseNameTxt, y);
        y += 30;
        addField("Credits", creditsTxt, y);
        y += 30;
        addField("Department ID", depIdTxt, y);

        addButtons();

        loadCourses();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    courseIdTxt.setText(model.getValueAt(row, 0).toString());
                    courseNameTxt.setText(model.getValueAt(row, 1).toString());
                    creditsTxt.setText(model.getValueAt(row, 2).toString());
                    depIdTxt.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    private void addField(String label, JTextField field, int y) {
        JLabel l = new JLabel(label);
        l.setBounds(20, y, 120, 25);
        field.setBounds(150, y, 150, 25);
        add(l);
        add(field);
    }

    private void addButtons() {
        addBtn.setBounds(320, 20, 100, 30);
        updateBtn.setBounds(320, 60, 100, 30);
        deleteBtn.setBounds(320, 100, 100, 30);
        loadBtn.setBounds(320, 140, 100, 30);

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
                if (courseNameTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Course Name cannot be empty!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO course (CourseName, Credits, DepartmentID) VALUES (?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, courseNameTxt.getText());
                ps.setInt(2, Integer.parseInt(creditsTxt.getText()));
                ps.setInt(3, Integer.parseInt(depIdTxt.getText()));
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    courseIdTxt.setText(String.valueOf(keys.getInt(1)));
                }

                JOptionPane.showMessageDialog(this, "Course Added!");
                loadCourses();

            } else if (e.getSource() == updateBtn) {
                if (courseIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a course to update!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE course SET CourseName=?, Credits=?, DepartmentID=? WHERE CourseID=?");
                ps.setString(1, courseNameTxt.getText());
                ps.setInt(2, Integer.parseInt(creditsTxt.getText()));
                ps.setInt(3, Integer.parseInt(depIdTxt.getText()));
                ps.setInt(4, Integer.parseInt(courseIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Course Updated!");
                loadCourses();

            } else if (e.getSource() == deleteBtn) {
                if (courseIdTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a course to delete!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement("DELETE FROM course WHERE CourseID=?");
                ps.setInt(1, Integer.parseInt(courseIdTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Course Deleted!");
                loadCourses();

            } else if (e.getSource() == loadBtn) {
                loadCourses();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadCourses() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM course");
            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("CourseID"),
                        rs.getString("CourseName"),
                        rs.getInt("Credits"),
                        rs.getInt("DepartmentID")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading courses: " + ex.getMessage());
        }
    }
}
