package application.modele;

import application.vue.controls.InvItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetJeu> objets = FXCollections.observableArrayList();


    private Environnement env;
    public Inventaire(Environnement env) {
        this.env = env;
    }

    public ObservableList<ObjetJeu> getObjets(){

        return objets;
    }

    public void ajouterObjet(ObjetJeu obj) {

        System.out.println(obj);
        objets.add(obj);
        this.env.supprimerObjetEnvironnement(obj);
        /*ArrayList<String> nom = new ArrayList<>();
        nom.add("Viande");
        nom.add("Bois");
        nom.add("Epee");
        for(int i = 1; i < 15; i++) {
            objets.add(new ObjetJeu(this.env,1, nom.get((int)(Math.random() * nom.size()) ), 2));
        }*/
    }

    public void retirerObjet(ObjetJeu objet) {
        //On retire l'objet de l'inventaire
        objets.remove(objet);

        //On veut afficher l'objet sur la carte
        objet.setX(this.env.getPersonnage().getX());
        objet.setY(this.env.getPersonnage().getY());
        this.env.getObjets().add(objet);
    }



}
