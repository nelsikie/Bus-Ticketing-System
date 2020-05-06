package controllers.subTabs;

import Model.busRoute;
import database.ConnectDB;
import database.readDBCalls;
import database.writeDBCalls;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubtabBusManagement {
    public readDBCalls RDBC = new readDBCalls();
    public ConnectDB CDB = new ConnectDB();
    ObservableList<busRoute> BR = FXCollections.observableArrayList();
    @FXML private TableView<busRoute> busScheduleOverview;
    @FXML private TableColumn<busRoute, Integer> busIDTable;
    @FXML private TableColumn<busRoute, String> busNameTable;
    @FXML private TableColumn<busRoute, Integer> busOriginTable;
    @FXML private TableColumn<busRoute, Integer> busDestinationTable;
    @FXML private TableColumn<busRoute, Integer> busDepartureTimeTable;
    @FXML private TableColumn<busRoute, Integer> busDistanceTable;

    // DEZE LATER TOEVOEGEN. DEZE HAALT DE HOOGSTE userID WAARDE OP. MOET HET IN SQL GOOIEN
    // SELECT MAX(iduser) from appdb.user;
    public Button busRouteRefresh;
    public RadioButton BM_Origin_AlbanyNY;
    public RadioButton BM_Origin_NashVilleTN;
    public TextField BM_Hour;
    public TextField BM_Minutes;
    public Button BM_AddBus;
    public Button BM_FetchData;
    public Button BM_UpdateData;
    public Button BM_RemoveBus;

    public TextField BM_BusName_Field;
    public TextField BM_BusID_Field;
    public RadioButton BM_Destination_Montreal;
    public RadioButton BM_Destination_NewYork;
    public int busRouteID = 1;
    public int selectedOriginSetINT;
    public int selectedDestinationSetINT;
    public final ToggleGroup originGroup = new ToggleGroup();
    public final ToggleGroup destinationGroup = new ToggleGroup();

    @FXML
    public void initialize() {
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
    }

    public void buttonAddBus(ActionEvent actionEvent) throws SQLException {
        if(BM_BusID_Field.getText().equals("") || BM_BusName_Field.getText().isEmpty() || BM_Hour.getText().isEmpty() || BM_Minutes.getText().isEmpty()) {
            System.out.println("You need to enter a value");
        } else {

            int BusID = Integer.parseInt(BM_BusID_Field.getText());
            String BusName = BM_BusName_Field.getText();

            String selectedOrigin = originGroup.getSelectedToggle().getUserData().toString();
            String selectedDestination = destinationGroup.getSelectedToggle().getUserData().toString();

            String Hour = BM_Hour.getText();
            String Minutes = BM_Minutes.getText();
            String BusTime = Hour + ":" + Minutes;

            writeDBCalls WDBC = new writeDBCalls();
            switch (selectedOrigin) {
                case "Albany":
                    selectedOriginSetINT = 1;
                    break;
                case "NashVille":
                    selectedOriginSetINT = 3;
                    break;
            }
            switch (selectedDestination) {
                case "Montreal":
                    selectedDestinationSetINT = 2;
                    break;
                case "New York":
                    selectedDestinationSetINT = 4;
                    break;
            }
            WDBC.createBusRoute(busRouteID, BusID, BusName, selectedOriginSetINT, selectedDestinationSetINT, BusTime, 50);
        }
    }

    public void buttonFetchBusData(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println("fetch bus data button");

        Connection C = CDB.ConnectToDB();
        ResultSet rs = C.createStatement().executeQuery("select * FROM busroute");
        BR.clear();
        while(rs.next()) {
            BR.add(new busRoute(rs.getInt("busID"), rs.getString("busName"), rs.getInt("busSourceStation"), rs.getInt("busDestinationStation"), rs.getDate("busStartTime"), rs.getInt("busDistance")));
        }


        busScheduleOverview.setItems(BR);
        busIDTable.setCellValueFactory(new PropertyValueFactory<>("busID"));
        busNameTable.setCellValueFactory(new PropertyValueFactory<>("busName"));
        busOriginTable.setCellValueFactory(new PropertyValueFactory<>("busSourceStation"));
        busDestinationTable.setCellValueFactory(new PropertyValueFactory<>("busDestinationStation"));
        busDepartureTimeTable.setCellValueFactory(new PropertyValueFactory<>("busStartTime"));
        busDistanceTable.setCellValueFactory(new PropertyValueFactory<>("busDistance"));
    }

    public void buttonUpdateBusData(ActionEvent actionEvent) {
        System.out.println("update bus button data");
    }

    public void buttonRemoveBus(ActionEvent actionEvent) {
        System.out.println("remove bus button data");
    }

    public void onSortBusSchedule(SortEvent<TableView> tableViewSortEvent) {
    }

    public void busRouteRefreshOverview(ActionEvent actionEvent) {
    }
}
