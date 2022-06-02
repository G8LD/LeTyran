package application.controleur;

import application.modele.Ennemie;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.objets.Bois;
import application.vue.EnnemieVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EnnemiControleur{

    @FXML
    private Pane root;
    private Environnement env;

    private EnnemieVue ennemieVue;

    public EnnemiControleur(Pane root) {
        this.root = root;
        this.env = new Environnement();
        this.ennemieVue= new EnnemieVue(root);

        root.setOnMouseClicked(mouseEvent ->{

            ennemieVue.afficherCadavres();


}
    }



}