package application.controleur;

import application.modele.FeuDeCamp;
import application.vue.FeuDeCampVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FeuDeCampControleur {

    public FeuDeCampControleur(FeuDeCamp feuDeCamp, FeuDeCampVue feuDeCampVue) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, actionEvent -> feuDeCampVue.transition()));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), actionEvent -> feuDeCamp.seReposer()));
        feuDeCampVue.getSpriteFeuDeCamp().setOnMouseClicked(mouseEvent -> {
            timeline.play();
        });
    }
}
