package application.modele;

import application.vue.controls.InvItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetInventaire> objets = FXCollections.observableArrayList();
    private ArrayList<Integer> placesDisponibles;

    private int stackMax = 5;
    public final static int PLACE_INVENTAIRE = 32;

    private Environnement env;
    public Inventaire(Environnement env) {


        this.env = env;


    }

    public ObservableList<ObjetInventaire> getObjets(){

        return objets;
    }


    public void ajouterObjetVersionDeux(Entite ent) {

        ArrayList<Integer> placeDisponible = new ArrayList<>();
        for(int i = 0; i < PLACE_INVENTAIRE; i++) {

            placeDisponible.add(i);
        }


        //On stock l'index de l'endroit dans lequel on peut empiler
        int indexStack = -1;
        //ça permet de vérifier qu'elle est le premier bloc a pouvoir être empiler
        int placeActuel = -1;


        for(int i = 0; i < this.getObjets().size(); i++) {
            ObjetInventaire objetStockee = this.getObjets().get(i);
            placeDisponible.remove(objetStockee.getPlaceInventaire());

            if(objetStockee.getEntite().getClass() == ent.getClass()) {
                if(objetStockee.getNombre() < stackMax && (placeActuel < 0 || placeActuel < objetStockee.getPlaceInventaire())) {
                    placeActuel = objetStockee.getPlaceInventaire();
                    indexStack = i;
                }
            }

            System.out.println(objetStockee.getPlaceInventaire());


        }

        if(indexStack >= 0) {
            this.getObjets().get(indexStack).ajouterDansStack();
        } else {

            ObjetInventaire nouvObjet = new ObjetInventaire(ent);
            nouvObjet.setPlaceInventaire(placeDisponible.get(0));
            this.getObjets().add(nouvObjet);

        }


    }

    /*public void definirPlacePrise(int i, boolean prit) {
        placesPrise.put(i,prit);
    };*/


    public void ajouterObjet(Entite obj) {

        ajouterObjetVersionDeux(obj);
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
