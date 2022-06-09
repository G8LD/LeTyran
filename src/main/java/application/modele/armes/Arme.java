package application.modele.armes;

import application.modele.Entite;
import application.modele.Environnement;

public abstract class Arme extends Entite {

    private int qualite;

    public Arme(Environnement env, int qualite) {
        super(env);
        this.qualite = qualite;
        setPv(pv());
    }

    //pour les ennemis
    public abstract void frapper();

    //renvoie les dégâts de l'arme selon la qualité
    public  abstract int nbDegat();

    private int pv() {
        if (qualite == 3)
            return 30;
        else if (qualite == 2)
            return 20;
        else
            return 10;
    }

    public int getQualite() {
        return qualite;
    }
}
