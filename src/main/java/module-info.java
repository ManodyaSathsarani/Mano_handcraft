module lk.ijse.mano_handcraft {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;


    opens lk.ijse.mano_handcraft.controller to javafx.fxml;
    exports lk.ijse.mano_handcraft;
}