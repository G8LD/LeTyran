package application.controleur;

import application.modele.Environnement;
import application.modele.MapJeu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Map;

public class MousePressed implements EventHandler<MouseEvent> {
    private Controleur controleur;
    private Environnement env;

    public MousePressed(Controleur controleur, Environnement env) {
        this.controleur = controleur;
        this.env = env;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        int x = (int)mouseEvent.getX() / 32;
        int y = (int)mouseEvent.getY() / 32;
        int id = (y * MapJeu.WIDTH) + x;

        System.out.println("x : " + x + " y " + y + " bloc numéro : " + id);


        this.env.getMapJeu().getTabMap()[x][y] = 0;
        this.controleur.getMapVue().supprimerBloc(id);
    }
}
