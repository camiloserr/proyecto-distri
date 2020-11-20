package IPS.tests;

import IPS.persistence.IIPSPersistence;
import IPS.persistence.IPSPersistence;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IPSPersistenceUnitTests {

    /**
     * crea archivo de configuracion y compara con el resultado esperado
     */
    @Test
    public void leerArchivoDeConfiguracionSatisfactoriamente(){

        //crea el archivo de pruebas
        try {
            FileWriter myWriter = new FileWriter("src/IPS/tests/testConfig.txt");
            myWriter.write("vac1: 15\n" +
                            "vac2: 15\n" +
                            "vac3: 15\n" +
                            "min: 6\n" +
                            "peticion: 30\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //crea el persistence
        IIPSPersistence persistence = new IPSPersistence("src/IPS/tests/testConfig.txt",  "src/IPS/tests/vacunasIps.txt");
        List<Integer> res = persistence.readConfigFile();

        // crea el oraculo
        ArrayList<Integer> correctAnswer = new ArrayList<>();
        correctAnswer.add(15);
        correctAnswer.add(15);
        correctAnswer.add(15);
        correctAnswer.add(6);
        correctAnswer.add(30);
        assertEquals(res, correctAnswer);
    }


}
