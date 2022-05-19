package application.modele.armes;

import application.modele.Personnage;

public class Arc extends Arme{
    private Personnage ennemi ;

    public Arc(int qualite, int degats, Personnage ennemi) {
        super(qualite, degats, 6);
        this.ennemi=ennemi;
    }
    @Override
    public void frapper() {

    }

    public void nbDegat(int qualite) {
        if (qualite == 1) {
            super.setDegats(3);
        } else if (qualite == 2) {
            super.setDegats(5);
        } else {
            super.setDegats(8);
        }
    }
    public void distanceDeRecul(int qualite) {
        if (qualite == 1) {
            ennemi.setX(ennemi.getX()-3);
        } else if (qualite == 2) {
            ennemi.setX(ennemi.getX()-6);
        } else {
            ennemi.setX(ennemi.getX()-9);
        }
    }

}
