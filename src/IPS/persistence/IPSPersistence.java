package IPS.persistence;

import model.IPSData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IPSPersistence implements IIPSPersistence{

    private String configFileName;

    /**
     * inicializa el valor del archivo de configuración
     * @param configFileName
     */
    public IPSPersistence(String configFileName)
    {
        this.configFileName = configFileName;
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
}
