package application.modele.objets;

import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Pierre extends Materiau {

    private final static int PV_MAX = 3;

    public Pierre(Environnement env) {
        super(env);
    }

    public Pierre(Environnement env, int x, int y) {
        super(env, x, y, PV_MAX);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Pioche) {
            decrementerPv(arme.nbDegat());
        } else {
            decrementerPv(1);
        }
    }

    @Override
    public void quandDetruit() {
        Pierre pierre = new Pierre(this.getEnv(), (int)this.getX() * 32, (int)this.getY() * 32);
        this.getEnv().getEntites().add(pierre);
    }
}
