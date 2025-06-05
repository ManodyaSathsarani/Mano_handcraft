package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.PaymentManagementDto;
import lk.ijse.mano_handcraft.dto.ProductManagementDto;
import lk.ijse.mano_handcraft.dto.tm.CatagorieManagementTM;
import lk.ijse.mano_handcraft.dto.tm.PaymentManagementTM;
import lk.ijse.mano_handcraft.dto.tm.ProductManagementTM;
import lk.ijse.mano_handcraft.model.ProductManagementModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProductManagementController implements Initializable {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ProductManagementTM, String > colCatagorieId;

    @FXML
    private TableColumn<ProductManagementTM, String> colDescription;

    @FXML
    private TableColumn<ProductManagementTM, String> colId;

    @FXML
    private TableColumn<ProductManagementTM, String> colName;

    @FXML
    private TableColumn<ProductManagementTM, Double> colPrice;

    @FXML
    private Label lblId;

    @FXML
    private TableView<ProductManagementTM> tblProduct;

    @FXML
    private TextField txtCatagorieId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    private final ProductManagementModel productManagementModel = new ProductManagementModel();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        colCatagorieId.setCellValueFactory(new PropertyValueFactory<>("catagorie_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
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
        tblProduct.setItems(FXCollections.observableArrayList(
                productManagementModel.getAllProduct()

                        .stream()
                        .map(productManagementDto -> new ProductManagementTM(
                                productManagementDto.getProduct_id(),
                                productManagementDto.getProduct_name(),
                                productManagementDto.getCatagorie_id(),
                                productManagementDto.getPrice(),
                                productManagementDto.getDescription()
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
            txtCatagorieId.setText(null);
            txtDescription.setText(null);
            txtPrice.setText(null);

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String productId = lblId.getText();
        String Name = txtName.getText();
        String CatagorieId = txtCatagorieId.getText();
        String Description = txtDescription.getText();
        Double Price = Double.parseDouble(txtPrice.getText());

        ProductManagementDto productManagementDto = new ProductManagementDto(
                productId,
                Name,
                CatagorieId,
                Price,
                Description
        );
        try {
            boolean isSaved = productManagementModel.saveProduct(productManagementDto);

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

        String productId = lblId.getText();
        String Name = txtName.getText();
        String CatagorieId = txtCatagorieId.getText();
        String Description = txtDescription.getText();
        Double Price = Double.parseDouble(txtPrice.getText());

        ProductManagementDto productManagementDto = new ProductManagementDto(
                productId,
                Name,
                CatagorieId,
                Price,
                Description
        );
        try {
            boolean isUpdated = productManagementModel.updateProduct(productManagementDto);
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
            String product_id = lblId.getText();
            try {
                boolean isDeleted = productManagementModel.deleteProduct(product_id);
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
        String nextId = productManagementModel.getNextProductId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        ProductManagementTM selectedItem = tblProduct.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getProduct_id());



            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }



}
