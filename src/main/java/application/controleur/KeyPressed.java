package application.controleur;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.armes.Pioche;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressed implements EventHandler<KeyEvent> {

    private Environnement env;

    public KeyPressed(Environnement jeu) {
        this.env = jeu;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case SPACE:
                if (!env.getPersonnage().getTombe())
                    env.getPersonnage().setSaute(true);
                break;
            case Q:
                if (env.getPersonnage().getDirection() != Direction.Gauche || !env.getPersonnage().getAvance()) {
                    env.getPersonnage().setDirection(Direction.Gauche);
                    env.getPersonnage().setAvance(true);
                }
                break;
            case D:
                if (env.getPersonnage().getDirection() != Direction.Droit || !env.getPersonnage().getAvance()) {
                    env.getPersonnage().setDirection(Direction.Droit);
                    env.getPersonnage().setAvance(true);
                }
                break;
            case W:
                this.env.getJoueur().setPv(this.env.getJoueur().getPv() - 10);
                this.env.getPersonnage().decrementerPv();
                break;
            case X:
                this.env.getJoueur().setPv(this.env.getJoueur().getPv() + 10);
                this.env.getPersonnage().augmenterPv();
                break;
            default:
                break;
        }
    }
}
