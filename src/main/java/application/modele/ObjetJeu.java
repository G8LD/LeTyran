package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ObjetJeu extends Entite {
    private String nom;
    private int place;
    private int stack;
    private static int slotInventaire = 0;
    private IntegerProperty xProperty;
    private IntegerProperty yProperty;
    private boolean tombe = false;

    public ObjetJeu(Environnement env, int place, String nom, int stack) {
        super(env);
        this.nom = nom;
        this.place = slotInventaire++;
        this.stack = stack;

        this.xProperty = new SimpleIntegerProperty(0);
        this.yProperty = new SimpleIntegerProperty(0);


        //Collision
        super.getCollider().setIgnoreCollision(false);
        super.getCollider().setActiveVerifCollision(true);
    }

    public void setPlaceInventaire(int nouvellePlace) {
        this.place = nouvellePlace;
    }

    public int getPlaceInventaire() {
        return this.place;
    }

    public IntegerProperty getXProperty() {
        return this.xProperty;
    };
    public IntegerProperty getYProperty() {
        return this.yProperty;
    }

    @Override
    public void quandCollisionDetecte(Entite ent) {

    }

    ;

    public int getX() {
        return this.xProperty.getValue();
    }

    public int getY() {
        return this.yProperty.getValue();
    }

    public void setX(int value) {
        this.xProperty.setValue(value);
    }

    public void setY(int value) {
        this.yProperty.setValue(value);
    }

    public void update() {
        super.update();
    }

    public String toString() {
        return this.nom;
    }
}
