package EPS.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignUpGUI {

    private JPanel contentPane;
    private JFrame frame;

    /**
     * Create the frame.
     */
    public SignUpGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 300, 200);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel userMs = new JLabel("Usuario:");
        userMs.setBounds(100, 12, 100, 15);
        contentPane.add(userMs);

        JTextField userField = new JTextField();
        userField.setBounds(100, 30, 100, 15);
        contentPane.add(userField);

        JLabel passwordMs = new JLabel("Contraseña:");
        passwordMs.setBounds(100, 60, 100, 15);
        contentPane.add(passwordMs);

        JTextField passwField = new JTextField();
        passwField.setBounds(100, 78, 100, 15);
        contentPane.add(passwField);

        JButton logInBtn = new JButton("Registrarse");
        logInBtn.setBounds(100, 120, 117, 25);
        contentPane.add(logInBtn);
    }

    public void setVisible(){
        frame.setVisible(true);
    }
}
