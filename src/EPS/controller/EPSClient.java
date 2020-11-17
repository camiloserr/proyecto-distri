package EPS.controller;

import GCC.controller.IGCC;

import java.rmi.Naming;
import java.util.List;

public class EPSClient {

    private EPSClient() {}

    public static void main(String[] args) {

        try {
            IGCC servicio = (IGCC) Naming.lookup("rmi://localhost:9999/GCC");
            List <Boolean> vacRecib = servicio.pedirVacunas(2,2,2, 1);
            System.out.println( "response: " + vacRecib.toString() );

            List <Boolean> vacRecib2 = servicio.pedirVacunas(2,2,2, 2);
            System.out.println( "response: " + vacRecib2.toString() );

            List <Boolean> vacRecib3 = servicio.pedirVacunas(2,2,2, 1);
            System.out.println( "response: " + vacRecib3.toString() );



        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
