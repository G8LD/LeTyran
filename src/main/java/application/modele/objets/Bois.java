package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Hache;

public class Bois extends Materiau {

    public Bois(int x, int y) {
        super(x, y);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Hache) {
            decrementerPv(9);
        } else {
            decrementerPv(5);
        }
    }
}
