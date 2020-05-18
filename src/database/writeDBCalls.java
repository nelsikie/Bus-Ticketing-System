package database;

import java.sql.Connection;
import java.sql.SQLException;

public class writeDBCalls {

    public boolean loginCorrect = false;
    public ConnectDB CB = new ConnectDB();
    public boolean cs = false;
    Connection C = null;

    public void createBusRoute(int busRouteID, int busID, String busName, String busSourceStation, String busDestination, String busStartTime, int Distance) throws SQLException {
        try {
            // BusRouteID hoeft niet meegegeven te worden is autoIncrement en Unique
            // Origin & Destination moeten een int zijn. Moet een conversie doen liggende aan selectie.

            // DE DATABASE MOET WORDEN AANGEPAST. JE MOET DE BUS TABEL AANMAKEN EN DAN EEN REFERENTIE NAAR DE BUS ROUTE.
            // WAARSCHIJNLIJK MOET DE TABLE OPNIEUW GEMAAKT WORDEN VOOR BUSROUTE
            C = CB.ConnectToDB();
            System.out.println("Should not print!!!!!");
            cs = C.createStatement().execute("INSERT INTO busRoute(busID, busName, busSourceStation, busDestinationStation, busStartTime, busDistance)" +
                    "VALUES('" + busID + "','" + busName + "', '" + busSourceStation + "', '" + busDestination + "', '" + busStartTime + "', '" + Distance + "')");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            C.close();
        }
    }
}


// IT WORKS BY WHY DOES IT RECONNECT TO THE SERVER AGAIN AFTER WANTING TO CLOSE THE CONNECTION?????

// Check if you can use execute updat instead