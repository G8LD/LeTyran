package application.controleur.controls;

import application.controleur.InventaireControleur;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class InvSlot extends Pane {
    private ColorInput color;
    private StackPane stackPane;
    private Label quantite;

    private int TAILLE_IMG_OBJET = 32;

    private ImageView imgVObjet;
    private ImageView viewParent;

    private Integer Objet;

    InventaireControleur invControl;

    private boolean dragActive =false;

    public InvSlot(InventaireControleur invControl, Integer obj, ImageView viewParent) {

        this.invControl = invControl;
        this.Objet = obj;
        this.viewParent = viewParent;
        this.imgVObjet = new ImageView(new Image("file:src/main/resources/application/inventaire/food/apple.png"));
        this.stackPane = new StackPane();

        this.imgVObjet.setFitHeight(TAILLE_IMG_OBJET);
        this.imgVObjet.setFitWidth(TAILLE_IMG_OBJET);

        this.setLayoutX(this.viewParent.getX() + 2);
        this.setLayoutY(this.viewParent.getY() + 2);

        color = new ColorInput();

        color.setWidth(TAILLE_IMG_OBJET);
        color.setHeight(TAILLE_IMG_OBJET);
        color.setPaint(Color.color(0.3,0.3,.3,0.1));

        this.stackPane.setOnMouseEntered(mouseEvent -> {
            mouseEntered();
        });

        this.stackPane.setOnMouseExited(mouseEvent -> {
            mouseExited();
        });

        this.stackPane.setOnMousePressed(mouseEvent -> {
            invControl.definirObjetPrit(this);
            dragActive = true;
        });

        this.stackPane.setOnMouseReleased(mouseEvent -> {
            dragActive = true;
            invControl.lacherObjet();
        });

        this.stackPane.setOnMouseDragged(mouseEvent ->
        {
            if (dragActive) {
                /*System.out.println(" x : " + (mouseEvent.getSceneX() - this.getPrefWidth() / 2));
                System.out.println(" y : " + (mouseEvent.getSceneY() - this.getPrefHeight() / 2));*/
                this.setLayoutX(mouseEvent.getSceneX() - this.getPrefWidth() / 2);
                this.setLayoutY(mouseEvent.getSceneY() - this.getPrefHeight() / 2);
            }
        });

        quantite = new Label();
        quantite.setText(obj.toString());
        this.stackPane.getChildren().add(this.imgVObjet);
        this.stackPane.getChildren().add(quantite);

        this.getChildren().add(stackPane);

    }
    private void mouseEntered() {
        this.imgVObjet.setEffect(color);
    }

    private void mouseExited() {
        this.imgVObjet.setEffect(null);
    }
}
