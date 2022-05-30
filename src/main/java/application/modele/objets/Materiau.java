package application.modele.objets;

import application.modele.Entite;
import application.modele.armes.Arme;

public abstract class Materiau extends Entite {

    private int x;
    private int y;
    private int pv;

    public Materiau(int x, int y, int pv) {
        this.x = x;
        this.y = y;
        this.pv = pv;
    }

    public void decrementerPv(int degat) {
        pv -= degat;
    }

    public int getX() {

        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public int getPv() {
        return pv;
    }
    //appelé quand le bloc est cliqué décremente selon la qualité si le joueur a la bonne arme sinon de 1
    public abstract void frappe(Arme arme);
}


