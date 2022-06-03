package application.modele;

import application.vue.controls.InvItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetInventaire> objets = FXCollections.observableArrayList();
    private HashMap<Integer, Boolean> placesPrise;

    private Environnement env;
    public Inventaire(Environnement env) {
        placesPrise = new HashMap<>();
        this.env = env;

    }

    public ObservableList<ObjetInventaire> getObjets(){

        return objets;
    }

    public boolean verifierPlacePrise(Integer i) {
        return placesPrise.get(i);
    }

    public void definirPlacePrise(int i, boolean prit) {
        placesPrise.put(i,prit);
    };


    public void ajouterObjet(Entite obj) {
        ObjetInventaire objInventaire = new ObjetInventaire(obj);
        obj.getCollider().setIgnoreCollision(true);

        //this.definirPlacePrise(this.getObjets().size(), true);

        objInventaire.setPlaceInventaire(this.getObjets().size());
        objets.add(objInventaire);
        this.env.getEntites().remove(obj);


    }

    public void retirerObjet(ObjetInventaire objet) {
        //On retire l'objet de l'inventaire
        Entite ent = objet.getEntite();
        objets.remove(objet);


        //On veut afficher l'objet sur la carte
        ent.setX(this.env.getPersonnage().getX() + 32);
        ent.setY(this.env.getPersonnage().getY());

        this.env.getEntites().add(ent);
        ent.getCollider().setIgnoreCollision(false);

    }



}
