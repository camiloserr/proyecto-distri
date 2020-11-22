package EPS.views;

import EPS.controller.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInGUI {

    private JPanel contentPane;
    private JFrame frame;

    /**
     * Create the frame.
     */
    public LogInGUI() {
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

        JButton logInBtn = new JButton("Ingresar");
        logInBtn.setBounds(100, 120, 117, 25);
        contentPane.add(logInBtn);
        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EPSClient epsClient = new EPSClient();
                //epsClient
                frame.setVisible(false);
            }
        });

    }

    public void setVisible(boolean value){
        frame.setVisible(value);
    }
}
