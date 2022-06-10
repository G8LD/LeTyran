package application.modele.armes.arc;

import application.modele.Direction;
import application.modele.Entite;
import application.modele.Environnement;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Fleche extends Entite {
    
    private static int idMax = 0;
    
    private String id;
    private Direction direction;
    private int distanceMax;
    private float distanceParcourue;
    private int degat;
    private boolean touche;

    public Fleche() {
    }

    public Fleche(Environnement env, int x, int y, Direction direction, int distanceMax, int degat) {
        super(env, x, y);
        this.direction = direction;
        this.distanceMax = distanceMax;
        this.degat = degat;
        id = "Fleche" + idMax++;
        distanceParcourue = 0;
        touche = false;
    }

    private void seDeplace() {
        int i = 0;
        while (i < 5 && distanceParcourue < distanceMax) {
            i++;
            switch (direction) {
                case Droit: super.setX(super.getX() + 0.45f); break;
                case Gauche: super.setX(super.getX() - 0.45f); break;
                case Bas: super.setY(super.getY() + 0.45f); break;
                case Haut: super.setY(super.getY() - 0.45f); break;
            }
            distanceParcourue+=0.45f;
            collide();
        }

        if (i < 3)
            getEnv().getListeFleches().remove(this);
    }

    public void update() {
        seDeplace();
    }

    @Override
    public void quandCollisionDetectee(Entite ent) {
        if (!touche) {
            System.out.println(ent);
            touche = true;
            ent.decrementerPV(degat);
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
