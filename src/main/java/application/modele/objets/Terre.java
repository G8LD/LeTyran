package application.modele.objets;

import application.modele.armes.Arme;

public class Terre extends Materiau {

    public Terre(int x, int y) {
        super(x, y);
    }

    @Override
    public void frappe(Arme arme) {
        decrementerPv(10);
    }
}
