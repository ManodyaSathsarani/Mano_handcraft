package lk.ijse.mano_handcraft.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.mano_handcraft.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginPageController {

    @FXML
    private AnchorPane ancMainPage;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction( ActionEvent actionEvent) throws IOException {
        String inputUserName = txtUserName.getText();
        String inputPassword = txtPassword.getText();

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Users WHERE username = ? AND password  = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, inputUserName);
            stmt.setString(2, inputPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (Objects.equals(inputUserName, "manodya")){
                    ancMainPage.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashboardO.fxml")));
                    ancMainPage.getChildren().add(load);
                }else if (Objects.equals(inputUserName,"admin")) {
//                    ancMainPage.getChildren().clear();
//                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/DashboardO.fxml"));
//                    ancMainPage.getChildren().add(load);
                }
            } else {
                System.out.println("Wrong username or password!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void navigateTo(String path){
        try {
            ancMainPage.getChildren();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancMainPage.widthProperty());
            anchorPane.prefHeightProperty().bind(ancMainPage.heightProperty());

            ancMainPage.getChildren().add(anchorPane);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"Wrong").show();
            e.printStackTrace();
        }
    }
}


