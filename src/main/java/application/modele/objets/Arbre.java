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

    public void frappe(Arme arme) {
        if (arme instanceof Hache) {
            if (arme.getQualite() == 1)
                pv -= 4;
            else if (arme.getQualite() == 2)
                pv -= 6;
            else
                pv -= 12;
        } else {
            pv -= 2;
        }
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
