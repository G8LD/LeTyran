package application.modele.armes;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.personnages.Personnage;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Lance extends Arme {


    public Lance(Environnement env, int qualite) {
        super(env, qualite);
    }

    @Override
    public void frapper(Personnage perso, Personnage ennemi) {
        ennemi.decrementerPV(nbDegat());
        decrementerPV();
        if (perso.getDirection() == Direction.Droit)
            ennemi.setDistancePoussee((getQualite()+1) * TUILE_TAILLE);
        else
            ennemi.setDistancePoussee(-(getQualite()+1) * TUILE_TAILLE);
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
        return 2;
    }
}
