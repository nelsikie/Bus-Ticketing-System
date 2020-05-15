package Model;

public class bus {
    int idBus;
    String busName;
    int busSize;

    public bus(int idBus, String busName, int busSize) {
        this.idBus = idBus;
        this.busName = busName;
        this.busSize = busSize;
    }

    public bus(String busName) {
        this.busName = busName;
    }

    public bus(int idBus) {
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getBusSize() {
        return busSize;
    }

    public void setBusSize(int busSize) {
        this.busSize = busSize;
    }
}
