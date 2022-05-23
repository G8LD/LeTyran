package application.modele.objets;

import application.modele.armes.Arme;

public abstract class Minerai {

    private final static int PV_MAX = 6;

    private int x;
    private int y;
    private int pv;
    private int idMin;
    private String nom;

    public Minerai(int x, int y, int idMin ,String nom) {
        this.x = x;
        this.y = y;
        this.pv = PV_MAX;
        this.idMin=idMin;
        this.nom=nom;
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

    public String getNom(){
        return this.nom;
        }


   public int getIdMin(){
       return this.idMin;
   }
}


