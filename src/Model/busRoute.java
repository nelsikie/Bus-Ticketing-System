package Model;

import java.util.Date;

public class busRoute {
    //int busRouteID;
    int busID;
    String busSourceStation;
    String busDestinationStation;
    Date busStartTime;
    int busDistance;
    String busName;

    public busRoute(int busID, String busName, String busSourceStation, String busDestinationStation, Date busStartTime, int busDistance) { // int busRouteID
        this.busID = busID;
        this.busName = busName;
        this.busSourceStation = busSourceStation;
        this.busDestinationStation = busDestinationStation;
        this.busStartTime = busStartTime;
        this.busDistance = busDistance;
    }

    public busRoute(int busID) {
        this.busID = busID;
    }

    public busRoute(String busName) {
        this.busName = busName;
    }

//    public int getBusRouteID() {
//        return busRouteID;
//    }

//    public void setBusRouteID(int busRouteID) {
//        this.busRouteID = busRouteID;
//    }

    public int getBusID() {
        return busID;
    }

    public void setBusID(int busID) {
        this.busID = busID;
    }

    public String getBusSourceStation() {
        return busSourceStation;
    }

    public void setBusSourceStation(String busSourceStation) {
        this.busSourceStation = busSourceStation;
    }

    public String getBusDestinationStation() {
        return busDestinationStation;
    }

    public void setBusDestinationStation(String busDestinationStation) {
        this.busDestinationStation = busDestinationStation;
    }

    public Date getBusStartTime() {
        return busStartTime;
    }

    public void setBusStartTime(Date busStartTime) {
        this.busStartTime = busStartTime;
    }

    public int getBusDistance() {
        return busDistance;
    }

    public void setBusDistance(int busDistance) {
        this.busDistance = busDistance;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }
}
