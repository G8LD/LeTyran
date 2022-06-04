package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetInventaire> objets;
    private HashMap<Integer, Boolean> placesPrise;

    private Environnement env;
    public Inventaire(Environnement env) {
        objets = FXCollections.observableArrayList();
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
        this.env.getListeEntites().remove(obj);


    }

    public void retirerObjet(ObjetInventaire objet) {
        //On retire l'objet de l'inventaire
        Entite ent = objet.getEntite();
        objets.remove(objet);


        //On veut afficher l'objet sur la carte
        ent.setX(this.env.getJoueur().getX() + 32);
        ent.setY(this.env.getJoueur().getY());

        this.env.getListeEntites().add(ent);
        ent.getCollider().setIgnoreCollision(false);

    }



}
