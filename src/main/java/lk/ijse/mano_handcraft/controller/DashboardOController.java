package lk.ijse.mano_handcraft.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardOController {

    @FXML
    private Pane sidePane;

    @FXML
    private AnchorPane ancDashBoardA;

    @FXML
    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/UserList.fxml"));
        sidePane.getChildren().add(load);

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
    }

    public void btnEmployeeManagementOnAction(ActionEvent actionEvent) {
    }

    @FXML
    void btnLogoutOnAction() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            ancDashBoardA.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
            ancDashBoardA.getChildren().add(load);
        }
    }

}
