package application.vue;

import application.modele.FeuDeCamp;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import static application.modele.MapJeu.*;


public class FeuDeCampVue {

    private FeuDeCamp feuDeCamp;
    private ImageView spriteFeuDeCamp;
    private Label labelMort;
    private FadeTransition fade;


    public FeuDeCampVue(FeuDeCamp feuDeCamp, ImageView spriteFeuDeCamp, Label labelMort) {
        this.feuDeCamp = feuDeCamp;
        this.spriteFeuDeCamp = spriteFeuDeCamp;
        this.labelMort = labelMort;

        spriteFeuDeCamp.setX(feuDeCamp.getX());
        spriteFeuDeCamp.setY(feuDeCamp.getY());

        labelMort.toBack();
        labelMort.setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);

        fade = new FadeTransition();
        fade.setNode(labelMort);
        fade.setDuration(Duration.seconds(2));
    }

    public void transition() {
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

    public ImageView getSpriteFeuDeCamp() {
        return spriteFeuDeCamp;
    }
}
