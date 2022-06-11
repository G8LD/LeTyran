package application.modele.personnages.ennemi;

import application.modele.Environnement;
import application.modele.armes.Epee;

public class Epeiste extends Ennemi {

    public Epeiste(Environnement env, int niveau, int x, int y, int distance) {
        super(env, x, y, distance);
        setPv(niveau * 20);
        setArme(new Epee(getEnv(), niveau));
    }

    protected void deplacement() {
        retourneDansZone();
        poursuiteJoueur();
        if (!getPoursuitJoueur())
                deplacementAllerRetour();
        seDeplacer();
    }
}
