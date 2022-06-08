package application.modele;

import application.modele.collisions.Collider;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Entite {
    private FloatProperty xProperty;
    private FloatProperty yProperty;
    private Environnement env;
    private Collider collider;
    private IntegerProperty pv;


    private boolean tombe = false;

    public Entite(Environnement env) {
        this.xProperty = new SimpleFloatProperty(0);
        this.yProperty = new SimpleFloatProperty(0);
        this.collider = new Collider(this);
        //this.getCollider().scaleCollider(32,32);
        this.env = env;
        this.pv = new SimpleIntegerProperty(100);
    }


    //Pour les matériaux
    public Entite(Environnement env, int x, int y) {
        this.xProperty = new SimpleFloatProperty(x);
        this.yProperty = new SimpleFloatProperty(y);
        this.env = env;
        this.collider = new Collider(this);
        this.pv = new SimpleIntegerProperty(100);
    }

    public Entite(Environnement env, int x, int y, int pv) {
        this.xProperty = new SimpleFloatProperty(x);
        this.yProperty = new SimpleFloatProperty(y);
        this.env = env;
        this.collider = new Collider(this);
        this.pv = new SimpleIntegerProperty(pv);
    }

    public Entite() {
        this.xProperty = new SimpleFloatProperty(0);
        this.yProperty = new SimpleFloatProperty(0);
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

    public void detruire() {
    }

    public void collide() {
        if(!this.getCollider().getIgnoreCollision()) {
            for (int i = 0; i < this.env.getListeEntites().size(); i++) {
                Entite ent = this.env.getListeEntites().get(i);
                if (ent != this && !ent.getCollider().getIgnoreCollision() && this.getCollider().intersect(ent)) {
                    //System.out.println("x:" + this.getX() + " y : " + this.getY() + " x:" + ent.getX() + " y :" + ent.getY());
                    this.quandCollisionDetectee(ent);
                }
            }
        }
    }

    public void decrementerPV() {
        pv.setValue(pv.getValue() - 1);
    }

    //Fonctions qui ont pour but d'être override
    public void quandCollisionDetectee(Entite ent) {}

    //region Getter & Setter
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
    //endregion
}
