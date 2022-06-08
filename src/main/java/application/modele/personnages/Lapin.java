package application.modele.personnages;

import application.modele.Environnement;
import application.modele.armes.Arme;

public class Lapin extends Ennemi {

    public Lapin(Environnement env, Arme arme, int x, int y, int distance) {
        super(env, arme, x, y, distance);
    }
}
