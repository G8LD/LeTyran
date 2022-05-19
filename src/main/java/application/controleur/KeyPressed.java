package application.controleur;

import application.modele.Direction;
import application.modele.Environnement;
import application.vue.controls.VieVue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyPressed implements EventHandler<KeyEvent> {

    private Controleur controleur;
    private Environnement jeu;
    private VieVue vie ;

    public KeyPressed(Controleur controleur, Environnement jeu, VieVue vie) {
        this.controleur = controleur;
        this.jeu = jeu;
        this.vie=vie;
    }

    @Override
    public void handle(KeyEvent event) {
        if (controleur.pasAnimations()) {
            switch (event.getCode()) {
                case Z:
                    switch (jeu.getPersonnage().getDirection()) {
                        case Immobile:
                            jeu.getPersonnage().setDirection(Direction.Haut);
                            break;
                        case Gauche:
                            jeu.getPersonnage().setDirection(Direction.HautGauche);
                            break;
                        case Droit:
                            jeu.getPersonnage().setDirection(Direction.HautDroit);
                            break;
                    }
                    jeu.getPersonnage().sauter();
                    break;
                case Q:
                    jeu.getPersonnage().setDirection(Direction.Gauche);
                    jeu.getPersonnage().seDeplacer();
                    break;
                case D:
                    jeu.getPersonnage().setDirection(Direction.Droit);
                    jeu.getPersonnage().seDeplacer();
                    break;
                case G:
                    jeu.getPersonnage().perdrePV();
                    vie.clearPane();
                    controleur.getVie().afficherVie(jeu.getPersonnage().getPv());
                    break;
                case H:
                    jeu.getPersonnage().prendrePV();
                    vie.clearPane();
                    controleur.getVie().afficherVie(jeu.getPersonnage().getPv());
                    break;
                default:
                    break;
            }
        }
    }
}
