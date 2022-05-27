package application.modele.objets;

import application.modele.armes.Arme;

public abstract class Materiau {

    private int x;
    private int y;
    private int pv;

    public Materiau() {
        x = 0;
        y = 0;
        pv = 0;
    }

    public Materiau(int x, int y, int pv) {
        this.x = x;
        this.y = y;
        this.pv = pv;
    }

    public void decrementerPv(int degat) {
        pv -= degat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPv() {
        return pv;
    }
    //appelé quand le bloc est cliqué décremente selon la qualité si le joueur a la bonne arme sinon de 1
    public abstract void frappe(Arme arme);
}


