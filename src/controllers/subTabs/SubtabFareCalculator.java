package controllers.subTabs;

import controllers.ErrorPopUp;
import database.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubtabFareCalculator {

    public ChoiceBox<String> busSourceStation = new ChoiceBox<String>();
    public ChoiceBox<String> busDestinationStation = new ChoiceBox<String>();
    public ConnectDB CBD = new ConnectDB();
    public ObservableList<String> optionsTest = FXCollections.observableArrayList();
    public Button button_CalculateMyFare = new Button();
    public Label travelTime = new Label();
    public Label totalDistance= new Label();
    public Label busFare = new Label();
    public Label ReservationCharge = new Label();
    public Label OtherServiceCharges = new Label();
    public Label grandTotal = new Label();
    public String selectedBusSource, selectedBusDestination;

    public void initialize() throws SQLException, ClassNotFoundException {
        optionsTest.add("Select city");
        busSourceStation.setValue("Select city");
        busDestinationStation.setValue("Select city");
        getLocations();
    }

    // Method needs to be set in general location. Used somewhere else
    public void getLocations() throws ClassNotFoundException, SQLException {
        Connection C = CBD.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("SELECT * from location");
        while(rs.next()) {
            optionsTest.add(rs.getString("locationName"));
        }
        busSourceStation.setItems(optionsTest);
        busDestinationStation.setItems(optionsTest);
    }

    public void calculateFare(ActionEvent action) throws IOException {
        if(busSourceStation.getValue().equals("Albany") && busDestinationStation.getValue().equals("Montreal")) {
            travelTime.setText("2 hours");
            totalDistance.setText("200 KM");
            busFare.setText("20");
            ReservationCharge.setText("$5");
            OtherServiceCharges.setText("$2.50");
            grandTotal.setText("27.50");
        } if(busSourceStation.getValue().equals("Albany") && busDestinationStation.getValue().equals("Nashville")) {
            travelTime.setText("8 hours");
            totalDistance.setText("800 KM");
            busFare.setText("$100");
            ReservationCharge.setText("$5");
            OtherServiceCharges.setText(" $2.50");
            grandTotal.setText("$107.50");
        } if(busSourceStation.getValue().equals("Albany") && busDestinationStation.getValue().equals("New York")) {
            System.out.println("Doesn't hit");
            travelTime.setText("6 hours");
            totalDistance.setText("600 KM");
            busFare.setText("$60");
            ReservationCharge.setText("$5");
            OtherServiceCharges.setText("$2.50");
            grandTotal.setText("$67.50");
        } if(busSourceStation.getValue().equals("Albany") && busDestinationStation.getValue().equals("Albany")) {
            ErrorPopUp error = new ErrorPopUp("You need to select a different city");
        } if(busSourceStation.getValue().equals("Select city") || busDestinationStation.getValue().equals("Select city")) {
            ErrorPopUp error = new ErrorPopUp("Please select a city");
        }
    }
}