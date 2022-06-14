package application.modele.armes;

import application.modele.Entite;
import application.modele.Environnement;

public class Armure extends Entite {

    private int qualite;

    public Armure(Environnement env, int qualite) {
        super(env);
        this.qualite = qualite;
        setPv(30 * qualite);
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

    public int getQualite() {
        return qualite;
    }

    @Override
    public void detruire() {
        getEnv().getJoueur().getInventaire().retirerObjet(getEnv().getJoueur().getInventaire().getObjetCorrespondant(this));
    }
}
