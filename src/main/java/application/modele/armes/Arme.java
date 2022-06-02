package application.modele.armes;

import application.modele.Entite;
import application.modele.Environnement;

public abstract class Arme extends Entite {

    private int qualite;
    private Environnement env;

    public Arme(Environnement env, int qualite) {
        super(env);
        this.qualite = qualite;
    }

    //pour les ennemis
    public abstract void frapper();

    //renvoie les dégâts de l'arme selon la qualité
    public  abstract int nbDegat();

    public int getQualite() {
        return qualite;
    }
}
