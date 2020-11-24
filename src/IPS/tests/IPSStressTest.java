package IPS.tests;

import GCC.controller.GCCServant;
import GCC.controller.IGCC;
import GCC.persistence.GCCPersistence;
import IPS.controller.IIPS;
import IPS.controller.IPSServant;
import IPS.model.IPSData;
import IPS.persistence.IPSPersistence;
import org.junit.Test;


import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

public class IPSStressTest {


    public static void main(String[] args) {

        IIPS ips;
        try {

            IPSPersistence persistence = new IPSPersistence("src/IPS/tests/vacunasIps.txt","src/IPS/tests/vacunasIps.txt", "src/IPS/tests/ipsData.txt");

            ips = new IPSServant(persistence);
        } catch (RemoteException e) {
            e.printStackTrace();
            return;
        }

        boolean bandera = true;
        int peticion =1;
        int vacA, vacB , vacC;
        int maxPeticiones = 1000000;
        long startTime = System.nanoTime();

        while(bandera == true){
            try {
                int[] vacunas = ips.darVacunaActuales();
                System.out.println("La ips tiene \nA: " + String.valueOf(vacunas[0]) + "\nB: " + vacunas[1] + "\nC: " + vacunas[2]);
                Random rand = new Random();

                int[] pet =  {rand.nextInt( 20), rand.nextInt( 20), rand.nextInt( 20)};

                //System.out.println(pet) ;

                ips.pedirVacunas( pet  , vacunas );

                vacunas = ips.darVacunaActuales();
                System.out.println("La ips tiene \nA: " + vacunas[0] + "\nB: " + vacunas[1] + "\nC: " + vacunas[2]);

                System.out.println("Peticion #" + peticion);
                peticion++;
            }
            catch (Exception e){

                e.printStackTrace();
                System.out.println("Ha ocuerrido una excepcion, se acabo la prueba");
            }

            if(peticion >= maxPeticiones){
                bandera = false;
            }
        }

        long endTime = System.nanoTime();

        System.out.println("se acabo la prueba con " + String.valueOf(maxPeticiones) +" peticiones\nSe demoró " + (endTime - startTime)/1000000 + " milisegundos" );
        return;

    }
}
