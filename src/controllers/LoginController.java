package controllers;

import database.readDBCalls;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public TextField userName;
    public PasswordField userPassword;

    public void onAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        readDBCalls RDBC = new readDBCalls();

        if (RDBC.loginAction(userName.getText(), userPassword.getText())) {
            Parent parentHomeView = FXMLLoader.load(getClass().getResource("../resources/BusReservationSystem.fxml"));
            Scene sceneHomeView = new Scene(parentHomeView);

            // This line get the stage information
            Stage stageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageWindow.setScene(sceneHomeView);
            stageWindow.show();
        }
    }
}
