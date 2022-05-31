package application.modele.objets;

import application.modele.Environnement;
import application.modele.armes.Arme;

public class Terre extends Materiau {

    private final static int PV_MAX = 1;

    public Terre() {
        super();
    }

    public Terre(Environnement env, int x, int y) {
        super(env,x, y, PV_MAX);
    }

    @Override
    public void frappe(Arme arme) {
        decrementerPv(1);
    }

    public void quandDetruit() {
        Terre terre = new Terre(this.getEnv(), (int)this.getX() * 32, (int)this.getY() * 32);
        this.getEnv().getEntites().add(terre);
    }
}
