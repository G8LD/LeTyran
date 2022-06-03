package application.modele;

import application.modele.armes.Pioche;
import application.vue.controls.InvItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetInventaire> objets = FXCollections.observableArrayList();

    private int stackMax = 5;
    public final static int PLACE_INVENTAIRE = 20;
    public final static int PLACE_MAIN_PERSONNAGE = 5;

    private Environnement env;
    public Inventaire(Environnement env) {


        this.env = env;
        //this.ajouterObjet(new Pioche(10));

    }

    public ObservableList<ObjetInventaire> getObjets(){

        return objets;
    }

    public void trierObjetInventaireParPlace() {
        int j;
        for (int i = 0; i < objets.size(); i++) {
            ObjetInventaire objEnCours = objets.get(i);
            j = i;
            while(j > 0 && objets.get(j - 1).getPlaceInventaire() > objEnCours.getPlaceInventaire()) {
                objets.set(j, objets.get(j));
                j = j - 1;
            }

            objets.set(j,objEnCours);
        }
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

                if(objetStockee.getNombre() < stackMax && placeActuel < objetStockee.getPlaceInventaire()) {
                    placeActuel = objetStockee.getPlaceInventaire();
                    indexStack = i;
                }
            }

        }


        if(indexStack < 0) {
            System.out.print("Place dispo : [");
            for(int place : placeDisponible) {
                System.out.print(place +",");
            }
            System.out.println("]");
            ObjetInventaire nouvObjet = new ObjetInventaire(ent);
            if(this.getObjets().size() > 0) {
                nouvObjet.setPlaceInventaire(this.getObjets().get(this.getObjets().size() - 1).getPlaceInventaire() + 1);
            } else {
                nouvObjet.setPlaceInventaire(0);
            }

            this.getObjets().add(nouvObjet);
        } else {
            this.getObjets().get(indexStack).ajouterDansStack();

        }


    }

    /*public void definirPlacePrise(int i, boolean prit) {
        placesPrise.put(i,prit);
    };*/


    public void ajouterObjet(Entite obj) {
        trierObjetInventaireParPlace();
        ajouterObjetVersionDeux(obj);
        if (this.env.getEntites() != null) {
            this.env.getEntites().remove(obj);
        }


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
