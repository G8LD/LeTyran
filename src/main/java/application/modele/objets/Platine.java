package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Platine extends Minerai {

    public Platine(int x, int y) {
        super(x, y);
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Pioche) {
            if (arme.getQualite() == 3) {
                decrementerPv(5);
            } else if (arme.getQualite() == 2) {
                decrementerPv(3);
            } else {
                decrementerPv(2);
            }
        } else {
            decrementerPv(1);
        }
    }
}
