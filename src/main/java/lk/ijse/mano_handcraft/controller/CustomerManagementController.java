package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.CustomerManagementDto;
import lk.ijse.mano_handcraft.dto.UserListDto;
import lk.ijse.mano_handcraft.dto.tm.CustomerManagementTM;
import lk.ijse.mano_handcraft.dto.tm.UserListTM;
import lk.ijse.mano_handcraft.model.CustomerManagementModel;
import lk.ijse.mano_handcraft.model.UserListModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerManagementController implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtAddress;

    public TableView<CustomerManagementTM> tblCustomer;
    public TableColumn<CustomerManagementTM, String> colId;
    public TableColumn<CustomerManagementTM, String> colName;
    public TableColumn<CustomerManagementTM, String> colPhone;
    public TableColumn<CustomerManagementTM, String> colAddress;


    private final CustomerManagementModel customerManagementModel = new CustomerManagementModel();

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblCustomer.setItems(FXCollections.observableArrayList(
                customerManagementModel.getAllCustomers()
                        .stream()
                        .map(customerManagementDto -> new CustomerManagementTM(
                                customerManagementDto.getCustomer_Id(),
                                customerManagementDto.getName(),
                                customerManagementDto.getPhone(),
                                customerManagementDto.getAddress()
                        )).toList()
        ));
    }

    private void resetPage() {
        try {
            loadTableData();
            loadNextId();

            btnSave.setDisable(false);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);

            txtName.setText(null);
            txtPhone.setText(null);
            txtAddress.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String customer = lblId.getText();
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();

        //

        CustomerManagementDto customerManagementDto = new CustomerManagementDto(
                customer,
                name,
                phone,
                address
        );
        try {
            boolean isSaved = customerManagementModel.saveCustomer(customerManagementDto);

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
        String customerId = lblId.getText();
        String customerName = txtName.getText();
        String customerPhone = txtPhone.getText();
        String customerAddress = txtAddress.getText();

        //

        CustomerManagementDto customerDto = new CustomerManagementDto(
                customerId,
                customerName,
                customerPhone,
                customerAddress
        );
        try {
            boolean isUpdated = customerManagementModel.updateCustomer(customerDto);
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
            String customerId = lblId.getText();
            try {
                boolean isDeleted = customerManagementModel.deleteCustomer(customerId);
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
        String nextId = customerManagementModel.getNextCustomerId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        CustomerManagementTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getCustomer_Id());
            txtName.setText(selectedItem.getName());
            txtPhone.setText(selectedItem.getPhone());
            txtAddress.setText(selectedItem.getAddress());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }
}
