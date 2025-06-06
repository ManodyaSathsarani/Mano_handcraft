package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.SupplierManagementDto;
import lk.ijse.mano_handcraft.dto.tm.SupplierManagementTM;
import lk.ijse.mano_handcraft.model.SupplierManagementModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierManagementController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SupplierManagementTM, String> colContactInformation;

    @FXML
    private TableColumn<SupplierManagementTM, String> colId;

    @FXML
    private TableColumn<SupplierManagementTM, String> colName;

    @FXML
    private Label lblId;

    @FXML
    private TableView<SupplierManagementTM> tblSuplier;

    @FXML
    private TextField txtContactInformation;

    @FXML
    private TextField txtName;


    private final SupplierManagementModel supplierManagementModel = new SupplierManagementModel();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colContactInformation.setCellValueFactory(new PropertyValueFactory<>("contact_info"));



        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblSuplier.setItems(FXCollections.observableArrayList(
                supplierManagementModel.getAllSupplier()

                        .stream()
                        .map(supplierManagementDto -> new SupplierManagementTM(
                                supplierManagementDto.getSupplier_id(),
                                supplierManagementDto.getSupplier_name(),
                                supplierManagementDto.getContact_info()

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

            txtName.clear();
            txtContactInformation.clear();
            lblId.setText("");










        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private boolean validateInputs() {
        String name = txtName.getText().trim();
        String contact = txtContactInformation.getText().trim();

        if (name.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Supplier name is required.");
            return false;
        }

        if (!name.matches("[A-Za-z ]{2,}")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Supplier name must contain only letters and spaces.");
            return false;
        }

        if (contact.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Contact information is required.");
            return false;
        }

        // Simple phone/email pattern check (optional)
        if (!contact.matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$") && !contact.matches("^.+@.+\\..+$")) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Enter a valid phone number or email.");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!validateInputs()) return;
        String userId = lblId.getText();
        String userName = txtName.getText();
        String userContactInformation = txtContactInformation.getText();






        SupplierManagementDto supplierManagementDto = new SupplierManagementDto(
                userId,
                userName,
                userContactInformation

        );
        try {
            boolean isSaved = supplierManagementModel.saveSupplier(supplierManagementDto);

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
        if (!validateInputs()) return;
        String userId = lblId.getText();
        String userName = txtName.getText();
        String userContactInformation = txtContactInformation.getText();


        SupplierManagementDto supplierManagementDto = new SupplierManagementDto(
                userId,
                userName,
                userContactInformation

        );
        try {
            boolean isUpdated = supplierManagementModel.updateSuppliier(supplierManagementDto);
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
            String supplier_id = lblId.getText();
            try {
                boolean isDeleted = supplierManagementModel.deleteSupplier(supplier_id);
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
        String nextId = supplierManagementModel.getNextSupplierrId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        SupplierManagementTM selectedItem = tblSuplier.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getSupplier_id());



            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }



}
