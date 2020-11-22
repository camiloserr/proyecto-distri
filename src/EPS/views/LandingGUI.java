package EPS.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

        JButton logInBtn = new JButton("Ingresar");
        logInBtn.setBounds(153, 99, 117, 25);
        contentPane.add(logInBtn);

        JButton signUpBtn = new JButton("Regitsrarse");
        signUpBtn.setBounds(153, 163, 117, 25);
        contentPane.add(signUpBtn);
    }

    public void setVisible(){
        frame.setVisible(true);
    }

}
