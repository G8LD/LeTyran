package application.modele.objets;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.MapJeu;
import application.modele.armes.Arme;
import application.modele.armes.Pioche;

import static application.modele.MapJeu.TUILE_TAILLE;

import java.util.Map;

public abstract class Materiau extends Entite {

    public Materiau() {
        super(null, 0, 0, 0);
    }

    public Materiau(Environnement env, int x, int y, int pv) {
        super(env, x, y, pv);
        //System.out.println(this.getCollider().getHitBox());
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
        int positionX = (int)this.getX() * MapJeu.TUILE_TAILLE + (MapJeu.WIDTH / 2);
        int positionY = (int)this.getY() * MapJeu.TUILE_TAILLE;
        switch (this.getClass().getSimpleName()) {
            case "Pierre": materiau = new Pierre(this.getEnv(), positionX, positionY); break;
            case "Fer": materiau = new Fer(this.getEnv(), positionX, positionY); break;
            case "Platine": materiau = new Platine(this.getEnv(), positionX, positionY); break;
            case "Terre" : materiau = new Terre(this.getEnv(), positionX, positionY); break;
            case "Bois" : materiau = new Bois(this.getEnv(), positionX, positionY); break;
            default: materiau = null; break;
        }
        this.getEnv().getListeEntites().add(materiau);

        getEnv().getMapJeu().getTabMap()[(int) getY()][(int) getX()] = 0;
        getEnv().getListeMateriaux().remove(this);
    }

    public double getX() {
        return super.getX();
    }

    public double getY() {
        return super.getY();
    }

    public int getPv() {
        return super.getPv();
    }

}


