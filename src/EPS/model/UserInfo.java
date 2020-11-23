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

    /**
     * Recibe la información que compone a un usuario e instancia el objeto a partir de esa información
     * @param uniqueId
     * @param name
     * @param lastName
     * @param state
     * @param vaccineRequested
     */
    public UserInfo(int uniqueId, String name, String lastName, String state, int vaccineRequested) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.lastName = lastName;
        this.state = state;
        this.vaccineRequested = vaccineRequested;
    }

    /**
     * Getter del id del usuario
     * @return el id
     */
    public int getUniqueId() {
        return this.uniqueId;
    }

    /**
     * Setter del id del usuario
     * @param uniqueId
     */
    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Getter del nombre del usuario
     * @return el nombre
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter del nombre del usuario
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter del apellido del usuario
     * @return el apellido del usuario
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter del apellido del usuario
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter de la vacuna solicitada
     * @return la vacuna solicitada
     */
    public int getVaccineRequested() {
        return this.vaccineRequested;
    }

    /**
     * Setter de la vacuna solicitada
     * @param vaccineRequested
     */
    public void setVaccineRequested(int vaccineRequested) {
        this.vaccineRequested = vaccineRequested;
    }

    /**
     * Getter del estado en que se encuentra la vacuna solicitada (Pendiente/Recibida)
     * @return estado de la vacuna solicitada
     */
    public String getState() {
        return this.state;
    }

    /**
     * Setter del estado de la vacuna solicitada
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
}
