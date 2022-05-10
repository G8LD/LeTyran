package application.controleur;

import application.modele.Direction;
import application.modele.Jeu;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyReleased implements EventHandler<KeyEvent> {

    private Jeu jeu;

    public KeyReleased(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case Z:
                switch (jeu.getPersonnage().getDirection()) {
                    case Haut: jeu.getPersonnage().setDirection(Direction.Immobile); break;
                    case Gauche: jeu.getPersonnage().setDirection(Direction.Gauche); break;
                    case Droit: jeu.getPersonnage().setDirection(Direction.Droit); break;
                }
            case Q:
            case D: jeu.getPersonnage().setDirection(Direction.Immobile); break;
            default: break;
        }
    }
}
