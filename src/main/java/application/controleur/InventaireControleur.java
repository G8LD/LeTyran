package application.controleur;

import application.controleur.listeners.ObjetSupprimeListener;
import application.modele.Inventaire;
import application.modele.Environnement;
import application.vue.InventaireVue;
import application.vue.controls.InvItem;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

public class InventaireControleur implements EventHandler<Event> {

    private Pane root;
    private Environnement jeu;
    private Inventaire inv;


    private InventaireVue invVue;

    public InventaireControleur(Pane root, Environnement jeu, Pane inventaireMain, Pane inventaireSac) {
        this.root = root;
        this.jeu = jeu;

        this.inv = this.jeu.getPersonnage().getInventaire();

        this.invVue = new InventaireVue(inv, this, inventaireMain, inventaireSac);

        this.inv.getObjets().addListener(new ObjetSupprimeListener(this));
    }

    public void gererEntreeClavier(KeyEvent keyvent) {
        if(keyvent.getCode() == KeyCode.I) {
            this.invVue.afficherInventaire();
        }
    }

    public void gererEntreeSouris(ScrollEvent scrollEvent) {
        if(scrollEvent.getDeltaY() > 0) {
            System.out.println("-1");
        } else if(scrollEvent.getDeltaY() < 0) {
            System.out.println("+1");
        }
    }

    @Override
    public void handle(Event event) {
        //System.out.println(event.);
        if(event.getEventType() == KeyEvent.KEY_PRESSED) {
            gererEntreeClavier((KeyEvent) event);
        } else if(event.getEventType() == ScrollEvent.SCROLL) {
            gererEntreeSouris((ScrollEvent) event);
        }
    }


    public void objetPlaceInventaireChanger(InvItem objetInventaire, int anciennePlace, int nouvellePlace) {
        this.inv.libererPlacePrise(anciennePlace);
        this.inv.definirPlacePrise(nouvellePlace);

        objetInventaire.getObjetInventaire().setPlaceInventaire(nouvellePlace);
    }

    public void jeterObjet(InvItem vueObjet) {
        this.inv.retirerObjet(vueObjet.getObjetInventaire());

    }

    public void enleverObjetAffichage(int indexObjetInventaire) {

    }

    public void echangerObjet(InvItem premier, InvItem second, int nouvPlacePrem, int nouvPlaceSec) {
        int placeEchange = second.getObjetInventaire().getPlaceInventaire();


        //System.out.println(second.getParent());

        second.getObjetInventaire().setPlaceInventaire(premier.getObjetInventaire().getPlaceInventaire());
        premier.getObjetInventaire().setPlaceInventaire(placeEchange);


    }

}
