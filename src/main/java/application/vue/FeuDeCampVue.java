package application.vue;

import application.modele.FeuDeCamp;
import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static application.modele.MapJeu.*;


public class FeuDeCampVue {

    private FeuDeCamp feuDeCamp;
    private ImageView spriteFeuDeCamp;
    private Pane paneT;
    private FadeTransition fade;


    public FeuDeCampVue(FeuDeCamp feuDeCamp, ImageView spriteFeuDeCamp, Pane paneT) {
        this.feuDeCamp = feuDeCamp;
        this.spriteFeuDeCamp = spriteFeuDeCamp;
        this.paneT = paneT;

        initSprite();
        initFt();
    }

    private void initFt() {
        paneT.toBack();
        paneT.setPrefSize(WIDTH * TUILE_TAILLE, HEIGHT * TUILE_TAILLE);
        fade = new FadeTransition();
        fade.setNode(paneT);
        fade.setDuration(Duration.seconds(2));
    }

    private void initSprite() {
        spriteFeuDeCamp.setX(feuDeCamp.getX());
        spriteFeuDeCamp.setY(feuDeCamp.getY());
        spriteFeuDeCamp.setFitWidth(TUILE_TAILLE);
        spriteFeuDeCamp.setFitHeight(TUILE_TAILLE);
    }

    public void transition() {
        paneT.toFront();
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setOnFinished(actionEvent -> {
            fade.setFromValue(1);
            fade.setToValue(1);
            fade.setOnFinished(actionEvent1 -> {
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.setOnFinished(actionEvent2 -> paneT.toBack());
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
