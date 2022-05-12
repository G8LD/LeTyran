package application.controleur;

import application.controleur.controls.InvSlot;
import application.modele.Inventaire;
import application.modele.Jeu;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class InventaireControleur implements EventHandler<KeyEvent> {

    private StackPane root;
    private TilePane invPane;

    private boolean affiche;
    private Image slotImg;
    private Jeu jeu;
    private Inventaire inv;

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

    public void afficherInventaire() {
        affiche = !affiche;
        if(affiche) {
            invPane = new TilePane();
            for(int i = 1; i < 10; i++) {
                /*Integer objet = inv.getObjets().get(i);
                if(objet != null) {
                    System.out.println("ok");
                }*/
                Glow glow = new Glow();
                InvSlot img = new InvSlot(slotImg, 48);

                img.setPrefWidth(48);
                img.setPrefHeight(48);
                invPane.getChildren().add(img);
            }

            invPane.setAlignment(Pos.CENTER);
            root.getChildren().add(invPane);
        }else {
            root.getChildren().remove(invPane);
        }
    }

}
