package application.controleur;

import application.modele.Environnement;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import static application.modele.MapJeu.TUILE_TAILLE;

public class MousePressed implements EventHandler<MouseEvent> {
    private Controleur controleur;
    private Environnement env;

    public MousePressed(Controleur controleur, Environnement env) {
        this.controleur = controleur;
        this.env = env;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getX() <= env.getJoueur().getX()+2*TUILE_TAILLE && mouseEvent.getX() >= env.getJoueur().getX()-TUILE_TAILLE
                && mouseEvent.getY() <= env.getJoueur().getY()+2*TUILE_TAILLE && mouseEvent.getY() >= env.getJoueur().getY()-TUILE_TAILLE) {
            env.getJoueur().interagit((int) mouseEvent.getX() / TUILE_TAILLE,(int) mouseEvent.getY() / TUILE_TAILLE);
            controleur.getArmeVue().animationFrappe();
        }
    }
}
