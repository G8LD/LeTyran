package application.modele.objets;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import javafx.scene.image.Image;

public class Fer extends Minerai {

    public Fer(int x, int y) {
        super(x, y,"Fer");
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
