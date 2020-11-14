package EPS;

import GCC.IGCC;

import java.rmi.Naming;

public class EPSClient {

    private EPSClient() {}

    public static void main(String[] args) {

        try {
            IGCC servicio = (IGCC) Naming.lookup("rmi://localhost:9999/GCC");
            String response = servicio.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
