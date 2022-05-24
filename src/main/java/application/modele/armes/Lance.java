package application.modele.armes;

public class Lance extends Arme {

    private final static int DISTANCE = 3;

    public Lance(int qualite) {
        super(qualite);
    }

    @Override
    public void frapper() {

    }

    public int nbDegat() {
        if (getQualite() == 1) {
            return 3;
        } else if (getQualite() == 2) {
            return 5;
        } else {
            return 8;
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
