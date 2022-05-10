module application.letyran {
    requires javafx.controls;
    requires javafx.fxml;


    opens application to javafx.fxml;
    exports application;

    opens application.controleur to javafx.fxml;
    exports application.controleur;
}