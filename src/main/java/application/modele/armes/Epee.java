package application.modele.armes;

public class Epee extends Arme {

    private final static int DISTANCE = 1;

    public Epee(int qualite) {
        super(qualite);
    }

    @Override
    public void frapper() {
    }

    public int nbDegat() {
        if (getQualite() == 1) {
            return 2;
        } else if (getQualite() == 2) {
            return 5;
        } else {
            return 8;
        }
    }
}
