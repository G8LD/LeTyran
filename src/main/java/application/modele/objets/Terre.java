package application.modele.objets;

import application.modele.armes.Arme;

public class Terre extends Materiau {

    private final static int PV_MAX = 1;

    public Terre(int x, int y) {
        super(x, y, PV_MAX);
    }

    @Override
    public void frappe(Arme arme) {
        decrementerPv(1);
    }
}
