package application.modele.objets;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Hache;

public class Arbre extends Entite {

    private final static int PV_MAX = 12;
    private int pv;

    public Arbre(Environnement env, int x, int y) {
        super(env, x, y);
        this.pv = PV_MAX;
    }
    //retourne le nombre de bois drop
    public int frappe(Arme arme) {
        int nbDegats;

        if (arme instanceof Hache) {
            nbDegats = arme.nbDegat();
        } else {
            nbDegats = 1;
        }

        int nbBois = 0;
        for (int i = 0; i < nbDegats; i++) {
            pv--;
            if (pv % 4 == 0) nbBois++;
        }
        return nbBois;
    }

    public float getX() {
        return super.getX();
    }

    public float getY() {
        return super.getY();
    }

    public int getPv() {
        return pv;
    }
}
