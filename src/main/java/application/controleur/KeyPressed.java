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
        System.out.println(event.getCode());
        switch (event.getCode()) {
            case SPACE:
                if (!jeu.getPersonnage().getTombe())
                    jeu.getPersonnage().setSaute(true);
                break;
            case Q:
                if (jeu.getPersonnage().getDirection() != Direction.Gauche || !jeu.getPersonnage().getAvance()) {
                    jeu.getPersonnage().setDirection(Direction.Gauche);
                    jeu.getPersonnage().setAvance(true);
                }
                break;
            case D:
                if (jeu.getPersonnage().getDirection() != Direction.Droit || !jeu.getPersonnage().getAvance()) {
                    jeu.getPersonnage().setDirection(Direction.Droit);
                    jeu.getPersonnage().setAvance(true);
                }
                break;
            default:
                break;
        }
    }
}
