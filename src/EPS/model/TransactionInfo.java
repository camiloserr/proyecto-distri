//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EPS.model;

import java.util.List;

public class TransactionInfo {
    private List<UserInfo> users;
    private String state;
    private String id;

    public TransactionInfo(String id, List<UserInfo> users, String state) {
        this.id = id;
        this.users = users;
        this.state = state;
    }

    public List<UserInfo> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
