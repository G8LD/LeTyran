package application.modele;

import application.modele.armes.Epee;
import application.modele.personnages.Personnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Ennemie extends Personnage {
    private ObservableList<Ennemie> listeCadavres;
    private int x ;
    private int y ;

    public Ennemie(Environnement env, int x , int y) {
        super(env);
        this.x=x;
        this.y=y ;
        listeCadavres = FXCollections.observableArrayList();

    }

    public int attaqueJoueur(int nbdegat) {
        return this.getEnv().getJoueur().getPv() - nbdegat;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void update() {

    }

    @Override
    protected int getHauteurMax() {
        return 0;
    }

    @Override
    protected int getVitesse() {
        return 0;
    }
}