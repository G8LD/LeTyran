package application.modele.armes;

import application.modele.Entite;
import application.modele.Environnement;

public class Armure extends Entite {

    private int qualite;

    public Armure(Environnement env, int qualite) {
        super(env);
        this.qualite = qualite;
        setPv(pv());
    }

    public int defendre() {
        int nbDefense;
        if (qualite == 1)
            nbDefense = 3;
        else if (qualite == 2)
            nbDefense = 6;
        else
            nbDefense = 9;
        setPv(getPv() - nbDefense);
        return nbDefense;
    }

    private int pv() {
        if (qualite == 1)
            return 30;
        else if (qualite == 2)
            return 60;
        else
            return 90;
    }
}
