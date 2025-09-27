package com.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.security.MessageDigest;
import com.util.DB;

public class LoginForm extends JFrame implements ActionListener {
    JTextField usertxt = new JTextField();
    JPasswordField passtxt = new JPasswordField();
    JButton loginbtn = new JButton("LOGIN");
    JButton cancelbtn = new JButton("CANCEL");

    public LoginForm() {
        setTitle("Login Form");
        setBounds(100, 100, 300, 200);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        userLabel.setBounds(50, 10, 120, 20);
        passLabel.setBounds(50, 50, 120, 20);
        usertxt.setBounds(50, 30, 180, 25);
        passtxt.setBounds(50, 70, 180, 25);
        loginbtn.setBounds(30, 120, 100, 30);
        cancelbtn.setBounds(150, 120, 100, 30);

        add(userLabel);
        add(usertxt);
        add(passLabel);
        add(passtxt);
        add(loginbtn);
        add(cancelbtn);

        loginbtn.addActionListener(this);
        cancelbtn.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    
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
        if (e.getSource() == cancelbtn) {
            System.exit(0);
        }

        try (Connection con = DB.getConnection()) {
            
            String sql = "SELECT * FROM `User` WHERE Username=? AND PasswordHash=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usertxt.getText());
            ps.setString(2, hashPassword(new String(passtxt.getPassword())));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                int userId = rs.getInt("UserID");
                JOptionPane.showMessageDialog(this, "Login successful! Role: " + role);
                dispose();
                new SMIS(role, userId); // load main system based on role
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
