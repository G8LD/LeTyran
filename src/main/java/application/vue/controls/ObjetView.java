package application.vue.controls;

import application.modele.ObjetJeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObjetView extends ImageView {

    private ObjetJeu obj;

    public ObjetView(ObjetJeu obj) {
        this.obj = obj;
        this.layoutXProperty().bind(obj.getXProperty());
        this.layoutYProperty().bind(obj.getYProperty());

        this.setImage(new Image("file:src/main/resources/application/inventaire/food/bananas.png"));
    }

    public void rendre() {

    }

    public ObjetJeu getObjet() {
        return this.obj;
    }
}
