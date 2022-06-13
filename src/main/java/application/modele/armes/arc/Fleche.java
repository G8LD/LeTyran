package application.modele.armes.arc;

import application.modele.Direction;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.objets.Arbre;
import application.modele.objets.Materiau;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Fleche extends Entite {
    
    private static int idMax = 2;
    
    private String id;
    private Personnage perso;
    private Direction direction;
    private int distanceMax;
    private float distanceParcourue;
    private int degat;
    private boolean touche;

    public Fleche() {
    }

    public Fleche(Environnement env, Personnage perso, int distanceMax, int degat) {
        super(env, (int) perso.getX(), (int) (perso.getY() - 10));
        this.perso = perso;
        this.direction = perso.getDirection();
        this.distanceMax = distanceMax;
        this.degat = degat;
        id = "Fleche" + idMax++;
        distanceParcourue = 0;
        touche = false;
    }

    private void seDeplace() {
        int i = 0;
        while (i < 7 && distanceParcourue < distanceMax) {
            i++;
            switch (direction) {
                case Droit: setX(getX() + 0.5f); break;
                case Gauche: setX(getX() - 0.5f); break;
                case Bas: setY(getY() + 0.5f); break;
                case Haut: setY(getY() - 0.5f); break;
            }
            distanceParcourue+=0.5f;
            collision();
            //collide();
        }

        if (i < 3)
            getEnv().getListeFleches().remove(this);
    }

    private void collision() {
        Entite entite = null;
        if (Math.abs(getEnv().getJoueur().getX() - getX()) < 1
                && getEnv().getJoueur().getY() - getY() > 0 && getEnv().getJoueur().getY() - getY() < TUILE_TAILLE)
            entite = getEnv().getJoueur();
        else {
            for (int j = 0; j < getEnv().getListeEnnemis().size() && entite == null; j++)
                if (Math.abs(getEnv().getListeEnnemis().get(j).getX() - getX()) < 1
                        && getEnv().getListeEnnemis().get(j).getY() - getY() > 0 && getEnv().getListeEnnemis().get(j).getY() - getY() < TUILE_TAILLE)
                    entite = getEnv().getListeEnnemis().get(j);
            if (entite == null)
                if (direction == Direction.Gauche)
                    entite = getEnv().getMinerai((int) (getX()/TUILE_TAILLE), (int) (getY()/TUILE_TAILLE)+1);
                else
                    entite = getEnv().getMinerai((int) (getX()/TUILE_TAILLE)+1, (int) (getY()/TUILE_TAILLE)+1);
        }

        if (entite != null)
            quandCollisionDetectee(entite);
    }

    public void update() {
        seDeplace();
    }

    @Override
    public void quandCollisionDetectee(Entite ent) {
        if (!touche && ent != perso && !(ent instanceof Fleche) && !(ent instanceof Arbre)) {
            touche = true;
            if (!(ent instanceof Materiau))
                ent.decrementerPv(degat);
            getEnv().getListeFleches().remove(this);
        }
    }

    public String getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }
}
