package lk.ijse.mano_handcraft.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.mano_handcraft.dto.UserListDto;
import lk.ijse.mano_handcraft.dto.tm.UserListTM;
import lk.ijse.mano_handcraft.model.UserListModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtUserName;
    public TextField txtPassword;
    public TextField txtRole;
    public TextField txtRegistrationDate;

    public TableView<UserListTM> tblUserList;
    public TableColumn<UserListTM, String> colId;
    public TableColumn<UserListTM, String> colName;
    public TableColumn<UserListTM, String> colUserName;
    public TableColumn<UserListTM, String> colPassword;
    public TableColumn<UserListTM, String> colRole;
    public TableColumn<UserListTM, String> colRegistrationDate;


    private final UserListModel userListModel = new UserListModel();

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        try {
            resetPage();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void loadTableData() throws SQLException, ClassNotFoundException {
        tblUserList.setItems(FXCollections.observableArrayList(
                userListModel.getAllUsers()
                        .stream()
                        .map(userListDto -> new UserListTM(
                                userListDto.getUser_Id(),
                                userListDto.getName(),
                                userListDto.getUserName(),
                                userListDto.getPassword(),
                                userListDto.getRole(),
                                userListDto.getRegistration_Date()
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
            txtUserName.setText(null);
            txtPassword.setText(null);
            txtRole.setText(null);
            txtRegistrationDate.setText(null);


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String userId = lblId.getText();
        String userName = txtName.getText();
        String userUserName = txtUserName.getText();
        String userContact = txtPassword.getText();
        String userRole = txtRole.getText();
        String userRegistrationDate = txtRegistrationDate.getText();

        UserListDto userListDto = new UserListDto(
                userId,
                userName,
                userUserName,
                userContact,
                userRole,
                userRegistrationDate
        );
        try {
            boolean isSaved = userListModel.saveUser(userListDto);

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
        String userUserName = txtUserName.getText();
        String userContact = txtPassword.getText();
        String userRole = txtRole.getText();
        String userRegistrationDate = txtRegistrationDate.getText();


        UserListDto userListDto = new UserListDto(
                userId,
                userName,
                userUserName,
                userContact,
                userRole,
                userRegistrationDate
        );
        try {
            boolean isUpdated = userListModel.updateUser(userListDto);
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
            String userId = lblId.getText();
            try {
                boolean isDeleted = userListModel.deleteUser(userId);
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
        String nextId = userListModel.getNextUserId();
        lblId.setText(nextId);
    }

    public void getData(MouseEvent mouseEvent) {
        UserListTM selectedItem = tblUserList.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getUser_Id());
            txtUserName.setText(selectedItem.getName());
            txtName.setText(selectedItem.getUserName());
            txtPassword.setText(selectedItem.getPassword());
            txtRole.setText(selectedItem.getRole());
            txtRegistrationDate.setText(selectedItem.getRegistration_Date());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }

    }
}
