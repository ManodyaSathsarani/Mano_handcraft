package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.IngredientManagementDto;
import lk.ijse.mano_handcraft.dto.OrderManagementDto;
import lk.ijse.mano_handcraft.dto.tm.IngredientManagementTM;
import lk.ijse.mano_handcraft.dto.tm.OrderManagementTM;
import lk.ijse.mano_handcraft.model.InventoryManagementModel;
import lk.ijse.mano_handcraft.model.OrderManagementModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderManagementController implements Initializable {
    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<OrderManagementTM, String> colCustomerId;

    @FXML
    private TableColumn<OrderManagementTM, String> colEmployeeId;

    @FXML
    private TableColumn<OrderManagementTM, String> colId;

    @FXML
    private TableColumn<OrderManagementTM, String> colOrderDate;

    @FXML
    private TableColumn<OrderManagementTM, String> colRole;

    @FXML
    private Label lblId;

    @FXML
    private TableView<OrderManagementTM> tblOrderManagement;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtTotalAmount;

    private final OrderManagementModel orderManagementModel = new OrderManagementModel();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("colId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));


        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblOrderManagement.setItems(FXCollections.observableArrayList(
                orderManagementModel.getAllOrder()

                        .stream()
                        .map(orderManagementDto -> new OrderManagementTM(
                                orderManagementDto.getOrder_id(),
                                orderManagementDto.getCustomer_id(),
                                orderManagementDto.getEmployee_id(),
                                orderManagementDto.getOrder_date(),
                                orderManagementDto.getTotal_amount()
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

            txtCustomerId.setText(null);
            txtEmployeeId.setText(null);
            txtOrderDate.setText(null);
            txtTotalAmount.setText(null);










        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String userId = lblId.getText();
        String userCustomerId = txtCustomerId.getText();
        String userEmployeeId = txtEmployeeId.getText();
        String userOrderDate = txtOrderDate.getText();
        String userTotalAmount = txtTotalAmount.getText();





            OrderManagementDto orderManagementDto = new OrderManagementDto(
                userId,
                userCustomerId,
                userEmployeeId,
                userOrderDate,
                userTotalAmount

        );
        try {
            boolean isSaved = orderManagementModel.saveOrder(orderManagementDto);

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
        String userCustomerId = txtCustomerId.getText();
        String userEmployeeId = txtEmployeeId.getText();
        String userOrderDate = txtOrderDate.getText();
        String userTotalAmount = txtTotalAmount.getText();


        OrderManagementDto orderManagementDto = new OrderManagementDto(
                userId,
                userCustomerId,
                userEmployeeId,
                userOrderDate,
                userTotalAmount

        );
        try {
            boolean isUpdated = orderManagementModel.updateOrder(orderManagementDto);
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
            String order_Id = lblId.getText();
            try {
                boolean isDeleted = orderManagementModel.deleteOrder(order_Id);
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
        String nextId = orderManagementModel.getNextOrderId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        OrderManagementTM selectedItem = tblOrderManagement.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getOrder_id());



            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }

}
