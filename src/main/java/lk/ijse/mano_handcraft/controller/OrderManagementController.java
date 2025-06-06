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
    private TableColumn<OrderManagementTM, String> colPrice;

    @FXML
    private TableColumn<OrderManagementTM, String> colProductId;

    @FXML
    private TableColumn<OrderManagementTM, String> colQuantity;

    @FXML
    private TableColumn<OrderManagementTM, String> colTotalAmount;

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
    private TextField txtPrice;

    @FXML
    private TextField txtProductId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTotalAmount;

    private final OrderManagementModel orderManagementModel = new OrderManagementModel();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("total_amount"));
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
        tblOrderManagement.setItems(FXCollections.observableArrayList(
                orderManagementModel.getAllOrder()

                        .stream()
                        .map(orderManagementDto -> new OrderManagementTM(
                                orderManagementDto.getOrder_id(),
                                orderManagementDto.getCustomer_id(),
                                orderManagementDto.getEmployee_id(),
                                orderManagementDto.getOrder_date(),
                                orderManagementDto.getTotal_amount(),
                                orderManagementDto.getProduct_id(),
                                orderManagementDto.getQuantity(),
                                orderManagementDto.getPrice()
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

            txtCustomerId.clear();
            txtEmployeeId.clear();
            txtOrderDate.clear();
            txtTotalAmount.clear();
            txtProductId.clear();
            txtQuantity.clear();
            txtPrice.clear();




        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    private boolean validateAllInputs() {
        String customerId = txtCustomerId.getText().trim();
        String employeeId = txtEmployeeId.getText().trim();
        String orderDate = txtOrderDate.getText().trim();
        String totalAmount = txtTotalAmount.getText().trim();
        String productId = txtProductId.getText().trim();
        String quantity = txtQuantity.getText().trim();
        String price = txtPrice.getText().trim();

        if (customerId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Customer ID is required.");
            return false;
        }

        if (employeeId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Employee ID is required.");
            return false;
        }

        if (orderDate.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Order Date is required.");
            return false;
        }

        try {
            java.time.LocalDate.parse(orderDate); // expects yyyy-MM-dd
        } catch (java.time.format.DateTimeParseException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Order Date must be in yyyy-MM-dd format.");
            return false;
        }

        if (totalAmount.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Total Amount is required.");
            return false;
        }

        try {
            double amount = Double.parseDouble(totalAmount);
            if (amount < 0) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Total Amount cannot be negative.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Total Amount must be a number.");
            return false;
        }

        return true;
    }

    // Helper alert method
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!validateAllInputs()) return;
        String userId = lblId.getText();
        String userCustomerId = txtCustomerId.getText();
        String userEmployeeId = txtEmployeeId.getText();
        String userOrderDate = txtOrderDate.getText();
        String userTotalAmount = txtTotalAmount.getText();
        String userProductId = txtProductId.getText();
        String userQuantity = txtQuantity.getText();
        String userPrice = txtPrice.getText();






            OrderManagementDto orderManagementDto = new OrderManagementDto(
                userId,
                userCustomerId,
                userEmployeeId,
                userOrderDate,
                userTotalAmount,
                userProductId,
                userQuantity,
                userPrice

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
        if (!validateAllInputs()) return;
        String userId = lblId.getText();
        String userCustomerId = txtCustomerId.getText();
        String userEmployeeId = txtEmployeeId.getText();
        String userOrderDate = txtOrderDate.getText();
        String userTotalAmount = txtTotalAmount.getText();
        String userProductId = txtProductId.getText();
        String userQuantity = txtQuantity.getText();
        String userPrice = txtPrice.getText();

        OrderManagementDto orderManagementDto = new OrderManagementDto(
                userId,
                userCustomerId,
                userEmployeeId,
                userOrderDate,
                userTotalAmount,
                userProductId,
                userQuantity,
                userPrice

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
            txtCustomerId.setText(selectedItem.getCustomer_id());
            txtEmployeeId.setText(selectedItem.getEmployee_id());
            txtOrderDate.setText(selectedItem.getOrder_date());
            txtTotalAmount.setText(selectedItem.getTotal_amount());
            txtProductId.setText(selectedItem.getProduct_id());
            txtQuantity.setText(selectedItem.getQuantity());
            txtPrice.setText(selectedItem.getPrice());


            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }

}
