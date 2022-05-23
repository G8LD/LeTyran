package application.modele.armes;

public class Epee extends Arme {

    public Epee(int qualite, int degats) {
        super(qualite, degats, 1);
    }

    @Override
    public void frapper() {

    }

    public void nbDegat(int qualite) {
        if (qualite == 1) {
            super.setDegats(2);
        } else if (qualite == 2) {
            super.setDegats(5);
        } else {
            super.setDegats(8);
        }
    }
}
