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

    @FXML
    void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CustomerManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/EmployeeManagement.fxml"));
        sidePane.getChildren().add(load);



    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/SupplierManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    public void btnCatagorieOnAction(ActionEvent actionEvent) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/CategorieManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    public void btnInventoryOnAction(ActionEvent actionEvent) throws IOException {
        sidePane  .getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/InventoryManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    public void btnIngredientOnAction(ActionEvent actionEvent) throws IOException {
        sidePane  .getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/IngredientManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        sidePane.getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/OrderManagement.fxml"));
        sidePane.getChildren().add(load);
    }



    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        sidePane  .getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/PaymentManagement.fxml"));
        sidePane.getChildren().add(load);
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        sidePane  .getChildren().clear();
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/ProductManagement.fxml"));
        sidePane.getChildren().add(load);
    }
}
