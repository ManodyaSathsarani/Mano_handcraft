package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.OrderDetailsManagementDto;
import lk.ijse.mano_handcraft.dto.tm.OrderDetailsManagementTM;

import lk.ijse.mano_handcraft.model.OrderDetailsManagementModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderDetailsManagementController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<OrderDetailsManagementTM, String> colId;

    @FXML
    private TableColumn<OrderDetailsManagementTM, String> colOrderId;

    @FXML
    private TableColumn<OrderDetailsManagementTM, Double> colPrice;

    @FXML
    private TableColumn<OrderDetailsManagementTM, String> colProductId;

    @FXML
    private TableColumn<OrderDetailsManagementTM, String> colQuantity;

    @FXML
    private Label lblId;

    @FXML
    private TableView<OrderDetailsManagementTM> tblOrderDetailsManagement;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtQuantity;

    private final OrderDetailsManagementModel orderDetailsManagementModel = new OrderDetailsManagementModel();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colProductId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));




        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
       tblOrderDetailsManagement .setItems(FXCollections.observableArrayList(
                orderDetailsManagementModel.getAllOrderdetail()

                        .stream()
                        .map(orderDetailsManagementDto -> new OrderDetailsManagementTM(
                                orderDetailsManagementDto.getOrder_id(),
                                orderDetailsManagementDto.getOrder_id(),
                                orderDetailsManagementDto.getProduct_id(),
                                orderDetailsManagementDto.getQuantity(),
                                orderDetailsManagementDto.getPrice()
                        )).
                        toList()
        ));






    }

    private void resetPage() {
        try {

            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtOrderId.setText(null);
            txtProductId.setText(null);
            txtQuantity.setText(null);
            txtPrice.setText(null);













        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String userId = lblId.getText();
        String userOrderId = txtOrderId.getText();
        String userProductId = txtProductId.getText();
        String userQuantity = txtQuantity.getText();
        Double userPrice = Double.valueOf(txtPrice.getText());






        OrderDetailsManagementDto orderDetailsManagementDto = new OrderDetailsManagementDto(
                userId,
                userOrderId,
                userProductId,
                userQuantity,
                userPrice


        );
        try {
            boolean isSaved = orderDetailsManagementModel.saveOrderDetailsManagement(orderDetailsManagementDto);

            if (isSaved) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String userId = lblId.getText();
        String userOrderId = txtOrderId.getText();
        String userProductId = txtProductId.getText();
        String userQuantity = txtQuantity.getText();
        Double userPrice = Double.valueOf(txtPrice.getText());



        OrderDetailsManagementDto orderDetailsManagementDto = new OrderDetailsManagementDto(
                userId,
                userOrderId,
                userProductId,
                userQuantity,
                userPrice
        );
        try {
            boolean isUpdated = orderDetailsManagementModel.updateOrderDetailsManagement( orderDetailsManagementDto);
            if (isUpdated) {
                resetPage();
                new Alert(Alert.AlertType.INFORMATION, "Updated").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are You Sure ? ",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            String orderDetails_id = lblId.getText();
            try {
                boolean isDeleted = orderDetailsManagementModel.deleteOrderDetail(orderDetails_id);
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Deleted").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    private void loadNextId() throws SQLException, ClassNotFoundException {
        String nextId = orderDetailsManagementModel.getNextOrderdetails();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        OrderDetailsManagementTM selectedItem = (OrderDetailsManagementTM) tblOrderDetailsManagement.getSelectionModel().getSelectedItem();



        if (selectedItem != null) {
            lblId.setText(selectedItem.getOrder_id());



            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }






}
