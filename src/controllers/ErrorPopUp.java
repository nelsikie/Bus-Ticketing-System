package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ErrorPopUp {

  public Button closeButton = new Button();
  public Label errorLabel = new Label();
  public Stage errorWindow = new Stage();

    public ErrorPopUp(String errorMSG) {
      System.out.print(errorMSG + " Inside method");
        errorWindow.initModality(Modality.APPLICATION_MODAL);
        errorWindow.setHeight(250);
        errorWindow.setWidth(250);

        errorLabel.setText(errorMSG);
        closeButton.setText("OK");
        closeButton.setOnAction(e-> errorWindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(errorLabel, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        errorWindow.setScene(scene);
        errorWindow.showAndWait();

    }

    // This is linked to the FXML file thats why it doesn't work with your custom code
    public void okOnAction(ActionEvent actionEvent) {
        errorWindow.close();
    }


}

