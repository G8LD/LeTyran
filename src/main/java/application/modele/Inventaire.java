package application.modele;

import application.vue.controls.InvItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetInventaire> objets = FXCollections.observableArrayList();


    private Environnement env;
    public Inventaire(Environnement env) {

        this.env = env;
    }

    public ObservableList<ObjetInventaire> getObjets(){

        return objets;
    }

    public void ajouterObjet(Entite obj) {
        obj.getCollider().setIgnoreCollision(true);
        this.env.getEntites().remove(obj);
        ObjetInventaire objInventaire = new ObjetInventaire(obj);
        objInventaire.setPlaceInventaire(this.getObjets().size());
        objets.add(objInventaire);


    }

    public void retirerObjet(ObjetInventaire objet) {
        //On retire l'objet de l'inventaire
        Entite ent = objet.getEntite();
        objets.remove(objet);


        //On veut afficher l'objet sur la carte
        ent.setX(this.env.getPersonnage().getX());
        ent.setY(this.env.getPersonnage().getY());

        this.env.getEntites().add(ent);
        ent.getCollider().setIgnoreCollision(false);

    }



}
