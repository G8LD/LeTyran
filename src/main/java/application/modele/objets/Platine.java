package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Platine extends Minerai {

    public Platine(int x, int y) {
        super(x, y,3,"Platine");
    }

    @Override
    public void frappe(Arme arme) {
        if (arme instanceof Pioche && arme.getQualite() == 3) {
            decrementerPv(3);
        } else {
            decrementerPv(2);
        }
    }
}
