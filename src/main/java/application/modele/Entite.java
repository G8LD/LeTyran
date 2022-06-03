package application.modele;

import application.modele.collisions.Collider;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Entite {
    private FloatProperty xProperty;
    private FloatProperty yProperty;
    private Environnement env;
    private Collider collider;
    private IntegerProperty pv;


    private boolean tombe = false;
    public Entite(Environnement env) {
        pv= new SimpleIntegerProperty(100);
        this.xProperty = new SimpleFloatProperty(0);
        this.yProperty = new SimpleFloatProperty(0);
        this.collider = new Collider(this);
        //this.getCollider().scaleCollider(32,32);
        this.env = env;
    }


    //Pour les matériaux
    public Entite(Environnement env, int x, int y) {
        this.xProperty = new SimpleFloatProperty(x);
        this.yProperty = new SimpleFloatProperty(y);
        this.env = env;
        this.collider = new Collider(this);
    }

    public float getX() {
        return xProperty.getValue();
    }

    public boolean getTombe() {
        return this.tombe;
    }


    public float getY() {
        return yProperty.getValue();
    }

    public void setX(float valeur) {
        xProperty.setValue(valeur);
    };

    public void setY(float valeur) {
        yProperty.setValue(valeur);
    };

    public FloatProperty getYProperty() {
        return yProperty;
    }

    public FloatProperty getXProperty() {
        return xProperty;
    }


    public Entite() {
        xProperty = new SimpleFloatProperty(6 * TUILE_TAILLE);
        yProperty = new SimpleFloatProperty(11 * TUILE_TAILLE);
    }


    public void update() {
        if(this.getCollider() != null) {
            collide();
        }
        tomber();
    }

    private void tomber() {
        for (int i = 0; i < 3; i++)
        if (!env.entreEnCollision((int) this.getX(), (int)this.getY(), Direction.Bas)) {
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

    public int getPv() {
        return this.pv.getValue();
    }

    public IntegerProperty getPVProperty()  {
        return this.pv;
    }

    public void setPv(int value) {
        this.pv.setValue(value);
    }




    public void detruire() {
        this.quandDetruit();
    }



    public void collide() {
        if(!this.getCollider().getIgnoreCollision()) {
            for (int i = 0; i < this.env.getEntites().size(); i++) {
                Entite ent = this.env.getEntites().get(i);
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
