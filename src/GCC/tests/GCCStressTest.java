package GCC.tests;

import GCC.controller.GCCServant;
import GCC.controller.IGCC;
import GCC.persistence.GCCPersistence;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GCCStressTest {

    @Test
    public void GCCPedirVacunasStressTest() {

        IGCC gcc = null;

        try {
            gcc = new GCCServant(new GCCPersistence("src/gcc/persistence/config.txt", "src/gcc/persistence/authentication.txt"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear el gcc");
        }

        boolean bandera = true;
        int peticion = 1;
        int vacA, vacB, vacC;
        int maxPeticiones = 500000;
        long startTime = System.nanoTime();

        while (bandera == true) {
            try {

                Random rand = new Random();

                int[] pet = {rand.nextInt(20), rand.nextInt(20), rand.nextInt(20)};

                //System.out.println(pet) ;

                List<Integer> respuesta = gcc.pedirVacunas(pet[0], pet[1], pet[2]);

                System.out.println("Se han devuelto \nA: " + (pet[0] - respuesta.get(0)) + "\nB: " + (pet[1] - respuesta.get(1)) + "\nC: " + (pet[2] - respuesta.get(2)));

                System.out.println("Peticion #" + peticion);
                peticion++;
            } catch (Exception e) {

                e.printStackTrace();
                System.out.println("Ha ocuerrido una excepcion, se acabo la prueba");
                return;
            }

            if (peticion >= maxPeticiones) {
                bandera = false;
            }
        }

        long endTime = System.nanoTime();

        System.out.println("se acabo la prueba con " + String.valueOf(maxPeticiones) + " peticiones\nSe demoró " + (endTime - startTime) / 1000000 + " milisegundos");
        return;

    }


    @Test
    public void GCCLoginStressTest() {

        IGCC gcc = null;

        try {
            gcc = new GCCServant(new GCCPersistence("src/gcc/tests/testConfig.txt", "src/gcc/tests/testAuth.txt"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear el gcc");
        }

        boolean bandera = true;
        int peticion = 1;
        String[] usuarios = {"usuario1", "usuario2", "usuario3", "usuario4"};
        String[] contras = {"hash1", "hash2", "7e240de74fb1ed08fa08d38063f6a6a91462a815", "5cb138284d431abd6a053a56625ec088bfb88912"};
        int maxPeticiones = 500000;
        long startTime = System.nanoTime();

        while (bandera == true) {
            try {

                boolean res = gcc.login(usuarios[peticion % 4], contras[peticion % 4]);
                if (peticion % 1000 == 0) {
                    System.out.println("Peticion: " + peticion);
                }
                peticion++;
            } catch (Exception e) {

                e.printStackTrace();
                System.out.println("Ha ocuerrido una excepcion, se acabo la prueba");
                return;
            }

            if (peticion >= maxPeticiones) {
                bandera = false;
            }
        }

        long endTime = System.nanoTime();

        System.out.println("se acabo la prueba con " + String.valueOf(maxPeticiones) + " peticiones\nSe demoró " + (endTime - startTime) / 1000000 + " milisegundos");
        return;

    }


    @Test
    public void GCCSignUpStressTest() {

        IGCC gcc = null;

        try {
            gcc = new GCCServant(new GCCPersistence("src/gcc/tests/testConfig.txt", "src/gcc/tests/testAuth.txt"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear el gcc");
        }

        boolean bandera = true;
        int peticion = 1;
        String[] usuarios = {"usuario1", "usuario2", "usuario3", "usuario4"};
        String[] contras = {"hash1", "hash2", "7e240de74fb1ed08fa08d38063f6a6a91462a815", "5cb138284d431abd6a053a56625ec088bfb88912"};
        int maxPeticiones = 500000;
        long startTime = System.nanoTime();

        while (bandera == true) {
            try {

                boolean res = gcc.register(randomString(), randomString());
                if (peticion % 1000 == 0) {
                    System.out.println("Peticion: " + peticion);
                }
                peticion++;
            } catch (Exception e) {

                e.printStackTrace();
                System.out.println("Ha ocuerrido una excepcion, se acabo la prueba");
                return;
            }

            if (peticion >= maxPeticiones) {
                bandera = false;
            }
        }

        long endTime = System.nanoTime();

        System.out.println("se acabo la prueba con " + String.valueOf(maxPeticiones) + " peticiones\nSe demoró " + (endTime - startTime) / 1000000 + " milisegundos");
        return;

    }

    private String randomString() {


        byte[] array = new byte[10];
        new Random().nextBytes(array);

        String generatedString = new String(array, Charset.forName("US-ASCII"));


        return generatedString;
    }




}
