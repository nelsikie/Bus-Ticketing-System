package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public String URL = "jdbc:mysql://localhost:3306/appdb";
    public String USER = "root";
    public String PASSWORD = "W!zardsmag1c";
    public Connection con = null;

    public Connection ConnectToDB() throws ClassNotFoundException {

        System.out.println("connecting to server... ");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to server... ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void closeConnection() throws SQLException {
        con.close();
    }
}
