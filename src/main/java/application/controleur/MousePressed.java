package application.controleur;

import application.modele.Environnement;
import application.modele.MapJeu;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Map;

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
        int x = (int)mouseEvent.getX() / TUILE_TAILLE;
        int y = (int)mouseEvent.getY() / TUILE_TAILLE;
        //int id;
        
        if(this.env.getMapJeu().getTabMap()[y][x] != 0
                && x <= env.getPersonnage().getX()/TUILE_TAILLE + 1 && x >= env.getPersonnage().getX()/TUILE_TAILLE - 1
                && y <= env.getPersonnage().getY()/TUILE_TAILLE + 1 && y >= env.getPersonnage().getY()/TUILE_TAILLE - 1) {
            env.getPersonnage().miner(x, y);
//            id = (y * MapJeu.WIDTH) + x;
//            this.env.getMapJeu().getTabMap()[y][x] = 0;
//            this.controleur.getMapVue().supprimerBloc(id);

            //System.out.println("x : " + x + " y " + y + " bloc numéro : " + id);
        }

    }
}