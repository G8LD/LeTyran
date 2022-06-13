package application.vue.inventaire;

import application.modele.Entite;
import application.vue.ChargeurRessources;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObjetView extends ImageView {

    private Entite obj;

    public ObjetView(Entite obj) {
        this.obj = obj;
        this.layoutXProperty().bind(obj.getXProperty());
        this.layoutYProperty().bind(obj.getYProperty());

        Image img = ChargeurRessources.iconObjets.get(obj.getClass().getSimpleName());
        if(img == null) {
            img = new Image("file:src/main/resources/application/inventaire/food/bananas.png");
        }

        this.setTranslateX(-15);
        this.setFitHeight(32);
        this.setFitWidth(32);
        this.setOpacity(0.5);
        this.setImage(img);

    }


    public Entite getObjet() {
        return this.obj;
    }
}
