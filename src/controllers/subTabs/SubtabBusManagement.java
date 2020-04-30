package controllers.subTabs;

import database.writeDBCalls;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class SubtabBusManagement {
    public TableColumn busIDTable;
    public TableColumn busNameTable;
    public TableColumn busOriginTable;
    public TableColumn busDestinationTable;
    public TableColumn busDepartureTimeTable;
    public TableColumn busDistanceTable;

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
    public TableView busScheduleOverview;
    public TextField BM_BusName_Field;
    public TextField BM_BusID_Field;
    public RadioButton BM_Destination_Montreal;
    public RadioButton BM_Destination_NewYork;
    public int busRouteID = 1;
    public int selectedOriginSetINT;
    public int selectedDestinationSetINT;

    @FXML
    public void initialize() {
        BM_Origin_AlbanyNY.setSelected(true);
        BM_Destination_Montreal.setSelected(true);
//        assignColumns();

    }

    public void buttonAddBus(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println("add bus button");
        int BusID = Integer.parseInt(BM_BusID_Field.getText());
        String BusName = BM_BusName_Field.getText();

        // Radio buttons [Origin]
        final ToggleGroup originGroup = new ToggleGroup();
        BM_Origin_AlbanyNY.setUserData("Albany");
        BM_Origin_NashVilleTN.setUserData("NashVille");
        BM_Origin_AlbanyNY.setToggleGroup(originGroup);
        BM_Origin_NashVilleTN.setToggleGroup(originGroup);

        String selectedOrigin = originGroup.getSelectedToggle().getUserData().toString();


        // Temporary Radio buttons for [Destination]
        final ToggleGroup destinationGroup = new ToggleGroup();
        BM_Destination_Montreal.setUserData("Montreal");
        BM_Destination_NewYork.setUserData("New York");
        BM_Destination_Montreal.setToggleGroup(destinationGroup);
        BM_Destination_NewYork.setToggleGroup(destinationGroup);

        String selectedDestination = destinationGroup.getSelectedToggle().getUserData().toString();

        String Hour = BM_Hour.getText();
        String Minutes = BM_Minutes.getText();
        String BusTime = Hour + ":" + Minutes;

        writeDBCalls WDBC = new writeDBCalls();
        //ConnectDB db = new ConnectDB();
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

        if (BM_BusName_Field.getText().isEmpty()) {
            System.out.println("Name not entered");
        } else {
            WDBC.createBusRoute(busRouteID, BusID, BusName, selectedOriginSetINT, selectedDestinationSetINT, BusTime, 50);
            // SQL Insert command. Print for now
            System.out.println("BusID: " + BusID + " Bus name " + BusName + "\n" +
                    "Origin: " + selectedOrigin + " to Destination : " + selectedDestination + " @ " + Hour + "H : " + Minutes + "M ");
        }


    }

    // Most likely needs to be set in the initialize method
//    public void assignColumns () {
//        busIDTable.setCellValueFactory(new PropertyValueFactory<>("busIDTable"));
//        busScheduleOverview.setItems(getData());
////        busNameTable;
////        busOriginTable;
////        busDestinationTable;
////        busDepartureTimeTable;
////        busDistanceTable;
//    }
//
//    private ObservableList getData() {
//        List list = new ArrayList();
//        list.add("test");
//        ObservableList data = FXCollections.observableArrayList(list);
//
//        return data;
//    }
    public void buttonFetchBusData(ActionEvent actionEvent) {
        System.out.println("fetch bus data button");
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
