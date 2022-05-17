package application.controleur;

import application.modele.Direction;
import application.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressed implements EventHandler<KeyEvent> {

    private Controleur controleur;
    private Environnement jeu;

    public KeyPressed(Controleur controleur, Environnement jeu) {
        this.controleur = controleur;
        this.jeu = jeu;
    }

    @Override
    public void handle(KeyEvent event) {
        if (controleur.getPersonnageVue().pasAnimations()) {
            switch (event.getCode()) {
                case Z:
                    switch (jeu.getPersonnage().getDirection()) {
                        case Immobile: jeu.getPersonnage().setDirection(Direction.Haut); break;
                        case Gauche: jeu.getPersonnage().setDirection(Direction.HautGauche); break;
                        case Droit: jeu.getPersonnage().setDirection(Direction.HautDroit); break;
                    }
                    jeu.getPersonnage().sauter();
                    break;
                case Q: jeu.getPersonnage().setDirection(Direction.Gauche); break;
                case D: jeu.getPersonnage().setDirection(Direction.Droit); break;
                default: break;
            }
        }
    }
}
