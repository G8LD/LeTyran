package application.modele.personnages.ennemi;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.armes.arc.Arc;

import static application.modele.Direction.Droit;
import static application.modele.Direction.Gauche;
import static application.modele.MapJeu.TUILE_TAILLE;

public class Archer extends Ennemi {

    public Archer(Environnement env, int niveau, int x, int y, int distance) {
        super(env, x, y, distance);
        setPv(niveau * 20);
        setArme(new Arc(getEnv(), niveau));
    }

    protected void deplacement() {
        if (!fuitJoueur() && (Math.abs(getEnv().getJoueur().getX() - (getX())) - 4 >= 4 * TUILE_TAILLE
                || Math.abs(getEnv().getJoueur().getY() - getY()) > TUILE_TAILLE)
                && (Math.abs(getX() - getOrigineX()) > 1 || Math.abs(getY() - getOrigineY()) > 1)) {
            deplacementAllerRetour();
            seDeplacer();
        }
    }

    @Override
    protected boolean joueurEnFace() {
        if (Math.abs(getEnv().getJoueur().getX() - getX()) < getArme().getDistance() * TUILE_TAILLE
                && Math.abs(getEnv().getJoueur().getY() - getY()) < TUILE_TAILLE) {
            if (getEnv().getJoueur().getX() - getX() <= 0)
                setDirection(Gauche);
            else
                setDirection(Droit);
            return true;
        }
        return false;
    }

    private boolean fuitJoueur() {
        if (Math.abs(getEnv().getJoueur().getX() - getX()) < 4 * TUILE_TAILLE
                && Math.abs(getEnv().getJoueur().getY() - getY()) < TUILE_TAILLE) {
            Direction direction;
            if (getEnv().getJoueur().getX() - getX() <= 0)
                direction = Droit;
            else
                direction = Gauche;
            int i = 0;
            while (i < 3 && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), direction)) {
                i++;
                if (direction == Droit)
                    super.setX(super.getX() + 0.45f);
                else
                    super.setX(super.getX() - 0.45f);
            }
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        if (getDistancePoussee() != 0)
            estPoussee();
        else {
            tomber();
            if (getAttaque())
                attaquer();
            if (!getAttaque())
                detectionJoueur();
            deplacement();
        }
    }
}
