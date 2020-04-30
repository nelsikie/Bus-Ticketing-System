package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ErrorPopUp {

    public Button okButton;
    public Label errorLabel = null;

    public ErrorPopUp(String error) {
        errorLabel.setText(error);
    }

    public void okOnAction(ActionEvent actionEvent) {

    }
}
