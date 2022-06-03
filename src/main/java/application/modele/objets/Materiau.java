package application.modele.objets;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Arme;

public abstract class Materiau extends Entite {

    private Environnement env;

    public Materiau() {
        super(null, 0, 0, 0);
    }

    public Materiau(Environnement env, int x, int y, int pv) {
        super(env, x, y, pv);
    }

    public void decrementerPv(int degat) {
        super.setPv(super.getPv() - degat);
    }

    public float getX() {

        return super.getX();
    }

    public float getY() {
        return super.getY();
    }

    public int getPv() {
        return super.getPv();
    }
    //appelé quand le bloc est cliqué décremente selon la qualité si le joueur a la bonne arme sinon de 1
    public abstract void frappe(Arme arme);

    public void quandDetruit() {
        Materiau materiau;

        switch (this.getClass().getSimpleName()) {
            case "Pierre": materiau = new Pierre(this.getEnv(), (int)this.getX() * 32, (int)this.getY() * 32); break;
            case "Fer": materiau = new Fer(this.getEnv(), (int)this.getX() * 32, (int)this.getY() * 32); break;
            case "Platine": materiau = new Platine(this.getEnv(), (int)this.getX() * 32, (int)this.getY() * 32); break;
            case "Terre" : materiau = new Terre(this.getEnv(), (int)this.getX() * 32, (int)this.getY() * 32); break;
            case "Bois" : materiau = new Bois(this.getEnv(), (int)this.getX() * 32, (int)this.getY() * 32); break;
            default: materiau = null; break;
        }
        this.getEnv().getListeEntites().add(materiau);
    }
}


