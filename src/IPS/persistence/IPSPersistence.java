package IPS.persistence;

import model.IPSData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IPSPersistence implements IIPSPersistence{

    private String configFileName;
    private String stateFileName;

    /**
     * inicializa el valor del archivo de configuración
     * @param configFileName
     */
    public IPSPersistence(String configFileName, String stateFileName)
    {
        this.configFileName = configFileName;
        this.stateFileName = stateFileName;
    }

    @Override
    public List<Integer> readConfigFile() {

        List<Integer> initialConfig = new ArrayList<>();

        Scanner fileReader = null;

        try {
            File ipsFile = new File(configFileName);
            fileReader = new Scanner(ipsFile);
            while( fileReader.hasNextLine() )
            {

                String line = fileReader.nextLine().trim();
                String[] arrOfStr = line.split(":", 2);
                int value = Integer.parseInt(arrOfStr[1].trim());
                initialConfig.add(value);

            }

            fileReader.close();


        }catch (FileNotFoundException e)
        {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();

        }

        return initialConfig;
    }

    public void saveState(int vacunaA, int vacunaB, int vacunaC, int minVacunas, int peticion) {

        try {
            FileWriter myWriter = new FileWriter("src/IPS/tests/testConfig.txt");
            myWriter.write("vac1: " + vacunaA + "\n" +
                    "vac2: " + vacunaB + "\n" +
                    "vac3: " + vacunaC + "\n" +
                    "min: " + minVacunas + "\n" +
                    "peticion: " + peticion + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
