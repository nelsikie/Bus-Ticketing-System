package controllers;

import database.readDBCalls;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class BusReservationSystem {

    public Label welcomeUser;
    public Button sqlButton;
    readDBCalls RDBC = new readDBCalls();
    LoginController LC = new LoginController();

    public void dragEvent(MouseEvent mouseEvent) {
        System.out.println("Mouse dragged");
    }

    public void selectReservation(Event event) {
    }

    public void selectBusManagement(Event event) {
    }

    public void selectAdmin(Event event) {
    }

    public void selectFareCalculator(Event event) {
    }

    public void selectTickets(Event event) {
    }
}