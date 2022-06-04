package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.objets.Materiau;
import javafx.beans.property.*;

public abstract class Personnage extends Entite {

    private ObjectProperty<Direction> directionProperty;
    private boolean saute;
    private boolean tombe;
    private boolean freeze;
    private BooleanProperty avanceProperty;
    private int hauteurSaut;
    private ObjectProperty<Arme> armeProperty;

    public Personnage(Environnement env) {
        super(env);
        saute = false; tombe = false; freeze = false;
        avanceProperty = new SimpleBooleanProperty(false);
        directionProperty = new SimpleObjectProperty<>(Direction.Droit);
        hauteurSaut = 0;
        armeProperty = new SimpleObjectProperty<>(new Pioche(env, 1));
        //this.getCollider().scaleCollider(32,32);
        System.out.println(this.getCollider());
        System.out.println(this.getCollider().getHitBox());
        //inventaire.ajouterObjet();
    }

    public void seDeplacer() {
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

    public void sauter() {
        int i = 0;
        while (i < getVitesse() && !tombe && hauteurSaut < getHauteurMax() && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), Direction.Haut)) {
            i++;
            super.setY(super.getY() - 1);
            hauteurSaut +=1;
        }
        if (i < getVitesse())
            saute = false;
    }

    public void tomber() {
        int i = 0;
        while (i < getVitesse() && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), Direction.Bas)) {
            i++;
            tombe = true;
            super.setY(super.getY() + 1);
        }

        if (i < getVitesse()) {
            tombe = false;
            hauteurSaut = 0;
        }
    }

    public void update() {
        super.collide();
        if (!freeze) {
            if (saute) sauter();
            if (avanceProperty.getValue()) seDeplacer();
        }
        if (!saute) tomber();

    }

    public void freezer() {
        freeze = !freeze;
    }

    protected abstract int getHauteurMax();

    protected abstract int getVitesse();

    //region Getter & Setter
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

    public final boolean getAvance() {
        return avanceProperty.getValue();
    }

    public final BooleanProperty getAvanceProperty() {
        return avanceProperty;
    }

    public void setAvance(boolean avance) {
        this.avanceProperty.setValue(avance);
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
    //endregion
}
