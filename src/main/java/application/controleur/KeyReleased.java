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
            case Z:
                switch (jeu.getPersonnage().getDirection()) {
                    case Haut:
                        jeu.getPersonnage().setDirection(Direction.Immobile);
                        break;
                    case Gauche:
                        jeu.getPersonnage().setDirection(Direction.Gauche);
                        break;
                    case Droit:
                        jeu.getPersonnage().setDirection(Direction.Droit);
                        break;
                }
                break;
            case Q:
            case D:
                controleur.getPersonnageVue().immobile();
                jeu.getPersonnage().setDirection(Direction.Immobile);
                break;
            default:
                break;
        }
    }
}
