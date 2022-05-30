package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Fer extends Materiau {

    private final static int PV_MAX = 5;

    public Fer(int x, int y) {
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
