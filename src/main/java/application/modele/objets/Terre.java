package application.modele.objets;

import application.modele.Environnement;
import application.modele.armes.Arme;

public class Terre extends Materiau {

    private final static int PV_MAX = 0;

    public Terre() {
    }

    public Terre(Environnement env, int x, int y) {
        super(env,x, y, PV_MAX);
    }

    @Override
    public void frappe() {
        detruire();
    }

}
