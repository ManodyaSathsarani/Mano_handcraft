package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.OrderManagementDto;
import lk.ijse.mano_handcraft.dto.PaymentManagementDto;
import lk.ijse.mano_handcraft.dto.tm.OrderManagementTM;
import lk.ijse.mano_handcraft.dto.tm.PaymentManagementTM;
import lk.ijse.mano_handcraft.model.OrderManagementModel;
import lk.ijse.mano_handcraft.model.PaymentManagementModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentManagementController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PaymentManagementTM, String> colAmount;

    @FXML
    private TableColumn<PaymentManagementTM, String> colId;

    @FXML
    private TableColumn<PaymentManagementTM, String> colMethod;

    @FXML
    private TableColumn<PaymentManagementTM, String> colOrderId;

    @FXML
    private TableColumn<PaymentManagementTM, String> colPaymentDate;

    @FXML
    private TableColumn<PaymentManagementTM, String> colStatus;

    @FXML
    private Label lblId;

    @FXML
    private TableView<PaymentManagementTM> tblPaymentManagement;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMethod;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPaymentDate;

    @FXML
    private TextField txtStatus;


    private final PaymentManagementModel paymentManagementModel = new PaymentManagementModel();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("payment_date"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblPaymentManagement.setItems(FXCollections.observableArrayList(
                paymentManagementModel.getAllPayment()

                        .stream()
                        .map(paymentManagementDto -> new PaymentManagementTM(
                                paymentManagementDto.getPayment_id(),
                                paymentManagementDto.getOrder_id(),
                                paymentManagementDto.getAmount(),
                                paymentManagementDto.getMethod(),
                                paymentManagementDto.getStatus(),
                                paymentManagementDto.getPayment_date()
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

            lblId.setText("");
            txtOrderId.setText(null);
            txtAmount.setText(null);
            txtMethod.setText(null);
            txtStatus.setText(null);
            txtPaymentDate.setText(null);












        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    private boolean validateAllInputs() {
        String orderId = txtOrderId.getText().trim();
        String amount = txtAmount.getText().trim();
        String method = txtMethod.getText().trim();
        String status = txtStatus.getText().trim();
        String paymentDate = txtPaymentDate.getText().trim();

        if (orderId.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Order ID is required.");
            return false;
        }

        if (amount.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Amount is required.");
            return false;
        }
        try {
            double amt = Double.parseDouble(amount);
            if (amt < 0) {
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Amount cannot be negative.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Amount must be a valid number.");
            return false;
        }

        if (method.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Payment method is required.");
            return false;
        }

        if (status.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Status is required.");
            return false;
        }

        if (paymentDate.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Payment date is required.");
            return false;
        }
        try {
            java.time.LocalDate.parse(paymentDate);  // expects format yyyy-MM-dd
        } catch (java.time.format.DateTimeParseException e) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Payment date must be in yyyy-MM-dd format.");
            return false;
        }

        return true;
    }

    // Helper method for showing alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!validateAllInputs()) return;
        String userId = lblId.getText();
        String userOrderId = txtOrderId.getText();
        String userAmount = txtAmount.getText();
        String userMethod = txtMethod.getText();
        String userStatus = txtStatus.getText();
        String userPaymentDate = txtPaymentDate.getText();





        PaymentManagementDto paymentManagementDto = new PaymentManagementDto(
                userId,
                userOrderId,
                userAmount,
                userMethod,
                userStatus,
                userPaymentDate


        );
        try {
            boolean isSaved = paymentManagementModel.savePayment(paymentManagementDto);

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
        String userOrderId = txtOrderId.getText();
        String userAmount = txtAmount.getText();
        String userMethod = txtMethod.getText();
        String userStatus = txtStatus.getText();
        String userPaymentDate = txtPaymentDate.getText();


            PaymentManagementDto paymentManagementDto = new PaymentManagementDto(
                userId,
                userOrderId,
                userAmount,
                userMethod,
                userStatus,
                userPaymentDate

        );
        try {
            boolean isUpdated = paymentManagementModel.updatePayment(paymentManagementDto);
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
            String payment_id = lblId.getText();
            try {
                boolean isDeleted = paymentManagementModel.deletePayment(payment_id);
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
        String nextId = paymentManagementModel.getNextPaymentId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        PaymentManagementTM selectedItem = tblPaymentManagement.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getOrder_id());



            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }


}
