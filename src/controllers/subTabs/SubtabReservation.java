package controllers.subTabs;

import Model.bus;
import database.ConnectDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class SubtabReservation {
    // Images
    private static final Image busSeatAvailable = new Image(SubtabReservation.class.getResourceAsStream("/resources/images/busSeatEmpty.png"));
    private static final Image busSeatTaken = new Image(SubtabReservation.class.getResourceAsStream("/resources/images/busSeatTaken.png"));
    public ChoiceBox<String> ddStartStation = new ChoiceBox<>();
    public ChoiceBox<String> ddDestionationStation = new ChoiceBox<>();
    public ObservableList<String> availableLocations = FXCollections.observableArrayList();
    public ConnectDB CDB = new ConnectDB();
    public Button getBusDetails;
    public Button resetButton;
    public ChoiceBox ddAvailableBuses = new ChoiceBox();
    public Button loadButton;
    public TextField dateDay, dateMonth, dateYear;
    public Label seatNumber;
    public ObservableList<bus> BUS = FXCollections.observableArrayList();
    public ImageView busViewImageView;
    public AnchorPane busReservationAnchorPane;
    public GridPane busSeatImageGridPane;
    public boolean busSeatAvailability; // true = available, false = not available
    // TESTING
    public int busSize = 10;
    public int busRows = 2;
    public ImageView[] busSeats;

    public void initialize() throws SQLException, ClassNotFoundException {
        availableLocations.add("Select a city");    // The list
        ddStartStation.setValue("Select a city");   // The actual drop down menu associated to available locations list
        ddDestionationStation.setValue("Select a city"); // The actual drop down menu associated to available locations list
        getLocations();
        loadAvailableBuses();

        busSeats = new ImageView[busSize];
        for (int i = 0; i < busSize; i++) {
            ImageView singleBusSeat = new ImageView(busSeatAvailable);
            singleBusSeat.setFitWidth(50);
            singleBusSeat.setFitHeight(50);
            busSeats[i] = singleBusSeat;

            busSeatImageGridPane.add(busSeats[i], i % busSize, i % busRows);
            singleBusSeat.setOnMousePressed(mouseEvent -> {
                if (singleBusSeat.getImage() == busSeatAvailable) {
                    singleBusSeat.setImage(busSeatTaken);
                    System.out.println(Arrays.asList(busSeats).indexOf(singleBusSeat));
                } else {
                    singleBusSeat.setImage(busSeatAvailable);
                    System.out.println(Arrays.asList(busSeats).indexOf(singleBusSeat));
                }
            });
        }
    }

    public void getLocations() throws ClassNotFoundException, SQLException {
        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("SELECT * from location");
        while (rs.next()) {
            availableLocations.add(rs.getString("locationName"));
        }
        ddStartStation.setItems(availableLocations);
        ddDestionationStation.setItems(availableLocations);
    }

    public void loadAvailableBuses() throws SQLException, ClassNotFoundException {
        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("select * FROM bus");
        while (rs.next()) {
            BUS.add(new bus(rs.getInt("idBus"), rs.getString("busName"), rs.getInt("busSize")));
        }
        BUS.forEach(bus -> {
            ddAvailableBuses.getItems().add("ID: " + bus.getIdBus() + " Name: " + bus.getBusName());
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

    public void loadBusAction(ActionEvent actionEvent) throws IOException {
        busViewImageView.setFitHeight(50);
        busViewImageView.setFitWidth(50);
        busViewImageView.setImage(busSeatAvailable);
        busSeatAvailability = true;
    }

    public void onClicked(MouseEvent mouseEvent) {
    }
}
