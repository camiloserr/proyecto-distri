package EPS.persistence;

import EPS.model.UserInfo;

import java.util.List;

public interface IEPSPersistence {

    void setFileName(String fileName);

    List<UserInfo> readUsrFromFile();

    void setUserInfo(List<UserInfo> newUsers);
}
