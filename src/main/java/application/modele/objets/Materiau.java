package application.modele.objets;

import application.modele.armes.Arme;

public abstract class Materiau {

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
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPv() {
        return pv;
    }

    public abstract void frappe(Arme arme);
}


