package application.modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Inventaire {
    private ObservableList<Integer> objets = FXCollections.observableArrayList();

    public ObservableList<Integer> getObjets(){
        return objets;
    }

    public void ajouterObjet() {
        objets.add(1);
        objets.add(15);
        objets.add(10);
    }

    public void retirerObjet() {

    }


    
}
