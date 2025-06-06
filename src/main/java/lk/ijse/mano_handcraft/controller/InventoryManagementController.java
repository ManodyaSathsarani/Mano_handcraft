package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.InventoryManagementDto;
import lk.ijse.mano_handcraft.dto.tm.InventoryManagementTM;
import lk.ijse.mano_handcraft.model.EmployeeMnagementModel;
import lk.ijse.mano_handcraft.model.InventoryManagementModel;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryManagementController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<InventoryManagementTM, String> colIngredientId;

    @FXML
    private TableColumn<InventoryManagementTM, String> colInventoryId;

    @FXML
    private TableColumn<InventoryManagementTM, String> colLastUpdate;

    @FXML
    private TableColumn<InventoryManagementTM, Double> colQuantityInStock;

    @FXML
    private Label lblInventoryId;

    @FXML
    private TableView<InventoryManagementTM> tblInventory;

    @FXML
    private TextField txtIngredientId;

    @FXML
    private TextField txtLastUpdate;

    @FXML
    private TextField txtQuantitiyInStock;

    private final InventoryManagementModel inventoryManagementModel = new InventoryManagementModel();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        colIngredientId.setCellValueFactory(new PropertyValueFactory<>("ingredient_id"));
        colQuantityInStock.setCellValueFactory(new PropertyValueFactory<>("quantity_in_stock"));
        colLastUpdate.setCellValueFactory(new PropertyValueFactory<>("last_updated"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }


    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblInventory.setItems(FXCollections.observableArrayList(
                inventoryManagementModel.getAllInventory()

                        .stream()
                        .map(inventoryManagementDto -> {
                                    return new InventoryManagementTM(
                                            inventoryManagementDto.getInventory_id(),
                                            inventoryManagementDto.getIngredient_id(),
                                            inventoryManagementDto.getQuantity_in_stock(),
                                            inventoryManagementDto.getLast_updated()
                                    );
                                }

                        ).
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

            txtIngredientId.setText(null);
            txtLastUpdate.setText(null);
            txtQuantitiyInStock.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private boolean validateInputs() {
        String ingredientId = txtIngredientId.getText();
        String quantityStr = txtQuantitiyInStock.getText();
        String lastUpdate = txtLastUpdate.getText();

        // Validate Ingredient ID
        if (ingredientId == null || ingredientId.isBlank()) {
            showValidationError("Ingredient ID is required.");
            return false;
        }

        if (!ingredientId.matches("I\\d{3}")) {
            showValidationError("Ingredient ID must follow the format I001, I002, etc.");
            return false;
        }

        // Validate Quantity
        if (quantityStr == null || quantityStr.isBlank()) {
            showValidationError("Quantity is required.");
            return false;
        }

        try {
            double quantity = Double.parseDouble(quantityStr);
            if (quantity <= 0) {
                showValidationError("Quantity must be greater than 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            showValidationError("Quantity must be a valid number.");
            return false;
        }

        // Validate Last Update Date (yyyy-MM-dd format)
        if (lastUpdate == null || lastUpdate.isBlank()) {
            showValidationError("Last Update Date is required.");
            return false;
        }

        if (!lastUpdate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            showValidationError("Date must be in format YYYY-MM-DD.");
            return false;
        }

        return true;
    }

    private void showValidationError(String message) {
        new Alert(Alert.AlertType.WARNING, message).show();
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (!validateInputs()) return;
        String inventoryId = lblInventoryId.getText();
        String ingredientId = txtIngredientId.getText();
        Double quantityInStock = Double.valueOf(txtQuantitiyInStock.getText());
        String lastUpdate = txtLastUpdate.getText();


        InventoryManagementDto inventoryManagementDto = new InventoryManagementDto(
                inventoryId,
                ingredientId,
                quantityInStock,
                lastUpdate


        );
        try {
            boolean isSaved = inventoryManagementModel.saveInventory(inventoryManagementDto);

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
        String inventoryId = lblInventoryId.getText();
        String ingredientId = txtIngredientId.getText();
        Double quantityInStock = Double.valueOf(txtQuantitiyInStock.getText());
        String lastUpdate = txtLastUpdate.getText();


        InventoryManagementDto inventoryManagementDto = new InventoryManagementDto(
                inventoryId,
                ingredientId,
                quantityInStock,
                lastUpdate

        );
        try {
            boolean isUpdated = inventoryManagementModel.updateInventory(inventoryManagementDto);
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
            String inventory_id = lblInventoryId.getText();
            try {
                boolean isDeleted = inventoryManagementModel.deleteInventory(inventory_id);
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
        String nextId = inventoryManagementModel.getNextInventory_Id();
        lblInventoryId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        InventoryManagementTM selectedItem = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblInventoryId.setText(selectedItem.getInventory_id());


            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }


}
