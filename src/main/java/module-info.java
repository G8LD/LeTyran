module application.letyran {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens application to javafx.fxml;
    exports application;

    opens application.controleur to javafx.fxml;
    exports application.controleur;
    exports application.modele;
    opens application.modele to javafx.fxml;
    exports application.vue;
    opens application.vue to javafx.fxml;
    exports application.vue.vueMap;
    opens application.vue.vueMap to javafx.fxml;
    exports application.modele.objets;
    opens application.modele.objets to javafx.fxml;
}