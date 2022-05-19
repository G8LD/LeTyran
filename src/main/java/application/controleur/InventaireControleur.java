package application.controleur;

import application.controleur.listeners.ObjetSupprimeListener;
import application.modele.Inventaire;
import application.modele.Environnement;
import application.vue.InventaireVue;
import application.vue.controls.InvItem;
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

        this.inv.getObjets().addListener(new ObjetSupprimeListener(this));
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.I) {
            this.invVue.afficherInventaire();
        }
    }


    public void objetPlaceInventaireChanger(InvItem objetInventaire, int nouvellePlace) {
        //System.out.println(objetInventaire.getObjet() + " " + nouvellePlace);
        objetInventaire.getObjet().setPlaceInventaire(nouvellePlace);
    }

    public void lacherObjet(InvItem objetInventaire) {
        this.inv.retirerObjet(objetInventaire.getObjet());

    }

    public void enleverObjetAffichage(int indexObjetInventaire) {

    }

    public void echangerObjet(InvItem premier, InvItem second, int nouvPlacePrem, int nouvPlaceSec) {
        int placeEchange = second.getObjet().getPlaceInventaire();


        //System.out.println(second.getParent());

        premier.getObjet().setPlaceInventaire(nouvPlacePrem);
        second.getObjet().setPlaceInventaire(nouvPlaceSec);

    }



}
