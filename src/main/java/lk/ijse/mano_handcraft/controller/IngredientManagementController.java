package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.IngredientManagementDto;
import lk.ijse.mano_handcraft.dto.tm.IngredientManagementTM;
import lk.ijse.mano_handcraft.model.IngredientManagementModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class IngredientManagementController implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtUnit;


    @FXML
    private TableView<IngredientManagementTM> tblIngredientManagement;

    public TableColumn<IngredientManagementTM, String> colId;
    public TableColumn<IngredientManagementTM, String> colName;
    public TableColumn<IngredientManagementTM, String> colUnit;


    private final  IngredientManagementModel ingredientManagementModel = new IngredientManagementModel();

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("ingredient_Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ingredient_Name"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));


        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblIngredientManagement.setItems(FXCollections.observableArrayList(
                ingredientManagementModel.getAllIngrediants()

                        .stream()
                        .map(ingredientManagementDto -> new IngredientManagementTM(
                                ingredientManagementDto.getIngredient_Id(),
                                ingredientManagementDto.getIngredient_Name(),
                                ingredientManagementDto.getUnit()
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

            txtName.setText(null);
            txtUnit.setText(null);








        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String userId = lblId.getText();
        String userName = txtName.getText();
        String userUnit  = txtUnit.getText();




        IngredientManagementDto ingredientManagementDto = new IngredientManagementDto(
                userId,
                userName,
                userUnit
        );
        try {
            boolean isSaved = ingredientManagementModel.saveIngredient(ingredientManagementDto);

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
        String userName = txtName.getText();
        String userUserName = txtUnit.getText();


        IngredientManagementDto ingredientManagementDto = new IngredientManagementDto(
                userId,
                userName,
                userUserName

        );
        try {
            boolean isUpdated = ingredientManagementModel.updateIngredient(ingredientManagementDto);
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
            String ingredient_id = lblId.getText();
            try {
                boolean isDeleted = ingredientManagementModel.deleteIngredient(ingredient_id);
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
        String nextId = ingredientManagementModel.getNextIngredient_Id();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        IngredientManagementTM selectedItem = tblIngredientManagement.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getIngredient_Id());



            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }
}
