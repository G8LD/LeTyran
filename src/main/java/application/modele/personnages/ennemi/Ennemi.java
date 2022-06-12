package application.modele.personnages.ennemi;

import application.modele.Environnement;
import application.modele.personnages.PNJ;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import static application.modele.Direction.Droit;
import static application.modele.Direction.Gauche;
import static application.modele.MapJeu.TUILE_TAILLE;

public abstract class Ennemi extends PNJ {

    private static int id = 0;

    private BooleanProperty attaqueProperty;
    private int delai;
    private boolean retourZone;
    private boolean poursuitJoueur;

    public Ennemi(Environnement env, int x, int y, int distance) {
        super(env, "Ennemi" + id++, x, y, distance);
        attaqueProperty = new SimpleBooleanProperty(false);
        delai = 0;
        retourZone = false;
        poursuitJoueur = false;
    }

    protected void detectionJoueur() {
        if (joueurEnFace()) {
            attaqueProperty.setValue(true);
            delai = 0;
        }
    }

    protected void attaquer() {
        if (delai++ >= 30) {
            if (joueurEnFace())
                getArme().frapper(this, getEnv().getJoueur());
            attaqueProperty.setValue(false);
        }
    }

    protected boolean joueurEnFace() {
        return Math.abs(getEnv().getJoueur().getX() - getX()) < getArme().getDistance() * TUILE_TAILLE
                && Math.abs(getEnv().getJoueur().getY() - getY()) < TUILE_TAILLE
                && ((getDirection() == Gauche && getEnv().getJoueur().getX() - getX() <= 0)
                || (getDirection() == Droit && getEnv().getJoueur().getX() - getX() >= 0));
    }

    protected void retourneDansZone() {
        if (((getX() < getOrigineX() - 10 * TUILE_TAILLE && getDirection() == Gauche)
                || (getX() > getOrigineX() + getDistance() + 10 * TUILE_TAILLE  && getDirection() == Droit)) && !getRetourZone()) {
            setDirection(getDirectionOpposee());
            retourZone = true;
        } else if (getX() >= getOrigineX() && getX() <= getOrigineX() + getDistance() && getY() == getOrigineY() && getRetourZone())
            retourZone = false;
    }

    protected void poursuiteJoueur() {
        if (!retourZone && Math.abs(getEnv().getJoueur().getX() - getX()) < 5 * TUILE_TAILLE && Math.abs(getEnv().getJoueur().getY() - getY()) < 2 * TUILE_TAILLE) {
                if (getEnv().getJoueur().getX() - getX() > 0)
                    setDirection(Droit);
                else
                    setDirection(Gauche);
                poursuitJoueur = true;
        } else
            poursuitJoueur = false;
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
            if (!getAttaque())
                deplacement();
        }
    }

    @Override
    protected int getHauteurMax() {
        return 2 * TUILE_TAILLE;
    }

    @Override
    protected int getVitesse() {
        return 4;
    }

    public final boolean getAttaque() {
        return attaqueProperty.getValue();
    }
    
    public final BooleanProperty getAttaqueProperty() {
        return attaqueProperty;
    }

    public boolean getRetourZone() {
        return retourZone;
    }

    public boolean getPoursuitJoueur() {
        return poursuitJoueur;
    }
}