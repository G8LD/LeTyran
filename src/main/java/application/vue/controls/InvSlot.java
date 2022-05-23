package application.vue.controls;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InvSlot extends Pane {
    private ImageView imgView;

    public InvSlot(Image img) {
        this.imgView = new ImageView();
        imgView.setImage(img);

        this.getChildren().add(imgView);
    }

    public void setSize(int x, int y) {
        this.imgView.setFitWidth(x);
        this.imgView.setFitHeight(y);

    }
}
