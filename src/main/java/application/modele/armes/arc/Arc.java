package application.modele.armes.arc;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.personnages.Personnage;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Arc extends Arme {

    public Arc(Environnement env, int qualite) {
        super(env, qualite);
    }

    @Override
    public void frapper(Personnage perso, Personnage ennemi) {
        getEnv().getListeFleches().add(new Fleche(getEnv(), perso, getDistance()*TUILE_TAILLE, nbDegat()));
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
        if (getQualite() == 1) {
            return 5;
        } else if (getQualite() == 2) {
            return 7;
        } else {
            return 9;
        }
    }
    //public int nombreDeFleche(int nbFleche){

   // }
}
