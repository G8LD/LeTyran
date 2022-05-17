package application.modele.armes;

public class Hache extends Arme {

    public Hache(int qualite, int degat) {
        super(qualite,degat, 1);
    }

    @Override
    public void frapper() {

    }

    public void nbDegat(int qualite) {
        if (qualite == 1) {
            super.setDegats(1);
        } else if (qualite == 2) {
            super.setDegats(3);
        } else {
            super.setDegats(5);
        }
    }
}
