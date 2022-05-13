package application.controleur;

import application.controleur.controls.InvSlot;
import application.modele.Inventaire;
import application.modele.Jeu;
import application.vue.InventaireVue;
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


    private InventaireVue invVue;


    private ImageView imgHovered = null;

    private Pane invPane;

    public InventaireControleur(StackPane root, Jeu jeu) {
        this.root = root;
        this.jeu = jeu;

        this.inv = this.jeu.getPersonnage().getInventaire();

        this.invVue = new InventaireVue(inv);
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.I) {
            this.invVue.afficherInventaire();
        }
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
}
