package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Hache;

public class Bois extends Materiau {

    private final static int PV_MAX = 2;

    public Bois(int x, int y) {
        super(x, y, PV_MAX);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Hache) {
            decrementerPv(2);
        } else {
            decrementerPv(1);
        }
    }
}
