
//HAKIZIMANA BAHATI 223010852

package form;


	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import javax.swing.*;

	public class RegistrationForm implements ActionListener {
	    JFrame frame;
	    JLabel nameLabel = new JLabel("Names");
	    JLabel userLabel = new JLabel("UserName");
	    JLabel passLabel = new JLabel("Password");

	    JTextField nameField = new JTextField();
	    JTextField userField = new JTextField();
	    JPasswordField passField = new JPasswordField();

	    JButton registerBtn = new JButton("Register");
	    JButton resetBtn = new JButton("Reset");

	    public RegistrationForm() {
	        createWindow();
	        setLocationAndSize();
	        addComponentsToFrame();
	        addActionEvent();
	    }

	    private void createWindow() {
	        frame = new JFrame();
	        frame.setTitle("User Registration");
	        frame.setBounds(100, 100, 400, 300);
	        frame.getContentPane().setLayout(null);
	        frame.getContentPane().setBackground(java.awt.Color.decode("#5DADE2")); // blue background
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	    }

	    private void setLocationAndSize() {
	        nameLabel.setBounds(30, 30, 120, 30);
	        nameField.setBounds(180, 30, 150, 30);

	        userLabel.setBounds(30, 80, 120, 30);
	        userField.setBounds(180, 80, 150, 30);

	        passLabel.setBounds(30, 130, 120, 30);
	        passField.setBounds(180, 130, 150, 30);

	        registerBtn.setBounds(50, 190, 120, 40);
	        resetBtn.setBounds(200, 190, 120, 40);
	    }

	    private void addComponentsToFrame() {
	        frame.add(nameLabel);
	        frame.add(userLabel);
	        frame.add(passLabel);

	        frame.add(nameField);
	        frame.add(userField);
	        frame.add(passField);

	        frame.add(registerBtn);
	        frame.add(resetBtn);
	    }

	    private void addActionEvent() {
	        registerBtn.addActionListener(this);
	        resetBtn.addActionListener(this);
	    }

	    
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == registerBtn) {
	            String name = nameField.getText();
	            String username = userField.getText();
	            String password = String.valueOf(passField.getPassword());

	            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
	                JOptionPane.showMessageDialog(frame, "Please fill all fields!");
	            } else {
	                JOptionPane.showMessageDialog(frame,
	                        "Registered Successfully!\nName: " + name +
	                                "\nUsername: " + username);
	            }
	        } else if (e.getSource() == resetBtn) {
	            nameField.setText("");
	            userField.setText("");
	            passField.setText("");
	        }
	    }


	
	public static void main(String[] args) {
		new RegistrationForm();

	}

}

