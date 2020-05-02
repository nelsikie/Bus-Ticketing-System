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
            // SQL Insert command. Print for now
            System.out.println("BusID: " + BusID + " Bus name " + BusName + "\n" +
                    "Origin: " + selectedOrigin + " to Destination : " + selectedDestination + " @ " + Hour + "H : " + Minutes + "M ");
        }
    }

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
