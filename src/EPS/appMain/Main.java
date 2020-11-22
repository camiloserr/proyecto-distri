//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EPS.appMain;

import EPS.controller.EPSClient;
import EPS.views.LogInGUI;

import java.awt.*;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        EPSClient epsClient = new EPSClient();
        deployMainView(epsClient);
    }

    public static void deployMainView(EPSClient epsClient) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //LandingGUI window = new LandingGUI();
                    LogInGUI window = new LogInGUI();
                    window.setVisible();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
