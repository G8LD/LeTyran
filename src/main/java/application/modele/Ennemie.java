package application.modele;

import application.modele.personnages.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ennemie extends Personnage {
    private ObservableList<Ennemie> listeCadavres;
    private int x ;
    private int y ;

    public Ennemie(Environnement env, int x , int y ) {
        super(env);
        this.x=x;
        this.y=y ;
        listeCadavres = FXCollections.observableArrayList();

    }

    public int attaqueJoueur(int nbdegat) {
        return this.getEnv().getJoueur().getPv() - nbdegat;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}