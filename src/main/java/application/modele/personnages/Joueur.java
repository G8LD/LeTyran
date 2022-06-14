package application.modele.personnages;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.ObjetJeu;
import application.modele.armes.Arme;
import application.modele.armes.Hache;
import application.modele.armes.Pioche;
import application.modele.armes.arc.Arc;
import application.modele.objets.Arbre;
import application.modele.objets.Bois;
import application.modele.objets.Coffre;
import application.modele.objets.Materiau;
import application.modele.personnages.ennemi.Ennemi;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.AudioClip;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Joueur extends Personnage {

    private Inventaire inventaire;
    private boolean freeze;
    private BooleanProperty mortProperty;
    private long delaiMort;
    private BooleanProperty avanceProperty;

    private AudioClip bruitCoffre = new AudioClip(getClass().getResource("/application/sons/coffreBruit.mp3").toExternalForm());

    public Joueur(Environnement env) {
        super(env);
        this.inventaire = new Inventaire(super.getEnv());
        this.inventaire.ajouterObjet(new Pioche(getEnv(), 1));
        this.inventaire.ajouterObjet(new Hache(getEnv(), 1));
        freeze = false;
        mortProperty = new SimpleBooleanProperty();
        delaiMort = 0;
        avanceProperty = new SimpleBooleanProperty(false);
    }

    public boolean interagit(int x, int y) {
        if(interactionEtabli(x, y) || (this.inventaire.getArme() != null && (frapper(x, y) || miner(x, y) || couper(x, y) || ouvrirCoffre(x, y))))
                return true;
        return false;
    }

    private boolean interactionEtabli(int x, int y) {
        if (x == getEnv().getEtabli().getX() && y == getEnv().getEtabli().getY()) {
            if (!getEnv().getEtabli().getOuvert())
                getEnv().getEtabli().ouvrir();
            else
                getEnv().getEtabli().fermer();
            return true;
        }
        return false;
    }

    private boolean ouvrirCoffre(int x, int y){
        Coffre coffre = getEnv().getCoffre(x, y);
        Entite bois= new Bois(getEnv(), x, y);
        if(coffre != null){
            //On baisse le son de l'audio
            bruitCoffre.setVolume(5. / 30.);
            bruitCoffre.play();
            this.getInventaire().ajouterObjet(bois);
            getEnv().getListeCoffres().remove(coffre);
            getEnv().getMapJeu().getTabMap()[y][x] = 59;
            coffre.detruire();
            System.out.println(this.getInventaire());
            return  true;
        }
        return false;
    }

    private boolean frapper(int x, int y) {
        if (getArme() instanceof Arc) {
            getArme().frapper(this, null);
            return true;
        } else {
            Ennemi ennemi = getEnv().getEnnemi(x, y);
            if (ennemi != null) {
                getArme().frapper(this, ennemi);
                return true;
            }
            return false;
        }
    }

    private boolean couper(int x, int y) {
        Arbre arbre = getEnv().getArbre(x, y);
        if (arbre != null) {
            arbre.estFrappe();
            return true;
        }
        return false;
    }

    private boolean miner(int x, int y) {
        Materiau minerai = getEnv().getMinerai(x, y);
        if (minerai != null) {
            minerai.estFrappe();
            return true;
        }
        return false;
    }

    @Override
    public void quandCollisionDetectee(Entite ent) {
        if (ent instanceof ObjetJeu || ent instanceof Materiau) {
            this.inventaire.ajouterObjet(ent);
        }
    }

    @Override
    public void update() {
        super.collide();
        if (mortProperty.getValue())
            detruire();
        else if (getDistancePoussee() != 0)
            estPoussee();
        else {
            if (!freeze) {
                if (getSaute()) sauter();
                if (getAvance()) seDeplacer();
            }
            if (!getSaute()) tomber();
        }
    }

    @Override
    public void detruire() {
        if (!mortProperty.getValue()) {
            mortProperty.setValue(true);
            delaiMort = System.currentTimeMillis();
        } else if (System.currentTimeMillis() - delaiMort >= 5_000) {
            mortProperty.setValue(false);
        } else if (System.currentTimeMillis() - delaiMort >= 2_000 && getX() != getEnv().getFeuDeCamp().getX()) {
            setSaute(false); setAvance(false); setDistancePoussee(0);
            setX(getEnv().getFeuDeCamp().getX());
            setY(getEnv().getFeuDeCamp().getY());
            getEnv().getFeuDeCamp().seReposer();
        }
    }

    public void freezer() {
        freeze = !freeze;
    }

    @Override
    protected int getHauteurMax() {
        return 2 * TUILE_TAILLE;
    }

    @Override
    protected int getVitesse() {
        return 3;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public final boolean getMort() {
        return mortProperty.getValue();
    }

    public final BooleanProperty getMortProperty() {
        return mortProperty;
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

    @Override
    public Arme getArme() {
        return inventaire.getArme();
    }
}
