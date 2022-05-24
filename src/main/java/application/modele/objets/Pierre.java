package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Pierre extends Materiau {

    public Pierre(int x, int y) {
        super(x, y);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Pioche) {
            if (arme.getQualite() == 1)
                decrementerPv(5);
            else
                decrementerPv(9);
        } else {
            decrementerPv(3);
        }
    }
}
