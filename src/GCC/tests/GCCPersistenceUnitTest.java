package GCC.tests;

import GCC.model.IPSInfo;
import GCC.persistence.GCCPersistence;
import GCC.persistence.IGCCPersistence;
import org.junit.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GCCPersistenceUnitTest {


    /**
     * crea archivo de configuracion y compara con el resultado esperado
     */
    @Test
    public void leerArchivoDeConfiguracionSatisfactoriamente(){

        //crea el archivo de pruebas
        try {
            FileWriter myWriter = new FileWriter("src/gcc/tests/testConfig.txt");
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
        IGCCPersistence persistence = new GCCPersistence("src/gcc/tests/testConfig.txt", "src/gcc/tests/authentiaction.txt");
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
    public void retornarUsuarios(){

        //crea el archivo de pruebas
        String[] users = {"usuario1", "usuario2", "usuario3", "usuario4"};
        String[] pass = {"hash1", "hash2", "7e240de74fb1ed08fa08d38063f6a6a91462a815", "5cb138284d431abd6a053a56625ec088bfb88912"};
        try {
            FileWriter myWriter = new FileWriter("src/gcc/tests/testAuth.txt");
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
        IGCCPersistence persistence = new GCCPersistence("src/gcc/tests/testConfig.txt", "src/gcc/tests/testAuth.txt");

        Map<String, String> res = persistence.getUsers();

        assertEquals(res.size() == 4, true);
        for(int i = 0 ; i < 4 ; ++i){
            assertEquals(true, res.get(users[i]).equals(pass[i]));
        }
    }

    @Test
    public void createUserReturnsTrue(){
        //crea el archivo de pruebas
        try {
            FileWriter myWriter = new FileWriter("src/gcc/tests/testAuth.txt");
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
        IGCCPersistence persistence = new GCCPersistence("src/gcc/tests/testConfig.txt", "src/gcc/tests/testAuth.txt");

        // juan no deberia existir
        assertEquals(false, persistence.existsUser("juan"));
        // agrega un usuario
        boolean res = persistence.newUser("juan", "shadejuan" );
        assertEquals(true, res);

        assertEquals(true, persistence.existsUser("usuario1"));

        //verifica que el usuario este en el archivo ahora
        assertEquals(true, persistence.existsUser("juan"));

        assertEquals(true, persistence.existsUser("usuario1"));


        //verifica que no se pueda ingresar un usuario que ya existe
        assertEquals(false, persistence.newUser("juan", "shadejuan" ));

        assertEquals(true, persistence.existsUser("usuario1"));

    }

}
