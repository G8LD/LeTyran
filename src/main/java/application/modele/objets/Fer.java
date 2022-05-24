package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Fer extends Materiau {

    public Fer(int x, int y) {
        super(x, y);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Pioche) {
            if (arme.getQualite() == 3) {
                decrementerPv(9);
            } else if (arme.getQualite() == 3) {
                decrementerPv(5);
            } else
                decrementerPv(3);
        } else {
            decrementerPv(2);
        }
    }

}
