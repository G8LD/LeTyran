package application.modele;

import application.modele.objets.Bois;
import application.modele.objets.Coffre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ennemie extends Personnage {
    private ObservableList<Ennemie> listeCadavres;


    public Ennemie(Environnement env) {
        super(env);
        listeCadavres = FXCollections.observableArrayList();

    }

    public int attaqueJoueur(int nbdegat) {
        return this.getEnv().getPersonnage().getPv() - nbdegat;
    }




}