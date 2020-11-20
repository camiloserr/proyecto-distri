package model;

import java.util.List;
import java.util.Objects;

public class IPSData {

    int port;
    String name;

    public IPSData(int port, String name, List<Integer> vacunas, int minVac) {
        this.port = port;
        this.name = name;
    }

    public IPSData() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        IPSData ipsData = (IPSData) o;
        return port == ipsData.port &&
                name.equals(ipsData.name);

    }
}
