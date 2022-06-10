package application.modele.personnages;

import application.modele.Environnement;
import application.modele.armes.Arme;

import static application.modele.Direction.Droit;
import static application.modele.Direction.Gauche;
import static application.modele.MapJeu.TUILE_TAILLE;

public class Lapin extends Ennemi {

    private static int id = 0;

    public Lapin(Environnement env, int x, int y, int distance) {
        super(env, "Lapin" + id++, x, y, distance);
    }

    @Override
    public void deplacement() {
        if (getX() >= getOrigineX() && getX() <= getOrigineX() + getDistance() && getY() == getOrigineY() && estBloque())
            setDirection(getDirectionOpposee());
        else if (((getX() < getOrigineX() && getDirection() == Gauche) || (getX() > getOrigineX() + getDistance() && getDirection() == Droit)))
            setDirection(getDirectionOpposee());
        else if (estBloque()) {
            if (getDirection() == Gauche)
                setOrigineX(getX());
            else
                setOrigineX(getX() - getDistance());
            setOrigineY(getY());
        }
        seDeplacer();
    }
    
    @Override
    public void update() {
        tomber();
        deplacement();
    }

    @Override
    protected int getVitesse() {
        return 3;
    }


}
