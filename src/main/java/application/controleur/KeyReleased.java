package application.controleur;

import application.modele.Direction;
import application.modele.Jeu;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
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
            case Q:
            case D: jeu.getPersonnage().setDirection(Direction.Immobile); break;
            default: break;
        }
    }
}
