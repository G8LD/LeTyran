package application.modele.objets;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Arme;

public abstract class Materiau extends Entite {

    private int pv;
    private Environnement env;
    public Materiau(Environnement env, int x, int y, int pv) {
        super(env, x, y);
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


