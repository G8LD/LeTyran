package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Hache;

public class Arbre {

    private final static int PV_MAX = 12;

    private int x;
    private int y;
    private int hauteur;
    private int pv;

    public Arbre(int x, int y, int hauteur) {
        this.x = x;
        this.y = y;
        this.hauteur = hauteur;
        this.pv = PV_MAX;
    }

    public int frappe(Arme arme) {
        int nbDegats;

        if (arme instanceof Hache) {
            if (arme.getQualite() == 1)
                nbDegats = 2;
            else if (arme.getQualite() == 2)
                nbDegats = 3;
            else
                nbDegats = 6;
        } else {
            nbDegats = 1;
        }

        int nbBois = 0;
        for (int i = 0; i < nbDegats; i++) {
            pv -=2;
            if (pv % 4 == 0) nbBois++;
        }
        return nbBois;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getPv() {
        return pv;
    }
}
