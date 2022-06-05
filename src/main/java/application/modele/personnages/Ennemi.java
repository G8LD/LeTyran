package application.modele.personnages;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.armes.Arme;

import static application.modele.Direction.Droit;
import static application.modele.Direction.Gauche;
import static application.modele.MapJeu.TUILE_TAILLE;

public class Ennemi extends Personnage {

    private static int id = 0;

    private int origine;
    private int distance;

    public Ennemi(Environnement env, Arme arme, int x, int y, int distance) {
        super(env, "Ennemi" + id++, arme, x*TUILE_TAILLE, y*TUILE_TAILLE);
        origine = x*TUILE_TAILLE;
        this.distance = distance*TUILE_TAILLE;
    }

    private void deplacement() {
        if ((getX() < origine && getDirection() == Gauche) || (getX() > origine + distance && getDirection() == Droit)
                || (super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), getDirection()) && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), getDirectionOpposee())))
            changerDirection();
        seDeplacer();
    }

    private void changerDirection() {
        if (getDirection() == Droit)
            setDirection(Gauche);
        else
            setDirection(Droit);
    }

    private Direction getDirectionOpposee() {
        if (getDirection() == Droit)
            return  Gauche;
        else
            return Droit;
    }

    @Override
    public void update() {
        tomber();
        deplacement();
    }

    @Override
    protected int getHauteurMax() {
        return 2 * TUILE_TAILLE;
    }

    @Override
    protected int getVitesse() {
        return 3;
    }
}
