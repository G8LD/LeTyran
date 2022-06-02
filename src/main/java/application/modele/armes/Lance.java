package application.modele.armes;

import application.modele.Environnement;

public class Lance extends Arme {

    private final static int DISTANCE = 3;

    public Lance(Environnement env, int qualite) {
        super(env, qualite);
    }

    @Override
    public void frapper() {

    }

    public int nbDegat() {
        if (getQualite() == 1) {
            return 3;
        } else if (getQualite() == 2) {
            return 6;
        } else {
            return 9;
        }
    }
    public int distanceDeRecul() {
        if (getQualite() == 1) {
            return 2;
        } else if (getQualite() == 2) {
            return 4;
        } else {
            return 6;
        }
    }

}
