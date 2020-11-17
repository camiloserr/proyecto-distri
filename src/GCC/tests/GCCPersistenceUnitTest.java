package GCC.tests;

import GCC.model.IPSInfo;
import GCC.persistence.GCCPersistence;
import GCC.persistence.IGCCPersistence;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GCCPersistenceUnitTest {


    /**
     * crea archivo de configuracion y compara con el resultado esperado
     */
    @Test
    public void leerArchivoDeConfiguracionSatisfactoriamente(){

        //crea el archivo de pruebas
        try {
            FileWriter myWriter = new FileWriter("testConfig.txt");
            myWriter.write("IPS1:\n" +
                    "    ip: localhost\n" +
                    "    port: 8888\n" +
                    "IPS2:\n" +
                    "    ip: localhost\n" +
                    "    port: 8889\n" +
                    "IPS3:\n" +
                    "    ip: localhost\n" +
                    "    port: 8881\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //crea el persistence
        IGCCPersistence persistence = new GCCPersistence("testConfig.txt", "authentiaction.txt");
        ArrayList<IPSInfo> res = persistence.readConfigFile();

        // crea el oraculo
        ArrayList<IPSInfo> correctAnswer = new ArrayList<>();
        correctAnswer.add(new IPSInfo(8888, "localhost", "IPS1", false));
        correctAnswer.add(new IPSInfo(8889, "localhost", "IPS2", false));
        correctAnswer.add(new IPSInfo(8881, "localhost", "IPS3", false));

        assertEquals(res, correctAnswer);
    }


    /**
     * crea archivo de autenticacoin y compara con el resultado esperado
     */
    @Test
    public void autenticarUsuarioSatisfactoriamente(){

        //crea el archivo de pruebas
        try {
            FileWriter myWriter = new FileWriter("testAuth.txt");
            myWriter.write("usuario1:hash1\n" +
                    "usuario2:hash2\n" +
                    "usuario3:7e240de74fb1ed08fa08d38063f6a6a91462a815\n" +
                    "usuario4:5cb138284d431abd6a053a56625ec088bfb88912\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //crea el persistence
        IGCCPersistence persistence = new GCCPersistence("testConfig.txt", "testAuth.txt");


        assertEquals(true,persistence.authenticateUser("usuario1", "hash1"));
        assertEquals(false,persistence.authenticateUser("usuarsdfadio1", "has98389h1"));
        assertEquals(true,persistence.authenticateUser("usuario3", "7e240de74fb1ed08fa08d38063f6a6a91462a815"));
    }

    @Test
    public void createUserReturnsTrue(){
        //crea el archivo de pruebas
        try {
            FileWriter myWriter = new FileWriter("testAuth.txt");
            myWriter.write("usuario1:hash1\n" +
                    "usuario2:hash2\n" +
                    "usuario3:7e240de74fb1ed08fa08d38063f6a6a91462a815\n" +
                    "usuario4:5cb138284d431abd6a053a56625ec088bfb88912\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //crea el persistence
        IGCCPersistence persistence = new GCCPersistence("testConfig.txt", "testAuth.txt");

        // agrega un usuario
        assertEquals(true, persistence.newUser("juan", "shadejuan" ));
        //verifica que el usuario este en el archivo ahora
        assertEquals(true, persistence.existsUser("juan"));
        //verifica que no se pueda ingresar un usuario que ya existe
        assertEquals(false, persistence.newUser("juan", "shadejuan" ));
    }




}
