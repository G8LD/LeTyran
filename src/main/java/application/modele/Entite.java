package application.modele;

import application.modele.collisions.Collider;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Entite {
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private Environnement env;
    private Collider collider;


    private boolean tombe = false;
    public Entite(Environnement env) {
        this.xProperty = new SimpleIntegerProperty(0);
        this.yProperty = new SimpleIntegerProperty(0);
        this.collider = new Collider(this);
        //this.getCollider().scaleCollider(32,32);
        this.env = env;
    }


    //Pour les matériaux
    public Entite(Environnement env, int x, int y) {
        this.xProperty = new SimpleIntegerProperty(x);
        this.yProperty = new SimpleIntegerProperty(y);
        this.env = env;
        this.collider = new Collider(this);
    }

    public int getX() {
        return xProperty.getValue();
    }

    public boolean getTombe() {
        return this.tombe;
    }


    public int getY() {
        return yProperty.getValue();
    }

    public void setX(int valeur) {
        xProperty.setValue(valeur);
    };

    public void setY(int valeur) {
        yProperty.setValue(valeur);
    };

    public IntegerProperty getYProperty() {
        return yProperty;
    }

    public IntegerProperty getXProperty() {
        return xProperty;
    }


    public Entite() {
        xProperty = new SimpleIntegerProperty(6 * TUILE_TAILLE);
        yProperty = new SimpleIntegerProperty(11 * TUILE_TAILLE);
    }


    public void update() {
        if(this.getCollider() != null) {
            collide();
        }
        tomber();
    }

    private void tomber() {
        for (int i = 0; i < 3; i++)
        if (!env.entreEnCollision(this.getX(), this.getY(), Direction.Bas)) {
            tombe = true;
            this.setY(this.getY() + 1);
        }
        tombe = false;
    }

    public Collider getCollider() {
        return this.collider;
    }

    public Environnement getEnv() {
        return env;
    }



    public void detruire() {
        this.quandDetruit();
    }



    public void collide() {
        if(!this.getCollider().getIgnoreCollision()) {
            for (Entite ent : this.getEnv().getEntites()) {
                if (ent != this && !ent.getCollider().getIgnoreCollision() && this.getCollider().intersect(ent)) {
                    //System.out.println("x:" + this.getX() + " y : " + this.getY() + " x:" + ent.getX() + " y :" + ent.getY());
                    this.quandCollisionDetectee(ent);
                }
            }
        }
    }

    //Fonctions qui ont pour but d'être override
    public void quandCollisionDetectee(Entite ent) {}

    public void quandDetruit() {}
}
