package EPS.persistence;

import EPS.model.UserInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EPSPersistence implements IEPSPersistence {

    private String fileName;

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public List<UserInfo> readUsrFromFile(){
        List<UserInfo> users = new ArrayList<>();
        String newPath = "src/EPS/persistence/" + fileName + ".txt";
        File file = new File(newPath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String ln;
            while((ln = br.readLine()) != null) {
                String[] request = ln.split(" ");
                UserInfo auxUser = new UserInfo(Integer.parseInt(request[0]), request[1], request[2], request[3], Integer.parseInt(request[4]));
                users.add(auxUser);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean setUserInfo(List<UserInfo> newUsers){
        String path = "src/EPS/persistence/" + fileName + ".txt";
        try {
            File file = new File(path);
            BufferedWriter wr = new BufferedWriter(new FileWriter(file, false));
            String newFile = "";

            for(UserInfo auxUser : newUsers){
                newFile += auxUser.getUniqueId() + " ";
                newFile += auxUser.getName() + " ";
                newFile += auxUser.getLastName() + " ";
                newFile += auxUser.getState() + " ";
                newFile += auxUser.getVaccineRequested() + "\n";
            }
            wr.write(newFile);
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
