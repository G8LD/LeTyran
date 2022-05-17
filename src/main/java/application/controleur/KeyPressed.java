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
                    jeu.getPersonnage().setSaute(true);
                    break;
                case Q: jeu.getPersonnage().setDirection(Direction.Gauche); jeu.getPersonnage().setAvance(true); break;
                case D: jeu.getPersonnage().setDirection(Direction.Droit); jeu.getPersonnage().setAvance(true); break;
                default: break;
            }
        }
    }
}
