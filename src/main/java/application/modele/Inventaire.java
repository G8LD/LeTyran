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

    public void ajouterObjet(String nomObjet) {
        ArrayList<String> nom = new ArrayList<>();
        nom.add(nomObjet);
        for(int i = 1; i < 15; i++) {
            objets.add(new ObjetJeu(1,  nomObjet, 2));
        }
    }

    public void retirerObjet(ObjetJeu objet) {
        objets.remove(objet);
    }


}
