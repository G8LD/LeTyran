package application.modele.armes;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.personnages.Personnage;

public abstract class Arme extends Entite {

    private int qualite;

    public Arme(Environnement env, int qualite) {
        super(env);
        this.qualite = qualite;
        setPv(pv());
    }


    public void frapper(Personnage perso) {
        perso.decrementerPV(nbDegat());
        decrementerPV();
    }

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

    public int getDistance() {
        return 1;
    }
}
