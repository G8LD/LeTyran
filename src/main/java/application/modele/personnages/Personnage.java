package application.modele.personnages;

import application.modele.Direction;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.objets.Arbre;
import application.modele.objets.Bois;
import application.modele.objets.Coffre;
import application.modele.objets.Materiau;
import javafx.beans.property.*;
import javafx.scene.media.AudioClip;

public abstract class Personnage extends Entite {

    private String id;
    private ObjectProperty<Direction> directionProperty;
    private boolean saute;
    private boolean tombe;
    private float hauteurSaut;
    private int distancePoussee;
    private ObjectProperty<Arme> armeProperty;

   // private AudioClip bruitCoffre = new AudioClip(getClass().getResource("/application/sons/coffreBruit.mp3").toExternalForm());

    public Personnage(Environnement env, Arme arme) {
        super(env);
        id = "Joueur";
        saute = false; tombe = false;
        directionProperty = new SimpleObjectProperty<>(Direction.Droit);
        hauteurSaut = 0;
        distancePoussee = 0;
        armeProperty = new SimpleObjectProperty<>(arme);
        //this.getCollider().scaleCollider(32,32);
        //System.out.println(this.getCollider());
        //System.out.println(this.getCollider().getHitBox());
        //inventaire.ajouterObjet();
    }

    public Personnage(Environnement env, String id, Arme arme, int x, int y) {
        super(env, x, y);
        this.id = id;
        saute = false; tombe = false;
        directionProperty = new SimpleObjectProperty<>(Direction.Droit);
        hauteurSaut = 0;
        distancePoussee = 0;
        armeProperty = new SimpleObjectProperty<>(arme);
        //this.getCollider().scaleCollider(32,32);
        //System.out.println(this.getCollider());
        //System.out.println(this.getCollider().getHitBox());
        //inventaire.ajouterObjet();
    }

    public Personnage(Environnement env, String id, int x, int y) {
        super(env, x, y);
        this.id = id;
        saute = false; tombe = false;
        directionProperty = new SimpleObjectProperty<>(Direction.Droit);
        hauteurSaut = 0;
        distancePoussee = 0;
        armeProperty = new SimpleObjectProperty<>();
        //this.getCollider().scaleCollider(32,32);
        //System.out.println(this.getCollider());
        //System.out.println(this.getCollider().getHitBox());
        //inventaire.ajouterObjet();
    }

    protected void seDeplacer() {
        int distance;
        if (tombe || saute)
            distance = getVitesse() - 1;
        else
            distance = getVitesse();
        int i = 0;
        while (i < distance && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), directionProperty.getValue())) {
            i++;
            if (directionProperty.getValue() == Direction.Droit)
                super.setX(super.getX() + 0.45f);
            else
                super.setX(super.getX() - 0.45f);
        }
    }

    protected void sauter() {
        int i = 0;
        while (i < getVitesse() && !tombe && hauteurSaut < getHauteurMax() && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), Direction.Haut)) {
            i++;
            super.setY(super.getY() - 0.60f);
            hauteurSaut +=0.60f;
        }
        if (i < getVitesse())
            saute = false;
    }

    protected void tomber() {
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
    }

    protected void estPoussee() {
        Direction direction;
        if (distancePoussee > 0)
            direction = Direction.Droit;
        else
            direction = Direction.Gauche;
        int i = 0;
        while (i < 3 && distancePoussee != 0 && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), direction)) {
            i++;
            tombe = true;
            if (direction == Direction.Droit) {
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

    public abstract void update();

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

    public final Arme getArme() {
        return armeProperty.getValue();
    }

    public final ObjectProperty<Arme> getArmeProperty() {
        return armeProperty;
    }

    public final void setArme(Arme arme) {
        armeProperty.setValue(arme);
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

    //endregion
}
