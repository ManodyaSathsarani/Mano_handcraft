module lk.ijse.mano_handcraft {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;
    requires static lombok;


    opens lk.ijse.mano_handcraft.controller to javafx.fxml;
    opens lk.ijse.mano_handcraft.dto.tm to javafx.base;

    exports lk.ijse.mano_handcraft;
    exports lk.ijse.mano_handcraft.controller;
}