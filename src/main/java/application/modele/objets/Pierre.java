package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Pierre extends Materiau {

    private final static int PV_MAX = 3;

    public Pierre(int x, int y) {
        super(x, y, PV_MAX);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Pioche) {
            decrementerPv(arme.nbDegat());
        } else {
            decrementerPv(1);
        }
    }
}
