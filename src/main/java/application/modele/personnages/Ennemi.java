package application.modele.personnages;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.armes.Arme;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Ennemi extends Personnage {

    private int origine;
    private int distance;

    public Ennemi(Environnement env, Arme arme, int x, int y, int distance) {
        super(env, arme, x, y);
        origine = x;
        this.distance = distance;
    }

    private void deplacement() {
        if (getX() < origine || getX() > origine + distance)
            changerDirection();
        seDeplacer();
    }

    private void changerDirection() {
        if (getDirection() == Direction.Droit)
            setDirection(Direction.Gauche);
        else
            setDirection(Direction.Droit);
    }

    @Override
    public void update() {
        deplacement();
    }

    @Override
    protected int getHauteurMax() {
        return 2 * TUILE_TAILLE;
    }

    @Override
    protected int getVitesse() {
        return 2;
    }
}
