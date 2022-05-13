package application.controleur;

import application.controleur.controls.InvSlot;
import application.modele.Inventaire;
import application.modele.Jeu;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

public class InventaireControleur implements EventHandler<KeyEvent> {

    private StackPane root;

    private boolean affiche;
    private Image slotImg;
    private Jeu jeu;
    private Inventaire inv;


    private InvSlot objPrit;
    private ImageView imgHovered = null;

    private Pane invPane;

    public InventaireControleur(StackPane root, Jeu jeu) {
        this.root = root;
        this.jeu = jeu;
        this.slotImg = new Image("file:src/main/resources/application/inventaire/slot.png");
        this.inv = this.jeu.getPersonnage().getInventaire();
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.I) {
            afficherInventaire();
        }
    }

    public void definirObjetPrit(InvSlot obj) {
        this.objPrit = obj;
    }

    public void lacherObjet() {
        if(this.invPane != null) {
            boolean found = false;
            float minDist = 0;
            ImageView selectedImg = null;
            int index = 0;

            while(!found && index < this.invPane.getChildren().size()) {
                if(this.invPane.getChildren().get(index) instanceof ImageView) {
                    ImageView img = (ImageView) this.invPane.getChildren().get(index);
                    float distanceX = (float) Math.abs(img.getX() - this.objPrit.getLayoutX());
                    float distanceY = (float) Math.abs(img.getY() - this.objPrit.getLayoutY());

                    float totalDist = distanceX + distanceY;
                    if(minDist == 0 || totalDist < minDist) {
                        selectedImg = img;
                        minDist = totalDist;
                    }
                }
                index++;
            }

            this.objPrit.setLayoutX(selectedImg.getX() + 8);
            this.objPrit.setLayoutY(selectedImg.getY() + 8);
            this.objPrit = null;
        }
    }

    public void afficherInventaire() {
        affiche = !affiche;
        int size = 64;
        if(affiche) {
            ColorInput color = new ColorInput();
            color.setPaint(Color.RED);

            this.invPane = new Pane();

            for(int i = 0; i < 10; i++) {

                ImageView imgv = new ImageView(this.slotImg);
                imgv.setFitWidth(48);
                imgv.setFitHeight(48);
                imgv.setX(48 * i);
                this.invPane.getChildren().add(imgv);

                imgv.setOnMouseEntered(mouseDragEvent -> {
                    System.out.println(this.objPrit);
                    if(this.objPrit != null) {
                        System.out.println("la souris entre");
                    }
                });

                if (i < inv.getObjets().size()) {
                    InvSlot slot = new InvSlot(this, inv.getObjets().get(i), imgv);
                    slot.setPrefWidth(32);
                    slot.setPrefHeight(32);

                    this.invPane.getChildren().add(slot);
                }
            }

            root.getChildren().add(this.invPane);
        }else {
            root.getChildren().remove(this.invPane);
        }
    }

}
