package application.vue.vuePerso;

import application.modele.Personnage;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import static application.controleur.Controleur.TUILE_TAILLE;
import static application.modele.MapJeu.HEIGHT;
import static application.modele.MapJeu.WIDTH;


public class PersonnageVue {

    private Personnage personnage;
    private StackPane spritesJoueur;
    private Pane paneJoueur;
    private AnimationDeplacementJoueur animationDeplacementJoueur;
    private TranslateTransition tt;

    public PersonnageVue(Personnage personnage, StackPane spritesJoueur, Pane paneJoueur, AnimationDeplacementJoueur animationDeplacementJoueur) {
        this.personnage = personnage;
        this.spritesJoueur = spritesJoueur;
        this.paneJoueur = paneJoueur;
        this.animationDeplacementJoueur = animationDeplacementJoueur;

        paneJoueur.setMaxSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
        tt = new TranslateTransition();
        tt.setNode(spritesJoueur);
        construirePerso(spritesJoueur);

        personnage.getXProperty().addListener(new DeplaceListener(personnage, this));
        personnage.getYProperty().addListener(new DeplaceListener(personnage, this));
    }

    //initialise les sprites du joueur_le met à la bonne position et met rend le bon sprite visible
    private void construirePerso(StackPane spritesJoueur) {
        for (int i = 0; i < spritesJoueur.getChildren().size(); i++)
            spritesJoueur.getChildren().get(i).setVisible(false);
        spritesJoueur.getChildren().get(3).setVisible(true);
        spritesJoueur.setTranslateX(personnage.getX() * TUILE_TAILLE);
        spritesJoueur.setTranslateY(personnage.getY() * TUILE_TAILLE);
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
