package application.modele.armes;

public class Pioche extends Arme {

    public Pioche(int qualite, int degats) {
        super(qualite, degats, 1);
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
