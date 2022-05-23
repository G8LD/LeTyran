package application.modele;

import application.vue.controls.InvItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventaire {
    private int MAX_OBJET = 1;
    private ObservableList<ObjetJeu> objets = FXCollections.observableArrayList();

    public ObservableList<ObjetJeu> getObjets(){
        return objets;
    }

    public void ajouterObjet() {
        ArrayList<String> nom = new ArrayList<>();
        nom.add("Viande");
        nom.add("Bois");
        nom.add("Epee");
        for(int i = 1; i < 15; i++) {
            objets.add(new ObjetJeu(1, nom.get((int)(Math.random() * nom.size()) ), 2));
        }
    }

    public void retirerObjet(ObjetJeu objet) {
        objets.remove(objet);
    }


}
