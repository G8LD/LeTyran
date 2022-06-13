package application.modele.objets;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Pioche;

import static application.modele.MapJeu.TUILE_TAILLE;

public abstract class Materiau extends Entite {

    public Materiau() {
        super(null, 0, 0, 0);
    }

    public Materiau(Environnement env, int x, int y, int pv) {
        super(env, x, y, pv);
    }

    //appelé quand le bloc est cliqué décremente selon la qualité si le joueur a la bonne arme sinon de 1
    public void estFrappe() {
        if (getEnv().getJoueur().getArme() instanceof Pioche)
            decrementerPv(getEnv().getJoueur().getArme().nbDegat());
        else
            decrementerPv();
        getEnv().getJoueur().getArme().decrementerPv();
    }

    public void detruire() {
        Materiau materiau;
        switch (this.getClass().getSimpleName()) {
            case "Pierre": materiau = new Pierre(this.getEnv(), (int)this.getX() * TUILE_TAILLE, (int)this.getY() * TUILE_TAILLE); break;
            case "Fer": materiau = new Fer(this.getEnv(), (int)this.getX() * TUILE_TAILLE, (int)this.getY() * TUILE_TAILLE); break;
            case "Platine": materiau = new Platine(this.getEnv(), (int)this.getX() * TUILE_TAILLE, (int)this.getY() * TUILE_TAILLE); break;
            case "Terre" : materiau = new Terre(this.getEnv(), (int)this.getX() * TUILE_TAILLE, (int)this.getY() * TUILE_TAILLE); break;
            case "Bois" : materiau = new Bois(this.getEnv(), (int)this.getX() * TUILE_TAILLE, (int)this.getY() * TUILE_TAILLE); break;
            default: materiau = null; break;
        }
        this.getEnv().getListeEntites().add(materiau);
        getEnv().getMapJeu().getTabMap()[(int) getY()][(int) getX()] = 0;
        getEnv().getListeMateriaux().remove(this);
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

}


