package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Pioche;
import application.modele.objets.Arbre;
import application.modele.objets.Bois;
import application.modele.objets.Coffre;
import application.modele.objets.Materiau;
import javafx.beans.property.*;
import javafx.scene.media.AudioClip;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Personnage extends Entite {

    private final static int HAUTEUR_MAX = 2 * TUILE_TAILLE;
    private final static int VITESSE = 3;

    private ObjectProperty<Direction> directionProperty;
    private Inventaire inventaire;
    private boolean saute;
    private boolean tombe;
    private boolean freeze;
    private BooleanProperty avanceProperty;
    private int hauteurSaut;
    private ObjectProperty<Arme> armeProperty;

    private AudioClip bruitCoffre = new AudioClip(getClass().getResource("/application/sons/coffreBruit.mp3").toExternalForm());



    public Personnage(Environnement env) {
        super(env);
        saute = false; tombe = false; freeze = false;
        avanceProperty = new SimpleBooleanProperty(false);

        directionProperty = new SimpleObjectProperty<>(Direction.Droit);
        hauteurSaut = 0;
        armeProperty = new SimpleObjectProperty<>(new Pioche(env, 1));
        this.inventaire = new Inventaire(super.getEnv());
    }

    public void interagit(int x, int y) {
        if (!miner(x,y))
            if(!couper(x,y)) {
                ouvert(x,y);
            }
    }

    private boolean ouvert(int x, int y){
        Coffre coffre = getEnv().getCoffre(x, y);
        if(coffre != null){
            //On baisse le son de l'audio
            bruitCoffre.setVolume(5. / 30.);
            bruitCoffre.play();

            for(int i=0 ; i<coffre.getLoot().size();i++) {
                this.getInventaire().ajouterObjet(coffre.getLoot().get(i));
            }
            getEnv().getListeCoffres().remove(coffre);
            getEnv().getMapJeu().getTabMap()[y][x] = 59;
            coffre.detruire();
            System.out.println(this.getInventaire());
            return  true;
        }
        return false;
    }

    private boolean couper(int x, int y) {
        Arbre arbre = getEnv().getArbre(x,y);
        if (arbre != null) {
            arbre.frappe(armeProperty.getValue());
            return true;
        }
        return false;
    }

    private boolean miner(int x, int y) {
        Materiau minerai = getEnv().getMinerai(x,y);
        if (minerai != null) {
            minerai.frappe(armeProperty.getValue());
            return true;
        }
        return false;
    }
    public void decrementerPv(){
        if(getPv()>0) {
            setPv(getPv() - 10);
        }
    }

    public void augmenterPv(){
        if(getPv()<100) {
            setPv(getPv() + 10);
        }
    }

    public void seDeplacer() {
        int distance;
        if (tombe || saute)
            distance = 2;
        else
            distance = VITESSE;
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
        if (!freeze) {
            if (saute) sauter();
            if (avanceProperty.getValue()) seDeplacer();
        }
        if (!saute) tomber();

    }
    public boolean estMort(){
        if(this.getPv()==0){
            return true ;
        }
        return false;
    }

    @Override
    public void quandCollisionDetectee(Entite ent) {
        if (ent instanceof ObjetJeu || ent instanceof Materiau) {
            this.inventaire.ajouterObjet(ent);
        }
    }

    public void freezer() {
        freeze = !freeze;
    }

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
