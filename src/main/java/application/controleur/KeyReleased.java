package application.controleur;

import application.modele.Direction;
import application.modele.Jeu;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyReleased implements EventHandler<KeyEvent> {

    private Controleur controleur;
    private Jeu jeu;

    public KeyReleased(Controleur controleur, Jeu jeu) {
        this.controleur = controleur;
        this.jeu = jeu;
    }

    @Override
    public void handle(KeyEvent event) {
        if (!controleur.getAnimationDeplacementJoueur().isRunning()) {
            switch (event.getCode()) {
                case Z:
                    switch (jeu.getPersonnage().getDirection()) {
                        case Haut: jeu.getPersonnage().setDirection(Direction.Immobile); break;
                        case Gauche: jeu.getPersonnage().setDirection(Direction.Gauche); break;
                        case Droit: jeu.getPersonnage().setDirection(Direction.Droit); break;
                    }
                    controleur.getAnimationDeplacementJoueur().immobile();
                case Q:
                case D:
                    jeu.getPersonnage().setDirection(Direction.Immobile);
                    controleur.getAnimationDeplacementJoueur().immobile();
                    break;
                default: break;
            }
        }
        System.out.println();
    }
}
