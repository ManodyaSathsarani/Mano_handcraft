package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.EmployeeManagementDto;
import lk.ijse.mano_handcraft.dto.tm.EmployeeManagementTM;
import lk.ijse.mano_handcraft.model.EmployeeMnagementModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtRole;
    public TextField txtHireDate ;
    public TextField txtPhone;
    public TextField txtAddress;

    public TableView<EmployeeManagementTM> tblEmployeeManagement;
    public TableColumn<EmployeeManagementTM, String> colId;
    public TableColumn<EmployeeManagementTM, String> colName;
    public TableColumn<EmployeeManagementTM, String> colRole;
    public TableColumn<EmployeeManagementTM, String> colHireDate;
    public TableColumn<EmployeeManagementTM, String> colPhone;
    public TableColumn<EmployeeManagementTM, String> colAddress;


    private final EmployeeMnagementModel employeeMnagementModel = new EmployeeMnagementModel();

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
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
        tblEmployeeManagement.setItems(FXCollections.observableArrayList(
                employeeMnagementModel.getAllemployee()
                        .stream()
                        .map(employeeManagementDto -> new EmployeeManagementTM(
                                employeeManagementDto.getEmployee_Id(),
                                employeeManagementDto.getName(),
                                employeeManagementDto.getRole(),
                                employeeManagementDto.getHire_date(),
                                employeeManagementDto.getPhone(),
                                employeeManagementDto.getAddress()
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
            txtRole.setText(null);
            txtHireDate.setText(null);
            txtPhone.setText(null);
            txtAddress.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String employeeId = lblId.getText();
        String employeeName = txtName.getText();
        String employeeRole = txtRole.getText();
        String employeeHireDate = txtHireDate.getText();
        String employeePhone = txtPhone.getText();
        String employeeAddress = txtAddress.getText();

        EmployeeManagementDto employeeManagementDto = new EmployeeManagementDto(
                employeeId,
                employeeName,
                employeeRole,
                employeeHireDate,
                employeePhone,
                employeeAddress
        );
        try {
            boolean isSaved = employeeMnagementModel.saveEmployee(employeeManagementDto);

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
        String employeeId = lblId.getText();
        String employeeName = txtName.getText();
        String employeeRole = txtRole.getText();
        String employeeHireDate = txtHireDate.getText();
        String employeePhone = txtPhone.getText();
        String employeeAddress = txtAddress.getText();

        EmployeeManagementDto employeeManagementDto = new EmployeeManagementDto(
                employeeId,
                employeeName,
                employeeRole,
                employeeHireDate,
                employeePhone,
                employeeAddress
        );
        try {
            boolean isUpdated = employeeMnagementModel.updateEmployee(employeeManagementDto);
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
            String employeeId = lblId.getText();
            try {
                boolean isDeleted = employeeMnagementModel.deleteEmployee(employeeId);
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
        String nextId = employeeMnagementModel.getEmployeeId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        EmployeeManagementTM selectedItem = tblEmployeeManagement.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getEmployee_Id());
            txtName.setText(selectedItem.getName());
            txtRole.setText(selectedItem.getRole());
            txtHireDate.setText(selectedItem.getHire_date());
            txtPhone.setText(selectedItem.getPhone());
            txtAddress.setText(selectedItem.getAddress());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }
}
