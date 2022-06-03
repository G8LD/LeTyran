package application.modele.objets;

import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Platine extends Materiau {

    private final static int PV_MAX = 8;

    public Platine() {

    }

    public Platine(Environnement env, int x, int y) {
        super(env, x, y, PV_MAX);
    }
}
