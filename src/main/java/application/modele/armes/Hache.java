package application.modele.armes;

import application.modele.Environnement;

public class Hache extends Arme {

    private final static int DISTANCE = 1;

    public Hache(Environnement env, int qualite) {
        super(env, qualite);
    }

    @Override
    public void frapper() {

    }

    public int nbDegat() {
        if (getQualite() == 1) {
            return 2;
        } else if (getQualite() == 2) {
            return 3;
        } else {
            return 5;
        }
    }
}
