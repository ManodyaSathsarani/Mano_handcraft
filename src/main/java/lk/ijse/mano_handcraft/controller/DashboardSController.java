package lk.ijse.mano_handcraft.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardSController {

    @FXML
    private Pane sidePane;

    @FXML
    private AnchorPane ancDashBoardS;



    @FXML
    void btnLogOutOnAction() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            ancDashBoardS.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
            ancDashBoardS.getChildren().add(load);
        }
    }

    @FXML
    void btnCatagorieManagementOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CategorieManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnInventoryManagementOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/InventoryManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnManagePaymentOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/PaymentManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnOrderDetailsOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OrderDetailsManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OrderManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    @FXML
    void btnProductManagementOnAction(ActionEvent event) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ProductManagement.fxml"));
        sidePane.getChildren().add(load);
    }
}
