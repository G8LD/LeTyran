package application.modele.personnages;

import application.modele.Direction;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.MapJeu;
import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.objets.Arbre;
import application.modele.objets.Bois;
import application.modele.objets.Coffre;
import application.modele.objets.Materiau;
import javafx.beans.property.*;
import javafx.scene.media.AudioClip;

import static application.modele.Direction.*;

public abstract class Personnage extends Entite {

    private String id;
    private ObjectProperty<Direction> directionProperty;
    private boolean saute;
    private boolean tombe;
    private double hauteurSaut;
    private int distancePoussee;

    public Personnage(Environnement env) {
        super(env);
        id = "Joueur";
        saute = false; tombe = false;
        directionProperty = new SimpleObjectProperty<>(Droit);
        hauteurSaut = 0;
        distancePoussee = 0;
        //this.getCollider().scaleCollider(32,32);
        //System.out.println(this.getCollider());
        //System.out.println(this.getCollider().getHitBox());
        //inventaire.ajouterObjet();
    }

    public Personnage(Environnement env, String id, int x, int y) {
        super(env, x, y);
        this.id = id;
        saute = false; tombe = false;
        directionProperty = new SimpleObjectProperty<>(Droit);
        hauteurSaut = 0;
        distancePoussee = 0;
        //this.getCollider().scaleCollider(32,32);
        //System.out.println(this.getCollider());
        //System.out.println(this.getCollider().getHitBox());
        //inventaire.ajouterObjet();
    }

    protected void seDeplacer() {
        switch (directionProperty.getValue()) {
            case Droit:
                if(this.getCollider().tracerLigne(this.getX(), this.getY(), 32, 0) == null) {
                    this.ajouterForceHorizontal(-2);
                }
                break;
            case Gauche:
                this.ajouterForceHorizontal(2);
        }
        /*int distance;
        if (tombe || saute)
            distance = getVitesse() - 1;
        else
            distance = getVitesse();
        int i = 0;

        int x = 0;

        switch (directionProperty.getValue()) {
            case Droit:
                x = 1;
                break;
            case Gauche:
                x = -1;
                break;

        }
        System.out.println(x);
        if(this.getCollider().tracerLigne(this.getX(), this.getY(), x * MapJeu.TUILE_TAILLE, 0) == null) {
            System.out.println("ok");
            super.setX(super.getX() + x);
        }
        /*while (i < distance && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), directionProperty.getValue())) {
            i++;
            if (directionProperty.getValue() == Direction.Droit)
                super.setX(super.getX() + 0.45f);
            else
                super.setX(super.getX() - 0.45f);
        }*/
    }

    protected void sauter() {
        /*int i = 0;
        while (i < getVitesse() && !tombe && hauteurSaut < getHauteurMax() && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), Haut)) {
            i++;
            super.setY(super.getY() - 0.60f);
            hauteurSaut +=0.60f;
        }
        if (i < getVitesse())
            saute = false;*/
        //if(this.getCollider().tracerLigne(this.getX(), this.getY(), 0, 64) != null) {
        this.ajouterForceVertical(10);
        //}
    }

    /*protected void tomber() {
        int i = 0;
        while (i < getVitesse() && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), Direction.Bas)) {
            i++;
            tombe = true;
            super.setY(super.getY() + 0.60f);
        }

        if (i < getVitesse()) {
            tombe = false;
            hauteurSaut = 0;
        }
    }*/

    protected void estPoussee() {
        Direction direction;
        if (distancePoussee > 0)
            direction = Droit;
        else
            direction = Gauche;
        int i = 0;
        while (i < 3 && distancePoussee != 0 && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), direction)) {
            i++;
            tombe = true;
            if (direction == Droit) {
                super.setX(super.getX() + 1);
                distancePoussee--;
            } else {
                super.setX(super.getX() - 1);
                distancePoussee++;
            }
        }

        if (i < 3) {
            distancePoussee = 0;
        }
    }

    public void update() {
        super.update();
        //seDeplacer();
    }

    //region Getter & Setter
    protected abstract int getHauteurMax();

    protected abstract int getVitesse();

    public final Direction getDirection() {
        return directionProperty.getValue();
    }

    public final void setDirection(Direction direction) {
        this.directionProperty.setValue(direction);
    }

    public final ObjectProperty getDirectionProperty() {
        return directionProperty;
    }

    public boolean getSaute() {
        return saute;
    }

    public void setSaute(boolean saute) {
        this.saute = saute;
    }

    public boolean getTombe() {
        return tombe;
    }

    public void setTombe(boolean tombe) {
        this.tombe = tombe;
    }

    public String getId() {
        return id;
    }

    public int getDistancePoussee() {
        return distancePoussee;
    }

    public void setDistancePoussee(int distancePoussee) {
        this.distancePoussee = distancePoussee;
    }

    public Arme getArme() {
        return null;
    }
    //endregion
}
