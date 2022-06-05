package application.modele.personnages;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.ObjetJeu;
import application.modele.armes.Pioche;
import application.modele.objets.Arbre;
import application.modele.objets.Materiau;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Joueur extends Personnage {

    private Inventaire inventaire;
    private boolean freeze;

    public Joueur(Environnement env) {
        super(env, new Pioche(env,1));
        this.inventaire = new Inventaire(super.getEnv());
        freeze = false;
    }

    public void interagit(int x, int y) {
        if (!frapper(x,y))
            if (!miner(x,y))
                couper(x,y);
    }

    private boolean frapper(int x, int y) {
        Ennemi ennemi = getEnv().getEnnemi(x,y);
        System.out.println(ennemi);
        if (ennemi != null) {
            getArme().frapper(ennemi);
            return true;
        }
        return false;
    }

    private boolean couper(int x, int y) {
        Arbre arbre = getEnv().getArbre(x,y);
        if (arbre != null) {
            arbre.estFrappe();
            getArme().decrementerPV();
            return true;
        }
        return false;
    }

    private boolean miner(int x, int y) {
        Materiau minerai = getEnv().getMinerai(x,y);
        if (minerai != null) {
            minerai.estFrappe();
            getArme().decrementerPV();
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
        if (!freeze) {
            if (getSaute()) sauter();
            if (getAvance()) seDeplacer();
        }
        if (!getSaute()) tomber();
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
}
