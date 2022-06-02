package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.objets.Materiau;
import javafx.beans.property.*;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Personnage extends Entite {

    private final static int HAUTEUR_MAX = 2 * TUILE_TAILLE;
    private final static int VITESSE = 3;

    private Direction direction;
    private Inventaire inventaire;
    private boolean saute;
    private boolean tombe;
    private BooleanProperty avanceProperty;
    private int hauteurSaut;
    private ObjectProperty<Arme> armeProperty;


    public Personnage(Environnement env) {
        super(env);
        saute = false; tombe = false;
        avanceProperty = new SimpleBooleanProperty(false);

        direction = Direction.Droit;
        hauteurSaut = 0;
        armeProperty = new SimpleObjectProperty<>(new Pioche(env, 1));
        this.inventaire = new Inventaire(super.getEnv());
        //this.getCollider().scaleCollider(32,32);
        System.out.println(this.getCollider());
        System.out.println(this.getCollider().getHitBox());
        //inventaire.ajouterObjet();
    }

    public void seDeplacer() {
        int distance;
        if (tombe || saute)
            distance = 2;
        else
            distance = VITESSE;
        int i = 0;
        while (i < distance && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), direction)) {
            i++;
            if (direction == Direction.Droit)
                super.setX(super.getX() + 0.45f);
            else
                super.setX(super.getX() - 0.45f);
        }
    }

    public void sauter() {
        int i = 0;
        while (i < VITESSE && !tombe && hauteurSaut < HAUTEUR_MAX && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), Direction.Haut)) {
            i++;
            super.setY(super.getY() - 1);
            hauteurSaut +=1;
        }
        if (i < VITESSE)
            saute = false;
    }

    public void tomber() {
        int i = 0;
        while (i < VITESSE && !super.getEnv().entreEnCollision((int)super.getX(), (int)super.getY(), Direction.Bas)) {
            i++;
            tombe = true;
            super.setY(super.getY() + 1);
        }

        if (i < VITESSE) {
            tombe = false;
            hauteurSaut = 0;
        }
    }

    public void update() {
        super.collide();
        if (saute) sauter();
        else tomber();
        if (avanceProperty.getValue()) seDeplacer();

    }

    @Override
    public void quandCollisionDetectee(Entite ent) {
        if (ent instanceof ObjetJeu || ent instanceof Materiau) {
            this.inventaire.ajouterObjet(ent);
        }
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
