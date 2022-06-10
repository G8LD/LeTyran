package application.modele.armes.arc;

import application.modele.Direction;
import application.modele.Entite;
import application.modele.Environnement;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Fleche extends Entite {

    private Direction direction;
    private int distanceMax;
    private float distanceParcourue;
    private int degat;

    public Fleche() {
    }

    public Fleche(Environnement env, int x, int y, Direction direction, int distance, int degat) {
        super(env, x, y);
        this.direction = direction;
        this.distanceMax = distance;
        distanceParcourue = 0;
        this.getCollider().scaleCollider(TUILE_TAILLE, TUILE_TAILLE/2);
    }

    public void update() {
        seDeplace();
    }

    private void seDeplace() {
        int i = 0;
        while (i < 3 && distanceParcourue < distanceMax) {
            i++;
            switch (direction) {
                case Droit: super.setX(super.getX() + 0.45f);
                case Gauche: super.setX(super.getX() - 0.45f);
                case Bas: super.setY(super.getY() + 0.45f);
                case Haut: super.setY(super.getY() + 0.45f);
            }
            distanceParcourue+=0.45f;
            collide();
        }

        if (i < 3)
            getEnv().getListeMateriaux().remove(this);
    }

    @Override
    public void quandCollisionDetectee(Entite ent) {
        System.out.println(ent);
        ent.decrementerPV(degat);
        getEnv().getListeMateriaux().remove(this);
    }

}
