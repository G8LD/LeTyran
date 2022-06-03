package application.controleur;

import application.modele.Ennemie;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.ObjetJeu;
import application.modele.objets.Bois;
import application.vue.EnnemieVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EnnemiControleur{

    private Pane root;
    private Environnement env;

    private EnnemieVue ennemieVue;

    public EnnemiControleur(Pane root) {
        this.root = root;
        this.env = new Environnement();
        this.ennemieVue= new EnnemieVue(root);

        this.ennemieVue.getImage().setOnMouseClicked(mouseEvent ->{
            System.out.println("On passe");
            this.prendreLoot();
            System.out.println("fini 3");

        });
         }

        public void prendreLoot(){
            Entite bois= new Bois(this.env,0,0);
            this.env.getPersonnage().getInventaire().ajouterObjet(bois);
            System.out.println(this.env.getPersonnage().getInventaire());

        }


}