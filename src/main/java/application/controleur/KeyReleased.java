package application.controleur;

import application.modele.Direction;
import application.modele.Jeu;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class KeyReleased implements EventHandler<KeyEvent> {

    private Controleur controleur;
    private Jeu jeu;
    private KeyCode toucheLachee;

    public KeyReleased(Controleur controleur, Jeu jeu) {
        this.controleur = controleur;
        this.jeu = jeu;
        toucheLachee = null;
    }

    @Override
    public void handle(KeyEvent event) {
        toucheLachee = event.getCode();
        System.out.println(toucheLachee);
        if (controleur.getPersonnageVue().pasAnimations())
            gestionToucheLachee();
    }

    public void gestionToucheLachee() {
        if (toucheLachee != null) {
            System.out.println("gestionToucheLachee appelé");
            switch (toucheLachee) {
                case Z:
                    switch (jeu.getPersonnage().getDirection()) {
                        case Haut: jeu.getPersonnage().setDirection(Direction.Immobile); break;
                        case Gauche: jeu.getPersonnage().setDirection(Direction.Gauche); break;
                        case Droit: jeu.getPersonnage().setDirection(Direction.Droit); break;
                    }
                    break;
                case Q:
                case D:
                    controleur.getPersonnageVue().immobile();
                    jeu.getPersonnage().setDirection(Direction.Immobile);
                    break;
                default: break;
            }
            toucheLachee = null;
        }
    }
}
