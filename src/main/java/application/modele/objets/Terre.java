package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Terre extends Minerai {

    public Terre(int x, int y) {
        super(x, y);
    }

    @Override
    public void frappe(Arme arme) {
        decrementerPv(6);
    }
}
