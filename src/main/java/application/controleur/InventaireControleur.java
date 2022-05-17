package application.controleur;

import application.modele.Inventaire;
import application.modele.Environnement;
import application.vue.InventaireVue;
import application.vue.controls.InvSlot;
import javafx.event.EventHandler;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class InventaireControleur implements EventHandler<KeyEvent> {

    private StackPane root;
    private Environnement jeu;
    private Inventaire inv;


    private InventaireVue invVue;


    private ImageView imgHovered = null;

    private Pane invPane;

    public InventaireControleur(StackPane root, Environnement jeu) {
        this.root = root;
        this.jeu = jeu;

        this.inv = this.jeu.getPersonnage().getInventaire();

        this.invVue = new InventaireVue(inv, root, this);
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.I) {
            this.invVue.afficherInventaire();
        }
    }


    public void objetLache(InvSlot objetInventaire, int nouvellePlace) {
        //System.out.println(objetInventaire.getObjet() + " " + nouvellePlace);
        objetInventaire.getObjet().setPlaceInventaire(nouvellePlace);
    }

}
