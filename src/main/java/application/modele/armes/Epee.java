package application.modele.armes;

import application.modele.Environnement;

public class Epee extends Arme {


    public Epee(Environnement env, int qualite) {
        super(env, qualite);
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
}
