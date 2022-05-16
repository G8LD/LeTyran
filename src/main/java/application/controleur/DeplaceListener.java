package application.controleur;

import application.modele.Direction;
import application.modele.Environnement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DeplaceListener implements ChangeListener {

    private Controleur controleur;
    private Environnement jeu;

    public DeplaceListener(Controleur controleur, Environnement jeu) {
        this.controleur = controleur;
        this.jeu = jeu;
    }

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        if (jeu.getPersonnage().getDirection() == Direction.Gauche || jeu.getPersonnage().getDirection() == Direction.Droit)
            controleur.getAnimationDeplacementJoueur().start();
        else if (jeu.getPersonnage().getDirection() == Direction.Haut)
            controleur.animationSaut((Integer) o - (Integer) t1);
        else
            controleur.animationChute((Integer) t1 - (Integer) o);
    }
}
