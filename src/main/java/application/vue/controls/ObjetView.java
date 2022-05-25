package application.vue.controls;

import application.modele.Entite;
import application.modele.ObjetJeu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObjetView extends ImageView {

    private Entite obj;

    public ObjetView(Entite obj) {
        this.obj = obj;
        this.layoutXProperty().bind(obj.getXProperty());
        this.layoutYProperty().bind(obj.getYProperty());

        this.setImage(new Image("file:src/main/resources/application/inventaire/food/bananas.png"));
    }


    public Entite getObjet() {
        return this.obj;
    }
}
