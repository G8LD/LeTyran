package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;

public class Bois extends Minerai {

        public Bois(int x, int y) {
            super(x, y);
        }

        @Override
        public void frappe(Arme arme) {
            if (arme instanceof Pioche && arme.getQualite() >= 2) {
                decrementerPv(3);
            } else {
                decrementerPv(2);
            }
        }

}


