package application.modele.objets;

import application.modele.Entite;
import application.modele.Environnement;
import application.modele.armes.Arme;
import application.modele.armes.Hache;

public class Arbre extends Entite {

    private final static int PV_MAX = 12;

    public Arbre(Environnement env, int x, int y) {
        super(env, x, y, PV_MAX);
    }
    //retourne le nombre de bois drop
    public void frappe(Arme arme) {
        int nbDegats;

        if (arme instanceof Hache) {
            nbDegats = arme.nbDegat();
        } else {
            nbDegats = 1;
        }

        int nbBois = 0;
        for (int i = 0; i < nbDegats; i++) {
            decrementerPV();
            if (getPv() % 4 == 0) nbBois++;
        }
        for (int i = 0; i < nbBois; i++)
            getEnv().getListeEntites().add(new Bois(getEnv(), (int)getX() * 32, (int)getY() * 32));
        if (getPv() <= 0) {
            detruire();
            System.out.println("arbre coupé");
        }
    }

    @Override
    public void detruire() {
        getEnv().getMapJeu().getTabMap()[(int) getY()][(int) getX()] = 0;
        getEnv().getListeArbres().remove(this);
    }
}
