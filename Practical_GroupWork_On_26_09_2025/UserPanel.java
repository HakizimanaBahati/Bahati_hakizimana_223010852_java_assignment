package com.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.util.DB;

public class UserPanel extends JPanel implements ActionListener {

    JTextField idTxt = new JTextField();
    JTextField nameTxt = new JTextField();
    JTextField emailTxt = new JTextField();
    JPasswordField passTxt = new JPasswordField();
    JComboBox<String> roleCmb = new JComboBox<>(new String[]{"Admin", "Teacher", "Student"});

    JButton addBtn = new JButton("Add");
    JButton updateBtn = new JButton("Update");
    JButton deleteBtn = new JButton("Delete");
    JButton loadBtn = new JButton("Load");

    JTable table;
    DefaultTableModel model;

    public UserPanel() {
        setLayout(null);

        // Table columns
        String[] labels = {"ID", "Username", "PasswordHash", "Role", "Email"};
        model = new DefaultTableModel(labels, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 250, 800, 300);

        int y = 20;
        addField("ID", idTxt, y);
        idTxt.setEditable(false);
        y += 30;
        addField("Username", nameTxt, y);
        y += 30;
        addField("Password", passTxt, y);
        y += 30;
        addField("Email", emailTxt, y);
        y += 30;
        addComboField("Role", roleCmb, y);

        addButtons();
        add(sp);

        loadUsers();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    idTxt.setText(model.getValueAt(row, 0).toString());
                    nameTxt.setText(model.getValueAt(row, 1).toString());
                    passTxt.setText(model.getValueAt(row, 2).toString());
                    roleCmb.setSelectedItem(model.getValueAt(row, 3).toString());
                    emailTxt.setText(model.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private void addField(String lbl, JComponent txt, int y) {
        JLabel l = new JLabel(lbl);
        l.setBounds(20, y, 80, 25);
        txt.setBounds(100, y, 150, 25);
        add(l);
        add(txt);
    }

    private void addComboField(String lbl, JComboBox<String> cmb, int y) {
        JLabel l = new JLabel(lbl);
        l.setBounds(20, y, 80, 25);
        cmb.setBounds(100, y, 150, 25);
        add(l);
        add(cmb);
    }

    private void addButtons() {
        addBtn.setBounds(300, 20, 100, 30);
        updateBtn.setBounds(300, 60, 100, 30);
        deleteBtn.setBounds(300, 100, 100, 30);
        loadBtn.setBounds(300, 140, 100, 30);

        add(addBtn);
        add(updateBtn);
        add(deleteBtn);
        add(loadBtn);

        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }

    // Hash password before storing
    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try (Connection con = DB.getConnection()) {
            if (e.getSource() == addBtn) {
                if (nameTxt.getText().isEmpty() || passTxt.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(this, "Username and Password cannot be empty!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO `User` (Username, PasswordHash, Role, Email) VALUES (?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, nameTxt.getText());
                ps.setString(2, hashPassword(new String(passTxt.getPassword())));
                ps.setString(3, roleCmb.getSelectedItem().toString());
                ps.setString(4, emailTxt.getText());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int generatedId = keys.getInt(1);
                    idTxt.setText(String.valueOf(generatedId));
                }

                JOptionPane.showMessageDialog(this, "User Added!");
                loadUsers();
                clearFieldsExceptId();

            } else if (e.getSource() == updateBtn) {
                if (idTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a user to update!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE `User` SET Username=?, PasswordHash=?, Role=?, Email=? WHERE UserID=?");
                ps.setString(1, nameTxt.getText());
                ps.setString(2, hashPassword(new String(passTxt.getPassword())));
                ps.setString(3, roleCmb.getSelectedItem().toString());
                ps.setString(4, emailTxt.getText());
                ps.setInt(5, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "User Updated!");
                loadUsers();
                clearFieldsExceptId();

            } else if (e.getSource() == deleteBtn) {
                if (idTxt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Select a user to delete!");
                    return;
                }

                PreparedStatement ps = con.prepareStatement("DELETE FROM `User` WHERE UserID=?");
                ps.setInt(1, Integer.parseInt(idTxt.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "User Deleted!");
                loadUsers();
                clearFieldsExceptId();

            } else if (e.getSource() == loadBtn) {
                loadUsers();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void loadUsers() {
        try (Connection con = DB.getConnection()) {
            model.setRowCount(0);
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `User`");
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role"),
                        rs.getString("Email")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFieldsExceptId() {
        nameTxt.setText("");
        passTxt.setText("");
        emailTxt.setText("");
        roleCmb.setSelectedIndex(0);
    }
}
