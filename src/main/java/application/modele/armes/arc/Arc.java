package application.modele.armes.arc;

import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Arc extends Arme {

    public Arc(Environnement env, int qualite) {
        super(env, qualite);
    }

    @Override
    public void frapper(Personnage perso, Personnage ennemi) {
        if (perso instanceof Joueur) {
            if (((Joueur) perso).getInventaire().recupererNombreRessources("Fleche") > 0) {
                ((Joueur) perso).getInventaire().retirerNbRessource("Fleche", 1);
                getEnv().getListeFleches().add(new Fleche(getEnv(), perso, getDistance() * TUILE_TAILLE, nbDegat()));
                decrementerPv();
            }
        } else
            getEnv().getListeFleches().add(new Fleche(getEnv(), perso, getDistance()*TUILE_TAILLE, nbDegat()));
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
}
