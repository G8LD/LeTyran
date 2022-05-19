package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Pierre extends Minerai {

    public Pierre(int x, int y) {
        super(x, y);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Pioche) {
            decrementerPv(3);
        } else {
            decrementerPv(2);
        }
    }
}
