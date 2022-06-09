package application.controleur;

import application.modele.Environnement;
import application.modele.Inventaire;
import application.modele.armes.Arme;
import application.vue.PersonnageVue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import static application.modele.MapJeu.TUILE_TAILLE;
import static application.modele.MapJeu.WIDTH;

public class MousePressed implements EventHandler<MouseEvent> {
    private Controleur controleur;
    private Environnement env;

    public MousePressed(Controleur controleur, Environnement env) {
        this.controleur = controleur;
        this.env = env;
    }
    @Override
    public void handle(MouseEvent mouseEvent) {



        float persoPosX = env.getPersonnage().getX() / TUILE_TAILLE;
        float persoPosY = env.getPersonnage().getY();


        int mouseX = ((int)mouseEvent.getX()/TUILE_TAILLE) - (WIDTH / 2) + (int)persoPosX;
        int mouseY = (int)mouseEvent.getY();


        if(mouseX > 0) {
            if (mouseX <= persoPosX + 2 && mouseX >= persoPosX - 2
                    && mouseY <= persoPosY + 2 * TUILE_TAILLE && mouseY >= persoPosY - TUILE_TAILLE) {

                if(env.getPersonnage().interagit(mouseX, mouseY / TUILE_TAILLE)) {
                    controleur.getArmeVue().animationFrappe();

                    Inventaire inv = env.getPersonnage().getInventaire();
                    Arme arme = inv.getArme();
                    //TODO améliorer
                    controleur.getArmeVue().mettreAJourImage(""+ arme.getClass().getSimpleName() + arme.getQualite());
                }
            }
        }
    }
}
