package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetInventaire> objets = FXCollections.observableArrayList();

    private int stackMax = 1;
    public final static int PLACE_INVENTAIRE = 25;
    public final static int PLACE_MAIN_PERSONNAGE = 5;


    private HashMap<Integer, Boolean> placesDisponible;

    private Environnement env;
    public Inventaire(Environnement env) {

        placesDisponible = new HashMap<>();
        this.env = env;

        for(int i = 0; i < PLACE_INVENTAIRE; i++) {

            placesDisponible.put(i, true);
        }
        //this.ajouterObjet(new Pioche(10));

    }

    public ObservableList<ObjetInventaire> getObjets(){

        return objets;
    }

    public void definirPlacePrise(int place) {
        this.placesDisponible.put(place, false);
    }

    public void libererPlacePrise(int place) {
        this.placesDisponible.put(place, true);
    }

    public int recupererPlaceDispo() {
        int i = 0;
        int placeTrouve = -1;
        while(i < placesDisponible.size() && placeTrouve < 0) {
            if(placesDisponible.get(i) != null && placesDisponible.get(i)) {
                placeTrouve = i;
            }
            i++;
        }
        return placeTrouve;
    }

    public void trierObjetInventaireParPlace() {
        int j;
        ObjetInventaire actuel;
        for (int i = 0; i < objets.size(); i++) {
            j = i;
            actuel = objets.get(i);


            while(j > 0 &&  objets.get(j-1).getPlaceInventaire() > actuel.getPlaceInventaire()) {
                objets.set(j, objets.get(j-1));

                j = j - 1;
            }

            objets.set(i,actuel);


        }
    }

    public boolean ajouterObjetVersionDeux(Entite ent) {

        boolean ajouter = false;


        //On stock l'index de l'endroit dans lequel on peut empiler
        int indexStack = -1;
        //ça permet de vérifier qu'elle est le premier bloc a pouvoir être empiler
        int placeActuel = -1;


        for(int i = 0; i < this.getObjets().size(); i++) {
            ObjetInventaire objetStockee = this.getObjets().get(i);

            if(objetStockee.getEntite().getClass() == ent.getClass()) {

                if(objetStockee.getNombre() < stackMax && placeActuel < objetStockee.getPlaceInventaire()) {
                    placeActuel = objetStockee.getPlaceInventaire();
                    indexStack = i;
                }
            }

        }


        if(indexStack < 0) {
            int placeTrouve = recupererPlaceDispo();

            if(placeTrouve >= 0) {
                ObjetInventaire nouvObjet = new ObjetInventaire(ent);

                System.out.println("Place trouvé " + placeTrouve);
                nouvObjet.setPlaceInventaire(placeTrouve);

                definirPlacePrise(placeTrouve);

                this.getObjets().add(nouvObjet);
                ajouter = true;
            } else {
                System.out.println("L'inventaire est rempli");
            }
        } else {
            this.getObjets().get(indexStack).ajouterDansStack();
            ajouter = true;

        }
        return ajouter;
    }

    public void ajouterObjet(Entite obj) {

        if(ajouterObjetVersionDeux(obj)) {
            if (this.env.getEntites() != null) {
                this.env.getEntites().remove(obj);
            }

            trierObjetInventaireParPlace();
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

    public String toString() {
        return "[Inventaire]" + "\nPlace Main : " + PLACE_MAIN_PERSONNAGE + "\nPlaceTotal" + PLACE_INVENTAIRE;
    }



}
