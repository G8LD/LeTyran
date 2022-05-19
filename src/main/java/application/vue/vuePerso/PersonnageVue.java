package application.vue.vuePerso;

import application.modele.Direction;
import application.modele.Personnage;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private TranslateTransition tt;

    public PersonnageVue(Personnage personnage, StackPane spritesJoueur, Pane paneJoueur) {
        this.personnage = personnage;
        this.spritesJoueur = spritesJoueur;
        this.paneJoueur = paneJoueur;

        paneJoueur.setMaxSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
        tt = new TranslateTransition();
        tt.setNode(spritesJoueur);
        construirePerso(spritesJoueur);

        personnage.getXProperty().addListener(new DeplaceListener(personnage, this));
        personnage.getYProperty().addListener(new DeplaceListener(personnage, this));
        personnage.getAvanceProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (!t1) immobile();
            }
        });
    }

    //initialise les sprites du joueur_le met à la bonne position et met rend le bon sprite visible
    private void construirePerso(StackPane spritesJoueur) {
        for (int i = 0; i < spritesJoueur.getChildren().size(); i++)
            spritesJoueur.getChildren().get(i).setVisible(false);
        spritesJoueur.getChildren().get(0).setVisible(true);
        spritesJoueur.setTranslateX(personnage.getX() * TUILE_TAILLE);
        spritesJoueur.setTranslateY(personnage.getY() * TUILE_TAILLE);
    }

    public boolean pasAnimations() {
        return tt.getCurrentRate() == 0;
    }

    public void animationHorizontale() {
        int i = 0;
        while (!spritesJoueur.getChildren().get(i).isVisible()) i++;
        spritesJoueur.getChildren().get(i).setVisible(false);

        int idSprite;
        if (i == 1) idSprite = 2;
        else idSprite = 1;
        spritesJoueur.getChildren().get(idSprite).setVisible(true);

        tt.setByY(0);
        if (personnage.getDirection() == Direction.Droit) {
            spritesJoueur.getChildren().get(idSprite).setScaleX(1);
            tt.setByX(TUILE_TAILLE);
        } else {
            spritesJoueur.getChildren().get(idSprite).setScaleX(-1);
            tt.setByX(-TUILE_TAILLE);
        }
        tt.setDuration(Duration.millis(125));
        if (tt.getCurrentRate() == 0) {
            tt.play();
        } else {
            tt.setOnFinished(actionEvent -> {
                tt.setOnFinished(actionEvent1 -> {
                });
                tt.play();
            });
        }
    }

    //animation du saut
    //translate transion correspondant à la hauteur du saut
    public void animationSaut(int hauteurSaut) {
        System.out.println(hauteurSaut);
        tt.setByY(-TUILE_TAILLE * hauteurSaut);
        tt.setByX(0);
        tt.setDuration(Duration.millis(hauteurSaut * 100));
        tt.play();
    }

    //animation de la chute
    //translate transion correspondant à la hauteur de la chute
    public void animationChute(int hauteurChute) {
        tt.setByY(TUILE_TAILLE * hauteurChute);
        tt.setByX(0);
        tt.setDuration(Duration.millis(hauteurChute * 100));
        tt.play();
    }

    //met l'image du personnage immobile selon sa direction
    public void immobile() {
        for (int i = 0; i  < spritesJoueur.getChildren().size(); i++)
            spritesJoueur.getChildren().get(i).setVisible(false);
        spritesJoueur.getChildren().get(0).setVisible(true);

        switch (personnage.getDirection()) {
            case Gauche : spritesJoueur.getChildren().get(0).setScaleX(-1); break;
            case Droit : spritesJoueur.getChildren().get(0).setScaleX(1); break;
            default: break;
        }
    }
}
