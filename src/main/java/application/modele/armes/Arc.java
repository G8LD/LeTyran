package application.modele.armes;

import application.modele.Environnement;

public class Arc extends Arme{

    private final static int DISTANCE = 6;

    public Arc(Environnement env, int qualite) {
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


    public int getDistance() {
        if (getQualite() == 1) {
            return 5;
        } else if (getQualite() == 2) {
            return 7;
        } else {
            return 9;
        }
    }
    //public int nombreDeFleche(int nbFleche){

   // }
}
