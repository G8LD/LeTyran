package application.vue.controls;

import application.modele.ObjetJeu;
import application.modele.objets.Minerai;
import application.vue.InventaireVue;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class InvItem extends StackPane {
    private ColorInput color;
    private Label quantite;

    private int TAILLE_IMG_OBJET = 32;
    private Image[] imageInv;

    private ImageView imgVObjet;

    private ObjetJeu Objet;

    private InventaireVue invVue;


    //Permet de vérifier si on est entrain de porter un objet
    private boolean dragActive =false;


    public InvItem(InventaireVue invVue, ObjetJeu obj, InvSlot viewParent) {
        this.imageInv= new Image[5];
        this.invVue = invVue;
        this.Objet = obj;
        this.imgVObjet = new ImageView(new Image("file:src/main/resources/application/inventaire/food/apple.png"));


        this.imgVObjet.setFitHeight(TAILLE_IMG_OBJET);
        this.imgVObjet.setFitWidth(TAILLE_IMG_OBJET);

        this.setLayoutX(8);
        this.setLayoutY(8);

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

        this.setOnMouseReleased(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                dragActive = false;
                this.invVue.lacherObjet(this);
            }
        });

        this.setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton() == MouseButton.PRIMARY) {
                this.invVue.definirObjetPrit(this);
                dragActive = true;
            } else if (mouseEvent.getButton() == MouseButton.SECONDARY && !dragActive) {
                this.invVue.lacherObjetInventaire(this);
            }
        });

        this.setOnMouseDragged(mouseEvent ->
        {
            if (dragActive) {
                Parent parent = this.getParent();
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
