package EPS;

import GCC.IGCC;

import java.rmi.Naming;
import java.util.List;

public class EPSClient {

    private EPSClient() {}

    public static void main(String[] args) {

        try {
            IGCC servicio = (IGCC) Naming.lookup("rmi://localhost:9999/GCC");
            String response = servicio.sayHello();
            List <Boolean> vacRecib = servicio.pedirVacunas(2,2,2);
            System.out.println( "response: " + vacRecib.toString() );
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
