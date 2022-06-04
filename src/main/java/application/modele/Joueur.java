package application.modele;

import application.modele.objets.Arbre;
import application.modele.objets.Materiau;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Joueur extends Personnage {

    private final static int HAUTEUR_MAX = 2 * TUILE_TAILLE;
    private final static int VITESSE = 3;

    private Inventaire inventaire;

    public Joueur(Environnement env) {
        super(env);
        this.inventaire = new Inventaire(super.getEnv());
    }

    public void interagit(int x, int y) {
        if (!miner(x,y))
            couper(x,y);
    }

    private boolean couper(int x, int y) {
        Arbre arbre = getEnv().getArbre(x,y);
        if (arbre != null) {
            arbre.frappe(getArme());
            return true;
        }
        return false;
    }

    private boolean miner(int x, int y) {
        Materiau minerai = getEnv().getMinerai(x,y);
        if (minerai != null) {
            minerai.frappe(getArme());
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
    protected int getHauteurMax() {
        return HAUTEUR_MAX;
    }

    @Override
    protected int getVitesse() {
        return VITESSE;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }
}
