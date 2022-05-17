package application.controleur;

import application.modele.Direction;
import application.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyReleased implements EventHandler<KeyEvent> {

    private Controleur controleur;
    private Environnement jeu;

    public KeyReleased(Controleur controleur, Environnement jeu) {
        this.controleur = controleur;
        this.jeu = jeu;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case Q:
            case D:
                jeu.getPersonnage().setAvance(false);
                break;
            default:
                break;
        }
    }
}
