package database;

import Model.busRoute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class readDBCalls {

    public boolean loginCorrect = false;
    public ConnectDB CB = new ConnectDB();
    public ResultSet rs = null;
    public ObservableList<String> busRouteSchedule = FXCollections.observableArrayList();
    public ObservableList<busRoute> oblist = FXCollections.observableArrayList();
    public ObservableList<Integer> test = FXCollections.observableArrayList();

    public boolean loginAction(String userName, String passWord) throws SQLException {
        try {
            Connection C = CB.ConnectToDB();
            rs = C.createStatement().executeQuery("SELECT * from user where user =\"" + userName + "\" AND password =\"" + passWord + "\"");
            if (rs.next()) {
                loginCorrect = true;
            } else {
                System.out.println("LOGIN CREDENTIALS INCORRECT");
                loginCorrect = false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
        }
        return loginCorrect;
    }
}