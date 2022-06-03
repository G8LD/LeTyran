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
        if(mouseEvent.getX() <= env.getPersonnage().getX()+2*TUILE_TAILLE && mouseEvent.getX() >= env.getPersonnage().getX()-TUILE_TAILLE
                && mouseEvent.getY() <= env.getPersonnage().getY()+2*TUILE_TAILLE && mouseEvent.getY() >= env.getPersonnage().getY()-TUILE_TAILLE) {
            env.getPersonnage().interagit((int) mouseEvent.getX() / TUILE_TAILLE,(int) mouseEvent.getY() / TUILE_TAILLE);
            controleur.getArmeVue().animationFrappe();
        }
    }
}
