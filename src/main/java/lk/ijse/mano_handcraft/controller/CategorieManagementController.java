package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.CatagorieManagementDto;
import lk.ijse.mano_handcraft.dto.CustomerManagementDto;
import lk.ijse.mano_handcraft.dto.tm.CatagorieManagementTM;
import lk.ijse.mano_handcraft.dto.tm.CustomerManagementTM;
import lk.ijse.mano_handcraft.dto.tm.UserListTM;
import lk.ijse.mano_handcraft.model.CatagorieManagementModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategorieManagementController implements Initializable {
    public Label lblId;
    public TextField txtName;

    //public TableView<CatagorieManagementTM> tblCategorieManagement;
    public TableColumn<CatagorieManagementTM, String> colId;
    public TableColumn<CatagorieManagementTM, String> colName;



    private final CatagorieManagementModel catagorieManagementModel = new CatagorieManagementModel();

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public TableView<CatagorieManagementTM> tblCatagorieManagement;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("catagorie_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("catagorie_name"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblCatagorieManagement.setItems(FXCollections.observableArrayList(
                catagorieManagementModel.getAllCatagaries()
                        .stream()
                        .map(catagorieManagementDto -> new CatagorieManagementTM(
                                catagorieManagementDto.getCatagorie_id(),
                                catagorieManagementDto.getCatagorie_name()
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


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    private boolean validateCategoryInput() {
        String name = txtName.getText();

        if (name == null || name.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Category name cannot be empty").show();
            return false;
        }

        if (!name.matches("^[A-Za-z ]+$")) {
            new Alert(Alert.AlertType.WARNING, "Category name must only contain letters and spaces").show();
            return false;
        }

        return true;
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!validateCategoryInput()) return;
        String id = lblId.getText();
        String name = txtName.getText();


        CatagorieManagementDto catagorieManagementDto = new CatagorieManagementDto(
                id,
                name
        );
        try {
            boolean isSaved = catagorieManagementModel.saveCtagorie(catagorieManagementDto);

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
        if (!validateCategoryInput()) return;
        String id = lblId.getText();
        String name = txtName.getText();


        CatagorieManagementDto catagorieManagementDto = new CatagorieManagementDto(
                id,
                name
        );
        try {
            boolean isUpdated = catagorieManagementModel.updateCategory(catagorieManagementDto);
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
                boolean isDeleted = catagorieManagementModel.deleteCatagorie(customerId);
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
        String nextId = catagorieManagementModel.getNextCatagorieId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        CatagorieManagementTM selectedItem = tblCatagorieManagement.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getCatagorie_id());
            txtName.setText(selectedItem.getCatagorie_name());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }


}
