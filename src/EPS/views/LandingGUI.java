package EPS.views;

import EPS.controller.EPSClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingGUI {

    private JPanel contentPane;
    private JFrame frame;

    /**
     * Create the frame.
     */
    public LandingGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel welcomeMs = new JLabel("Bienvendido a la interfaz de su EPS");
        welcomeMs.setBounds(100, 12, 250, 15);
        contentPane.add(welcomeMs);

        JLabel infoMS = new JLabel("Si es un usuario nuevo por favor registrese");
        infoMS.setBounds(70, 51, 320, 15);
        contentPane.add(infoMS);

        EPSClient epsClient = new EPSClient();

        JButton logInBtn = new JButton("Ingresar");
        logInBtn.setBounds(153, 99, 117, 25);
        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                try{
                    LogInGUI newLogInFrame = new LogInGUI(epsClient);
                    newLogInFrame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        contentPane.add(logInBtn);

        JButton signUpBtn = new JButton("Regitsrarse");
        signUpBtn.setBounds(153, 163, 117, 25);
        contentPane.add(signUpBtn);
        signUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.setVisible(false);
                try{
                    SignUpGUI newSignUpFrame = new SignUpGUI(epsClient);
                    newSignUpFrame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Despliega o cierra la ventana
     * @param value
     */
    public void setVisible(boolean value){
        frame.setVisible(value);
    }

}
