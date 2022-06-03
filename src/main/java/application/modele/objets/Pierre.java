package application.modele.objets;

import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Pierre extends Materiau {

    private final static int PV_MAX = 3;

    public Pierre() {
    }

    public Pierre(Environnement env, int x, int y) {
        super(env, x, y, PV_MAX);
    }
}
