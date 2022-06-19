package application.controleur;

import application.modele.Direction;
import application.modele.Environnement;
import application.modele.armes.arc.Fleche;
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
                if (!env.getJoueur().getTombe())
                    env.getJoueur().setSaute(true);
                break;
            case Q:
                if (env.getJoueur().getDirection() != Direction.Gauche || !env.getJoueur().getAvance()) {
                    env.getJoueur().setDirection(Direction.Gauche);
                    env.getJoueur().setAvance(true);
                }
                break;
            case D:
                if (env.getJoueur().getDirection() != Direction.Droit || !env.getJoueur().getAvance()) {
                    env.getJoueur().setDirection(Direction.Droit);
                    env.getJoueur().setAvance(true);
                }
                break;
            case W:
                if (this.env.getJoueur().getPv()>0)
                    this.env.getJoueur().setPv(this.env.getJoueur().getPv()-10);
                break;
            case X:
                    if (this.env.getJoueur().getPv()<100)
                        this.env.getJoueur().setPv(this.env.getJoueur().getPv()+10);
                break;
            case R:
                this.env.getJoueur().getInventaire().ajouterObjet(new Fleche());
                break;
            default:
                break;
        }
    }
}
