package controllers.subTabs;

import Model.bus;
import Model.busRoute;
import controllers.ErrorPopUp;
import database.ConnectDB;
import database.readDBCalls;
import database.writeDBCalls;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubtabBusManagement {
    public final ToggleGroup originGroup = new ToggleGroup();
    public final ToggleGroup destinationGroup = new ToggleGroup();
    public readDBCalls RDBC = new readDBCalls();
    public ConnectDB CDB = new ConnectDB();
    public ObservableList<busRoute> BR = FXCollections.observableArrayList();
    public ChoiceBox busSelection = new ChoiceBox();
    public ObservableList<bus> BUS = FXCollections.observableArrayList();
    public RadioButton BM_Origin_AlbanyNY;
    public RadioButton BM_Origin_NashVilleTN;
    public TextField BM_Hour;
    public TextField BM_Minutes;
    public TextField BM_BusDistance;
    public Button BM_AddBus;
    public Button BM_FetchData;
    public Button BM_UpdateData;
    public Button BM_RemoveBus;
    public TextField BM_BusName_Field;
    public TextField BM_BusID_Field;
    public RadioButton BM_Destination_Montreal;
    public RadioButton BM_Destination_NewYork;
    public int busRouteID = 1;
    @FXML
    private TableView<busRoute> busScheduleOverview;
    @FXML
    private TableColumn<busRoute, Integer> busIDTable;
    @FXML
    private TableColumn<busRoute, String> busNameTable;
    @FXML
    private TableColumn<busRoute, String> busOriginTable;
    @FXML
    private TableColumn<busRoute, String> busDestinationTable;
    @FXML
    private TableColumn<busRoute, Integer> busDepartureTimeTable;
    @FXML
    private TableColumn<busRoute, Integer> busDistanceTable;

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        BM_Origin_AlbanyNY.setSelected(true);
        BM_Destination_Montreal.setSelected(true);

        // Radio buttons [Origin] / Temporary Radio buttons for [Destination]
        BM_Origin_AlbanyNY.setUserData("Albany");
        BM_Origin_NashVilleTN.setUserData("NashVille");
        BM_Origin_AlbanyNY.setToggleGroup(originGroup);
        BM_Origin_NashVilleTN.setToggleGroup(originGroup);
        BM_Destination_Montreal.setUserData("Montreal");
        BM_Destination_NewYork.setUserData("New York");
        BM_Destination_Montreal.setToggleGroup(destinationGroup);
        BM_Destination_NewYork.setToggleGroup(destinationGroup);
        loadAvailableBusses();
    }

    public void buttonAddBus(ActionEvent event) throws SQLException, IOException {
        if (BM_BusID_Field.getText().equals("") || BM_BusName_Field.getText().isEmpty() || BM_Hour.getText().isEmpty() || BM_Minutes.getText().isEmpty() || BM_BusDistance.getText().isEmpty()) {
            ErrorPopUp error = new ErrorPopUp("You need to enter a value");
        } else {
            String selectedBusInsert = busSelection.getSelectionModel().selectedIndexProperty().toString();
            int BusID = Integer.parseInt(BM_BusID_Field.getText());
            String BusName = BM_BusName_Field.getText();

            String selectedOrigin = originGroup.getSelectedToggle().getUserData().toString();
            String selectedDestination = destinationGroup.getSelectedToggle().getUserData().toString();

            String Hour = BM_Hour.getText();
            String Minutes = BM_Minutes.getText();
            String BusTime = Hour + ":" + Minutes;

            writeDBCalls WDBC = new writeDBCalls();
            selectedOrigin = originGroup.getSelectedToggle().getUserData().toString();
            selectedDestination = originGroup.getSelectedToggle().getUserData().toString();
            int BusDistance = Integer.parseInt(BM_BusDistance.getText());
            WDBC.createBusRoute(busRouteID, BusID, BusName, selectedOrigin, selectedDestination, BusTime, BusDistance);
        }
    }

    public void loadAvailableBusses() throws SQLException, ClassNotFoundException {
        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("select * FROM bus");
        while (rs.next()) {
            BUS.add(new bus(rs.getInt("idBus"), rs.getString("busName")));
        }
        BUS.forEach(bus -> {
            busSelection.getItems().add("ID: " + bus.getIdBus() + " Name: " + bus.getBusName());
        });
    }

    public void buttonFetchBusRouteData(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String selectedBusInsert = busSelection.getValue().toString();
        System.out.println(selectedBusInsert);

        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("select * FROM busroute");
        BR.clear();
        while (rs.next()) {
            BR.add(new busRoute(rs.getInt("busID"), rs.getString("busName"), rs.getString("busSourceStation"), rs.getString("busDestinationStation"), rs.getDate("busStartTime"), rs.getInt("busDistance")));
        }

        busScheduleOverview.setItems(BR);
        busIDTable.setCellValueFactory(new PropertyValueFactory<>("busID"));
        busNameTable.setCellValueFactory(new PropertyValueFactory<>("busName"));
        busOriginTable.setCellValueFactory(new PropertyValueFactory<>("busSourceStation"));
        busDestinationTable.setCellValueFactory(new PropertyValueFactory<>("busDestinationStation"));
        busDepartureTimeTable.setCellValueFactory(new PropertyValueFactory<>("busStartTime"));
        busDistanceTable.setCellValueFactory(new PropertyValueFactory<>("busDistance"));
    }

    public void buttonRemoveBus(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        System.out.println("remove bus button data");
        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery(""); // "DELETE FROM busroute WHERE busID='" + busID + "'" how to call busID in actionEvent method
    }

    public void buttonUpdateBusData(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        System.out.println("update bus button data");
        busScheduleOverview.getSelectionModel();
        Connection C = CDB.ConnectToDB(); // Move to top
        ResultSet rs = C.createStatement().executeQuery("Update busroute SET ");
    }

    public void onSortBusSchedule(SortEvent<TableView<busRoute>> tableViewSortEvent) {
    }
}
