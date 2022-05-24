package application.modele;

import application.modele.collisions.Collider;
import application.modele.collisions.RectangleCol;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entite {
    private Environnement env;
    private Collider collider;

    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private boolean ignoreCollision;


    public Entite(Environnement env) {
        this.env = env;
        this.ignoreCollision = false;
        this.collider = new Collider(this);
        this.collider.scaleCollider(10,10);
        this.xProperty = new SimpleIntegerProperty(0);
        this.yProperty = new SimpleIntegerProperty(0);
    }

    public void setIgnoreCollision(boolean ignore) {
        this.ignoreCollision = ignore;
    }

    public boolean getIgnoreCollision() {
        return this.ignoreCollision;
    }


    public int getX() {
        return xProperty.getValue();
    }


    public void setX(int x) {
        this.xProperty.set(x);
    }

    public void setY(int y) {
        this.yProperty.set(y);
    }

    public int getY() {
        return yProperty.getValue();
    }

    public IntegerProperty getXProperty() {
        return xProperty;
    }

    public IntegerProperty getYProperty() {
        return yProperty;
    }

    public Environnement getEnv() {
        return env;
    }

    public Collider getCollider() {
        return this.collider;
    }

    public abstract void quandCollisionDetecte(Entite ent);



    public void collide() {



        for(int i = 0; i < this.env.getObjets().size(); i++) {
            Entite ent = this.env.getObjets().get(i);
            //System.out.println(ent + " | " + this);
            if(!ent.ignoreCollision && ent != this) {
                if (this.collider.intersect(ent)) {
                    this.quandCollisionDetecte(ent);
                }
            }
        }
        //System.out.println(String.format("HG :%s HD:%s BG:%s BD:%s ", pointHautGauche, pointHautDroit, pointBasGauche, pointBasDroit));
    }

    public void update() {
        this.collide();
    }
}
