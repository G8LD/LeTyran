package application.modele.objets;

import application.modele.armes.Arme;

public abstract class Minerai {

    private final static int PV_MAX = 9;

    private int x;
    private int y;
    private int pv;

    public Minerai(int x, int y) {
        this.x = x;
        this.y = y;
        this.pv = PV_MAX;
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


