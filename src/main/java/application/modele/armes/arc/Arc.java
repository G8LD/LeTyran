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
            int i = 0;
            while (i < ((Joueur) perso).getInventaire().getObjets().size()
                    && !((Joueur) perso).getInventaire().getObjets().get(i).getEntite().getClass().getSimpleName().equals("Fleche")) i++;
            if (i < ((Joueur) perso).getInventaire().getObjets().size()) {
                getEnv().getListeFleches().add(new Fleche(getEnv(), perso, getDistance() * TUILE_TAILLE, nbDegat()));
                ((Joueur) perso).getInventaire().getObjets().get(i).retirerDansStack();
                decrementerPv();
            }
        } else
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
}
