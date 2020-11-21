package IPS.persistence;



import IPS.model.IPSData;

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
    private String ipsFileName;
    /**
     * inicializa el valor del archivo de configuración
     * @param configFileName
     */
    public IPSPersistence(String configFileName, String stateFileName, String ipsFileName)
    {
        this.configFileName = configFileName;
        this.stateFileName = stateFileName;
        this.ipsFileName = ipsFileName;

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

    @Override
    public IPSData readIPSFile() {
        IPSData ips = new IPSData();

        Scanner fileReader = null;

        try {
            File ipsFile = new File(ipsFileName);
            fileReader = new Scanner(ipsFile);


            String data = fileReader.nextLine();
            data.trim();
            ips.setName(data);
            data = fileReader.nextLine();
            data.trim();
            String[] arrOfStr2 = data.split(":", 2);
            ips.setPort( Integer.parseInt(arrOfStr2[1].trim()) );
            fileReader.close();


        }catch (FileNotFoundException e)
        {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();

        }

        return ips;
    }

    @Override
    public void saveState(int vacunaA, int vacunaB, int vacunaC, int minVacunas, int peticion) {

        try {
            FileWriter myWriter = new FileWriter(stateFileName);
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
