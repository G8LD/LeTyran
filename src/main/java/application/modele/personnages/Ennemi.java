package application.modele.personnages;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.armes.Arme;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static application.modele.Direction.Droit;
import static application.modele.Direction.Gauche;
import static application.modele.MapJeu.TUILE_TAILLE;

public class Ennemi extends Personnage {

    private static int id = 0;

    private int origineX;
    private int origineY;
    private int distance;
    private BooleanProperty attaqueProperty;
    private int delai;
    private boolean retourZone;

    public Ennemi(Environnement env, Arme arme, int x, int y, int distance) {
        super(env, "Ennemi" + id++, arme, x * TUILE_TAILLE, y * TUILE_TAILLE, 20);
        origineX = x * TUILE_TAILLE;
        origineY = y * TUILE_TAILLE;
        this.distance = distance * TUILE_TAILLE;
        attaqueProperty = new SimpleBooleanProperty(false);
        delai = 0;
        retourZone = false;
    }

    private void deplacement() {
//        if (Math.abs(getEnv().getJoueur().getX() - getX()) < distance && Math.abs(getEnv().getJoueur().getY() - getY()) < 2 * TUILE_TAILLE)
//            if (getEnv().getJoueur().getX() - getX() > 0)
//                setDirection(Droit);
//            else
//                setDirection(Gauche);
//        else if (getX() >= origineX && getX() <= origineX + distance && getY() == origineY && estBloque())
//            setDirection(getDirectionOpposee());
//        else if (((getX() < origineX && getDirection() == Gauche) || (getX() > origineX + distance && getDirection() == Droit)))
//            setDirection(getDirectionOpposee());
//        else if (estBloque()) {
//            if (getDirection() == Gauche)
//                origineX = (int) getX();
//            else
//                origineX = (int) (getX() - distance);
//            origineY = (int) getY();
//        }
//        seDeplacer();
        if (((getX() < origineX - 2 * distance && getDirection() == Gauche) || (getX() > origineX + 3 * distance && getDirection() == Droit)) && !retourZone) {
            setDirection(getDirectionOpposee());
            retourZone = true;
        } else if (getX() >= origineX && getX() <= origineX + distance && getY() == origineY && retourZone)
            retourZone = false;

        if (!retourZone)
            if (Math.abs(getEnv().getJoueur().getX() - getX()) < distance && Math.abs(getEnv().getJoueur().getY() - getY()) < 2 * TUILE_TAILLE)
                if (getEnv().getJoueur().getX() - getX() > 0)
                    setDirection(Droit);
                else
                    setDirection(Gauche);
            else if (getX() >= origineX && getX() <= origineX + distance && getY() == origineY && estBloque())
                setDirection(getDirectionOpposee());
            else if (((getX() < origineX && getDirection() == Gauche) || (getX() > origineX + distance && getDirection() == Droit)))
                setDirection(getDirectionOpposee());
            else if (estBloque()) {
                if (getDirection() == Gauche)
                    origineX = (int) getX();
                else
                    origineX = (int) (getX() - distance);
                origineY = (int) getY();
            }
        seDeplacer();
    }

    private boolean estBloque() {
        return super.getEnv().entreEnCollision((int) super.getX(), (int) super.getY(), getDirection())
                && !super.getEnv().entreEnCollision((int) super.getX(), (int) super.getY(), getDirectionOpposee());
    }

    private Direction getDirectionOpposee() {
        if (getDirection() == Droit)
            return Gauche;
        else
            return Droit;
    }

    private void detectionJoueur() {
        if (joueurEnFace()) {
            attaqueProperty.setValue(true);
            delai = 0;
        }
    }

    private void attaquer() {
        if (delai++ >= 20) {
            if (joueurEnFace())
                getArme().frapper(getEnv().getJoueur());
            attaqueProperty.setValue(false);
        }
    }

    private boolean joueurEnFace() {
        return Math.abs(getEnv().getJoueur().getX() - getX()) < TUILE_TAILLE
                && Math.abs(getEnv().getJoueur().getY() - getY()) < TUILE_TAILLE
                && ((getDirection() == Gauche && getEnv().getJoueur().getX() - getX() <= 0)
                || (getDirection() == Droit && getEnv().getJoueur().getX() - getX() >= 0));
    }
    @Override
    public void update() {
        tomber();
        if (attaqueProperty.getValue())
            attaquer();
        if (!attaqueProperty.getValue())
            detectionJoueur();
        if (!attaqueProperty.getValue())
            deplacement();
    }


    @Override
    protected int getHauteurMax() {
        return 2 * TUILE_TAILLE;
    }

    @Override
    protected int getVitesse() {
        return 4;
    }

    public final BooleanProperty getAttaqueProperty() {
        return attaqueProperty;
    }
}
