package GCC.model;

public class IPSInfo {
    int port;
    String ip;
    String name;
    boolean active;

    public IPSInfo(int port, String ip, String name, boolean active) {

        this.port = port;
        this.ip = ip;
        this.name = name;
        this.active = active;
    }

    @Override
    public String toString() {
        return "IPSInfo{" +
                "port=" + port +
                ", ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }

    public IPSInfo(){

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj==null || obj.getClass()!=this.getClass()) return false;


        IPSInfo i = (IPSInfo) obj;
        if(this.getIp().equals(i.getIp()) &&
        this.getName().equals(i.getName()) &&
        this.getPort() == i.getPort() &&
        this.isActive()==i.isActive()){
            return true;
        }

        return false;
    }
}
