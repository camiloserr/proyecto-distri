package EPS.views;

import EPS.controller.EPSClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EPSInfoGUI {
    private JPanel contentPane;
    private JFrame frame;

    /**
     * Create the frame.
     */
    public EPSInfoGUI(EPSClient epsClient, String userName) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 500, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        try{
            epsClient.getUsrList(userName);
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel nameLabel = new JLabel();
        nameLabel.setBounds(5, 5, 400, 15);
        nameLabel.setText("Bienvenido: " + userName);
        contentPane.add(nameLabel);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(300, 5, 80, 10);
        contentPane.add(refreshBtn);

        JTabbedPane optionPane = new JTabbedPane(JTabbedPane.TOP);
        optionPane.setBounds(16, 16, 480, 480);
        contentPane.add(optionPane);

        JPanel usrListPane = new JPanel();
        optionPane.addTab("Usuarios", null, usrListPane, null);

        JTextArea usersInfo = new JTextArea();
        usersInfo.setLineWrap(true);
        usersInfo.setBounds(30, 30, 460, 479);
        usersInfo.setEditable(true);
        JScrollPane userScroll = new JScrollPane(usersInfo);
        userScroll.setBounds(30, 30, 400, 400);
        usrListPane.add(userScroll);

        JPanel trsListPane = new JPanel();
        optionPane.addTab("Transacciones", null, trsListPane, null);

        JTextArea transInfo = new JTextArea();
        transInfo.setLineWrap(true);
        transInfo.setBounds(30, 30, 460, 460);
        transInfo.setEditable(true);
        JScrollPane transScroll = new JScrollPane(transInfo);
        transScroll.setBounds(30, 30, 400, 400);
        trsListPane.add(transScroll);

        epsClient.makeOrder();
        usersInfo.setText(epsClient.getUsrStr());
        transInfo.setText(epsClient.showTransactions());

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                epsClient.getUsrList(userName);
                epsClient.makeOrder();
                usersInfo.setText(epsClient.getUsrStr());
                transInfo.setText(epsClient.showTransactions());
            }
        });
    }

    public void setVisible(boolean value){
        frame.setVisible(value);
    }

}
