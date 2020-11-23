//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package EPS.model;

public class UserInfo {
    private int uniqueId;
    private String name;
    private String lastName;
    private String state;
    private int vaccineRequested;

    public UserInfo(int uniqueId, String name, String lastName, String state, int vaccineRequested) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lastName = lastName;
        this.state = state;
        this.vaccineRequested = vaccineRequested;
    }

    public int getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getVaccineRequested() {
        return this.vaccineRequested;
    }

    public void setVaccineRequested(int vaccineRequested) {
        this.vaccineRequested = vaccineRequested;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
