package application.vue;

import application.controleur.listeners.ArmeListener;
import application.modele.Direction;
import application.modele.Personnage;
import application.modele.armes.Arme;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;

public class ArmeVue {

    private Personnage personnage;
    private HashMap<String, Image> listeSprites;
    private ImageView spriteArme;
    private RotateTransition rt;

    public ArmeVue(Personnage personnage, ImageView spriteArme) {
        this.personnage = personnage;
        this.spriteArme = spriteArme;

        listeSprites = new HashMap<>();
        initListeSprites();

        rt = new RotateTransition(Duration.millis(150), spriteArme);
        spriteArme.setImage(listeSprites.get("Pioche1"));
        spriteArme.setVisible(false);
        personnage.getArmeProperty().addListener(new ArmeListener(this));
    }

    private void initListeSprites() {
        listeSprites.put("Hache1", new Image("file:src/main/resources/application/arme/sprite_hache1.png"));
        listeSprites.put("Pioche1", new Image("file:src/main/resources/application/arme/sprite_pioche1.png"));
    }

    public void animationFrappe() {
        if (rt.getCurrentRate() == 0) {
            spriteArme.setVisible(true);
            Boolean directionDroite = personnage.getDirection() == Direction.Droit;
            if (directionDroite) {
                spriteArme.setScaleX(-1);
                rt.setByAngle(90);
                spriteArme.setTranslateX(personnage.getX());
                spriteArme.setTranslateY(personnage.getY() - 10);
            } else {
                spriteArme.setScaleX(1);
                rt.setByAngle(-90);
                spriteArme.setTranslateX(personnage.getX() - 20);
                spriteArme.setTranslateY(personnage.getY() - 10);
            }
            rt.setOnFinished(actionEvent -> {
                spriteArme.setVisible(false);
                rt.setDuration(Duration.ONE);
                if (directionDroite) {
                    rt.setByAngle(270);
                } else {
                    rt.setByAngle(-270);
                }
                rt.setOnFinished(actionEvent1 -> {});
                rt.play();
                rt.setDuration(Duration.millis(150));
            });
            rt.play();
        }
    }

    public HashMap<String, Image> getListeSprites() {
        return listeSprites;
    }

    public ImageView getSpriteArme() {
        return spriteArme;
    }
}