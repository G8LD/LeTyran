package application.controleur;

import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.armes.Arme;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import static application.modele.MapJeu.*;

public class MousePressed implements EventHandler<MouseEvent> {
    private Controleur controleur;
    private Environnement env;

    public MousePressed(Controleur controleur, Environnement env) {
        this.controleur = controleur;
        this.env = env;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {
        
        float persoPosX = env.getJoueur().getX() / TUILE_TAILLE;
        float persoPosY = env.getJoueur().getY() / TUILE_TAILLE;

        int mouseX = ((int)mouseEvent.getX()/TUILE_TAILLE) - (WIDTH / 2) + (int)persoPosX;
        int mouseY = ((int)mouseEvent.getY() / TUILE_TAILLE) - (HEIGHT /2) + (int)persoPosY;

        if(mouseX >= 0) {
            if (mouseX <= persoPosX + 2 && mouseX >= persoPosX - 2
                    && mouseY <= persoPosY +2 && mouseY >= persoPosY - 2) {

                if(env.getJoueur().interagit(mouseX, mouseY)) {
                    controleur.getArmeVue().animationFrappe();
                } else {
                    env.getJoueur().poserBlock(mouseX, mouseY);
                }
            }
        }
    }
}
