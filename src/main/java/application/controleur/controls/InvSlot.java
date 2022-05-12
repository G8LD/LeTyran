package application.controleur.controls;

import javafx.fxml.FXML;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InvSlot extends Pane {
    private ColorInput color;

    public InvSlot(Image img, int size) {

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(size);
        imgView.setFitHeight(size);

        color = new ColorInput();

        color.setWidth(size);
        color.setHeight(size);
        color.setPaint(Color.color(0.3,0.3,.3,0.1));

        this.setOnMouseEntered(mouseEvent -> {
            mouseEntered();
        });

        this.setOnMouseExited(mouseEvent -> {
            mouseExited();
        });

        this.getChildren().add(imgView);
    }
    private void mouseEntered() {
        this.setEffect(color);
    }

    private void mouseExited() {
        this.setEffect(null);
    }
}
