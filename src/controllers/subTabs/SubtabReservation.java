package controllers.subTabs;

import Model.bus;
import database.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubtabReservation {
    public ChoiceBox<String> ddStartStation = new ChoiceBox<>();
    public ChoiceBox<String> ddDestionationStation = new ChoiceBox<>();
    public ObservableList<String> availableLocations = FXCollections.observableArrayList();
    public ConnectDB CDB = new ConnectDB();
    public Button getBusDetails;
    public Button resetButton;
    public SubScene busDetails;
    public ChoiceBox ddAvailableBuses = new ChoiceBox();
    public Button loadButton;
    public TextField dateDay, dateMonth, dateYear;
    public Label seatNumber;
    public ObservableList<bus> BUS = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        availableLocations.add("Select a city");    // The list
        ddStartStation.setValue("Select a city");   // The actual drop down menu associated to available locations list
        ddDestionationStation.setValue("Select a city"); // The actual drop down menu associated to available locations list
        getLocations();
        loadAvailableBuses();

    }

    public void getLocations() throws ClassNotFoundException, SQLException {
        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("SELECT * from location");
        while(rs.next()) {
            availableLocations.add(rs.getString("locationName"));
        }
        ddStartStation.setItems(availableLocations);
        ddDestionationStation.setItems(availableLocations);
    }

    public void loadAvailableBuses() throws SQLException, ClassNotFoundException {
        System.out.println("loadAvailableBuses method");
        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("select * FROM bus");
        while(rs.next()) {
            BUS.add(new bus(rs.getInt("idBus"), rs.getString("busName"), rs.getInt("busSize")));
        }
        BUS.forEach(bus -> { ddAvailableBuses.getItems().add("ID: " + bus.getIdBus() + " Name: " + bus.getBusName() + " Seats: " + bus.getBusSize());

            System.out.println( bus.getIdBus() + bus.getBusSize() + bus.getBusName());
        });
    }

    public void resetButtonAction(ActionEvent actionEvent) {
        ddStartStation.setValue("Select a city");
        ddDestionationStation.setValue("Select a city");
        ddAvailableBuses.setValue("Select a bus");
        seatNumber.setText("");
        dateDay.setText("");
        dateMonth.setText("");
        dateYear.setText("");
    }
}
