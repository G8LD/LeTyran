package application.controleur;

import application.modele.Direction;
import application.modele.Jeu;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class DeplaceListener implements ChangeListener {

    private Jeu jeu;
    private AnimationDeplacementJoueur animationDeplacementJoueur;

    public DeplaceListener(Jeu jeu, AnimationDeplacementJoueur animationDeplacementJoueur) {
        this.jeu = jeu;
        this.animationDeplacementJoueur = animationDeplacementJoueur;
    }

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {
        if (jeu.getPersonnage().getDirection() == Direction.Gauche || jeu.getPersonnage().getDirection() == Direction.Droit)
            animationDeplacementJoueur.start();
    }
}
