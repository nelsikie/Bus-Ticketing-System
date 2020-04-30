package database;

import java.sql.*;

public class readDBCalls {

    public boolean loginCorrect = false;
    public ConnectDB CB = new ConnectDB();
    public ResultSet rs = null;

    public boolean loginAction(String userName, String passWord) throws SQLException {
        try {
            Connection C = CB.ConnectToDB();
            rs = C.createStatement().executeQuery("SELECT * from user where user =\"" + userName + "\" AND password =\"" + passWord + "\"");
            if (rs.next()) {
                System.out.println("User & password correct");
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
