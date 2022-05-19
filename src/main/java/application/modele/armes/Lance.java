package application.modele.armes;

import application.modele.Personnage;

public class Lance extends Arme{
    private Personnage ennemi ;

    public Lance(int qualite, int degats, Personnage ennemi) {
        super(qualite, degats, 3);
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
           ennemi.setX(ennemi.getX()-2);
        } else if (qualite == 2) {
            ennemi.setX(ennemi.getX()-4);
        } else {
            ennemi.setX(ennemi.getX()-6);
        }

    }

}
