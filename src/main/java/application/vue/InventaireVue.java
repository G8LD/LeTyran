package application.vue;

import application.controleur.InventaireControleur;
import application.controleur.controls.InvSlot;
import application.modele.Inventaire;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InventaireVue {
    private Inventaire inv;

    private InvSlot objPrit;
    private boolean affiche = false;

    private Pane invPaneConteneur;

    private Image slotImg;

    public InventaireVue(Inventaire inv) {
        this.inv = inv;
        this.slotImg = new Image("file:src/main/resources/application/inventaire/slot.png");

    }
    public void afficherInventaire() {
        this.affiche = !this.affiche;
        int size = 64;
        if(affiche) {
            ColorInput color = new ColorInput();
            color.setPaint(Color.RED);

            this.invPaneConteneur = new Pane();

            for(int i = 0; i < 10; i++) {

                ImageView imgv = new ImageView(this.slotImg);
                imgv.setFitWidth(48);
                imgv.setFitHeight(48);
                imgv.setX(48 * i);
                this.invPaneConteneur.getChildren().add(imgv);

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

                    this.invPaneConteneur.getChildren().add(slot);
                }
            }

            root.getChildren().add(this.invPaneConteneur);
        }else {
            root.getChildren().remove(this.invPaneConteneur);
        }
    }

    public void definirObjetPrit(InvSlot obj) {
        this.objPrit = obj;
    }
}
