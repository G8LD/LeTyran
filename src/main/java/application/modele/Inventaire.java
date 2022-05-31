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

    public void ajouterDansPile(Entite entite) {
        int indexTrouve = -1;
        int placeActuel = -1;
        int placeDisponible = 0;

        for (int i = 0; i < this.getObjets().size() ; i++) {
            ObjetInventaire objetInventaire = this.getObjets().get(i);
            if(objetInventaire.getEntite().getClass() == entite.getClass()) {
                int place = objetInventaire.getPlaceInventaire();

                if(placeActuel < 0 || place < placeActuel) {
                    indexTrouve = i;
                    System.out.println("Place de l'objet " + place);
                    placeActuel = place;
                }
            }
        }

        if(indexTrouve < 0) {
            indexTrouve = 0;
        }
        if(this.getObjets().size() < 1) {
            ObjetInventaire nouveauObjet;
            nouveauObjet = new ObjetInventaire(entite);
            entite.getCollider().setIgnoreCollision(true);
            nouveauObjet.setPlaceInventaire(this.getObjets().size());
            objets.add(nouveauObjet);
        }
        else {
            ObjetInventaire objetAStack;
            objetAStack = this.getObjets().get(indexTrouve);
            objetAStack.setNombre(objetAStack.getNombre() + 1);
        }

    }

    public boolean verifierPlacePrise(Integer i) {
        return placesPrise.get(i);
    }

    public void definirPlacePrise(int i, boolean prit) {
        placesPrise.put(i,prit);
    };


    public void ajouterObjet(Entite obj) {

        /*ajouterDansPile(obj);
        ObjetInventaire objInventaire = new ObjetInventaire(obj);
        obj.getCollider().setIgnoreCollision(true);

        //this.definirPlacePrise(this.getObjets().size(), true);

        objInventaire.setPlaceInventaire(this.getObjets().size());
        objets.add(objInventaire);*/
        this.ajouterDansPile(obj);
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
