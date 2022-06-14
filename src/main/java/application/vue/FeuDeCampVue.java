package application.vue;

import application.modele.FeuDeCamp;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static application.modele.MapJeu.*;


public class FeuDeCampVue {

    private Label labelMort;
    private FadeTransition fade;


    public FeuDeCampVue(FeuDeCamp feuDeCamp, ImageView spriteFeuDeCamp, Label labelMort) {
        this.labelMort = labelMort;

        spriteFeuDeCamp.setX(feuDeCamp.getX() * TUILE_TAILLE);
        spriteFeuDeCamp.setY(feuDeCamp.getY() * TUILE_TAILLE);

        labelMort.toBack();
        labelMort.setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);

        fade = new FadeTransition();
        fade.setNode(labelMort);
        fade.setDuration(Duration.seconds(2));
    }

    //affiche un ecran noir pour la transition
    public void transition(boolean mort) {
        if (mort)
            labelMort.setText("VOUS ÊTES MORT");
        else
            labelMort.setText("");
        labelMort.toFront();
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setOnFinished(actionEvent -> {
            fade.setFromValue(1);
            fade.setToValue(1);
            fade.setOnFinished(actionEvent1 -> {
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(actionEvent2 -> labelMort.toBack());
                fade.play();
            });
            fade.play();
        });
        fade.play();
    }
}
