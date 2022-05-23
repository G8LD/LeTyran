package application.modele.objets;

import application.modele.armes.Arme;

public abstract class Minerai {

    private final static int PV_MAX = 6;

    private int x;
    private int y;
    private int pv;
    private String idNom;

    public Minerai(int x, int y, String id) {
        this.x = x;
        this.y = y;
        this.pv = PV_MAX;
        this.idNom = id;
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

    public String getIdNom() {
        return this.idNom;
    }
}



