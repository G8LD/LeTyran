package application.modele.armes;

public class Hache extends Arme {

    private final static int DISTANCE = 1;

    public Hache(int qualite) {
        super(qualite);
    }

    @Override
    public void frapper() {

    }

    public void nbDegat() {
        if (qualite == 1) {
            super.setDegats(1);
        } else if (qualite == 2) {
            super.setDegats(3);
        } else {
            super.setDegats(5);
        }
    }
}
