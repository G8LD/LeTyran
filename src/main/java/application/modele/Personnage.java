package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Hache;
import application.modele.armes.Pioche;
import javafx.beans.property.*;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Personnage extends Entite {


    private Direction direction;
    private Environnement env;
    private Inventaire inventaire;
    private boolean saute;
    private boolean tombe;
    private BooleanProperty avanceProperty;
    private int hauteurSaut;
    private ObjectProperty<Arme> armeProperty;


    public Personnage(Environnement env) {
        saute = false; tombe = false;
        avanceProperty = new SimpleBooleanProperty(false);

        direction = Direction.Droit;
        hauteurSaut = 0;
        armeProperty = new SimpleObjectProperty<>(new Pioche(1));
        this.env = env;
        this.inventaire = new Inventaire(this.env);
        //inventaire.ajouterObjet();
    }

    public void seDeplacer() {
        int distance;
        if (tombe || saute)
            distance = 2;
        else
            distance = 3;
        for (int i = 0; i < distance; i++)
        if(!env.entreEnCollision(super.getX(), super.getY(), direction)) {
            if (direction == Direction.Droit)
                super.setX(super.getX() + 1);
            else
                super.setX(super.getX() - 1);
        }
    }

    public void sauter() {
        for (int i = 0; i < 3; i++)
        if (!tombe && hauteurSaut < 2 * TUILE_TAILLE && !env.entreEnCollision(super.getX(), super.getY(), Direction.Haut)) {
            super.setY(super.getY()- 1);
            hauteurSaut +=1;
        } else if (saute) {
            saute = false;
        }
    }

    public void tomber() {
        for (int i = 0; i < 3; i++)
        if (!env.entreEnCollision(super.getX(), super.getY(), Direction.Bas)) {
            tombe = true;
            super.setY(super.getY() + 1);
        } else {
            tombe = false;
            hauteurSaut = 0;
        }
    }

    public void update() {
        if (saute) sauter();
        else tomber();
        if (avanceProperty.getValue()) seDeplacer();

    }

//region Getter & Setter
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public Inventaire getInventaire() {
        return this.inventaire;
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
