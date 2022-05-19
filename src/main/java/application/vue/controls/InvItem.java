package application.vue.controls;

import application.modele.ObjetJeu;
import application.vue.InventaireVue;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class InvItem extends StackPane {
    private ColorInput color;
    private Label quantite;

    private int TAILLE_IMG_OBJET = 32;

    private ImageView imgVObjet;

    private ObjetJeu Objet;

    private InventaireVue invVue;

    //Permet de vérifier si on est entrain de porter un objet
    private boolean dragActive =false;


    public InvItem(InventaireVue invVue, ObjetJeu obj, InvSlot viewParent) {

        this.invVue = invVue;
        this.Objet = obj;
        this.imgVObjet = new ImageView(new Image("file:src/main/resources/application/inventaire/food/apple.png"));

        this.imgVObjet.setFitHeight(TAILLE_IMG_OBJET);
        this.imgVObjet.setFitWidth(TAILLE_IMG_OBJET);

        this.setLayoutX(8);
        this.setLayoutY(8);

        /*this.setLayoutX(this.viewParent.get() + 2);
        this.setLayoutY(this.viewParent.getY() + 2);*/

        color = new ColorInput();

        color.setWidth(TAILLE_IMG_OBJET);
        color.setHeight(TAILLE_IMG_OBJET);
        color.setPaint(Color.color(0.3,0.3,.3,0.1));

        this.setOnMouseEntered(mouseEvent -> {
            mouseEntered();
        });

        this.setOnMouseExited(mouseEvent -> {
            mouseExited();
        });

        this.setOnMousePressed(mouseEvent -> {
            this.invVue.definirObjetPrit(this);
            dragActive = true;
        });

        this.setOnMouseReleased(mouseEvent -> {
            dragActive = true;
            this.invVue.lacherObjet();
        });

        this.setOnMouseDragged(mouseEvent ->
        {
            if (dragActive) {
                Parent parent = this.getParent();
                /*System.out.println(" x : " + (mouseEvent.getSceneX() - this.getPrefWidth() / 2));
                System.out.println(" y : " + (mouseEvent.getSceneY() - this.getPrefHeight() / 2));*/
                this.setLayoutX(mouseEvent.getSceneX() - parent.getLayoutX() - this.getPrefWidth() / 2);
                this.setLayoutY(mouseEvent.getSceneY() - parent.getLayoutY() - this.getPrefHeight() / 2);
            }
        });

        quantite = new Label();
        quantite.setText(obj.toString());
        this.getChildren().add(this.imgVObjet);
        this.getChildren().add(quantite);


    }


    public ObjetJeu getObjet() {
        return this.Objet;
    }
    private void mouseEntered() {
        this.imgVObjet.setEffect(color);
    }

    private void mouseExited() {
        this.imgVObjet.setEffect(null);
    }
}
