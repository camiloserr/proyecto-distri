//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EPS.appMain;

import EPS.controller.EPSClient;
import EPS.views.*;

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
                    LandingGUI window = new LandingGUI();
                    //EPSClient epsClient = new EPSClient();
                    //EPSInfoGUI window = new EPSInfoGUI(epsClient, "coomeva");
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
