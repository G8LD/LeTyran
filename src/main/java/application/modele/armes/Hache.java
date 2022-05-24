package application.modele.armes;

public class Hache extends Arme {

    private final static int DISTANCE = 1;

    public Hache(int qualite) {
        super(qualite);
    }

    @Override
    public void frapper() {

    }

    public int nbDegat() {
        if (getQualite() == 1) {
            return 1;
        } else if (getQualite() == 2) {
            return 3;
        } else {
            return 5;
        }
    }
}
