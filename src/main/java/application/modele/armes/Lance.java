package application.modele.armes;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Lance extends Arme {

    public Lance(Environnement env, int qualite) {
        super(env, qualite);
    }

    @Override
    public void frapper(Personnage perso, Personnage ennemi) {
        ennemi.decrementerPv(nbDegat());
        if (perso instanceof Joueur)
            decrementerPv();
        if (perso.getDirection() == Direction.Droit)
            ennemi.setDistancePoussee((getQualite()+1) * TUILE_TAILLE);
        else
            ennemi.setDistancePoussee(-(getQualite()+1) * TUILE_TAILLE);
    }

    public int getDistance() {
        return 2;
    }
}
