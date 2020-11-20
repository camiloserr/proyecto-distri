package EPS.controller;

import GCC.controller.IGCC;

import java.rmi.Naming;
import java.util.List;

public class EPSClient {

    private EPSClient() {}

    public static void main(String[] args) {
        //TODO: usar VaccineManager para pedir las vacunas
        try {
            IGCC servicio = (IGCC) Naming.lookup("rmi://localhost:9999/GCC");
            List <Integer> vacRecib = servicio.pedirVacunas(2,2,2);
            System.out.println( "response: " + vacRecib.toString() );

            List <Integer> vacRecib2 = servicio.pedirVacunas(2,2,2);
            System.out.println( "response: " + vacRecib2.toString() );

            List <Integer> vacRecib3 = servicio.pedirVacunas(2,2,2);
            System.out.println( "response: " + vacRecib3.toString() );

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
