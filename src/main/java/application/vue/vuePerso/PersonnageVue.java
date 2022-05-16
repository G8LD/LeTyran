package application.vue.vuePerso;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import static application.controleur.Controleur.TUILE_TAILLE;


public class PersonnageVue {

    private AnimationDeplacementJoueur animationDeplacementJoueur;
    private TranslateTransition tt;

    public PersonnageVue(StackPane spritesJoueur, AnimationDeplacementJoueur animationDeplacementJoueur) {
        this.animationDeplacementJoueur = animationDeplacementJoueur;
        tt = new TranslateTransition();
        tt.setNode(spritesJoueur);
    }

    public boolean pasAnimations() {
        return !animationDeplacementJoueur.isRunning() && tt.getCurrentRate() == 0;
    }

    //animation du saut
    //translate transion correspondant à la hauteur du saut
    //appelle la méthode tomber à la fin du translate
    public void animationSaut(int hauteurSaut) {
        tt.setByY(-TUILE_TAILLE * hauteurSaut);
        tt.setByX(0);
        tt.setDuration(Duration.millis(hauteurSaut * 100));
        tt.play();
    }

    public AnimationDeplacementJoueur getAnimationDeplacementJoueur() {
        return animationDeplacementJoueur;
    }
}
