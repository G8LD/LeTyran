package application.vue;

import application.controleur.listeners.ArmeListener;
import application.modele.Direction;
import application.modele.MapJeu;
import application.modele.Personnage;
import application.vue.vueEnv.ChargeurRessources;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ArmeVue {

    private Personnage personnage;
    private ImageView spriteArme;
    private RotateTransition rt;

    public ArmeVue(Personnage personnage, ImageView spriteArme) {
        this.personnage = personnage;
        this.spriteArme = spriteArme;

        rt = new RotateTransition(Duration.millis(150), spriteArme);
        spriteArme.setImage(ChargeurRessources.iconObjets.get("Pioche1"));
        spriteArme.setVisible(false);
        personnage.getArmeProperty().addListener(new ArmeListener(this));
    }

    public static void initSprites() {
        ChargeurRessources.iconObjets.put("Hache1", new Image("file:src/main/resources/application/arme/sprite_hache1.png"));
        ChargeurRessources.iconObjets.put("Pioche1", new Image("file:src/main/resources/application/arme/sprite_pioche1.png"));
        ChargeurRessources.iconObjets.put("Epee1", new Image("file:src/main/resources/application/arme/sprite_epee1.png"));
        ChargeurRessources.iconObjets.put("Arc1", new Image("file:src/main/resources/application/arme/sprite_arc1.png"));
        ChargeurRessources.iconObjets.put("Lance1", new Image("file:src/main/resources/application/arme/sprite_lance1.png"));
        ChargeurRessources.iconObjets.put("Hache2", new Image("file:src/main/resources/application/arme/sprite_hache2.png"));
        ChargeurRessources.iconObjets.put("Pioche2", new Image("file:src/main/resources/application/arme/sprite_pioche2.png"));
        ChargeurRessources.iconObjets.put("Epee2", new Image("file:src/main/resources/application/arme/sprite_epee2.png"));
        ChargeurRessources.iconObjets.put("Arc2", new Image("file:src/main/resources/application/arme/sprite_arc2.png"));
        ChargeurRessources.iconObjets.put("Lance2", new Image("file:src/main/resources/application/arme/sprite_lance2.png"));
        ChargeurRessources.iconObjets.put("Hache3", new Image("file:src/main/resources/application/arme/sprite_hache3.png"));
        ChargeurRessources.iconObjets.put("Pioche3", new Image("file:src/main/resources/application/arme/sprite_pioche3.png"));
        ChargeurRessources.iconObjets.put("Epee3", new Image("file:src/main/resources/application/arme/sprite_epee3.png"));
        ChargeurRessources.iconObjets.put("Arc3", new Image("file:src/main/resources/application/arme/sprite_arc3.png"));
        ChargeurRessources.iconObjets.put("Lance3", new Image("file:src/main/resources/application/arme/sprite_lance3.png"));
    }

    public void animationFrappe() {
        if (rt.getCurrentRate() == 0) {
            spriteArme.setVisible(true);
            Boolean directionDroite = personnage.getDirection() == Direction.Droit;
            if (directionDroite) {
                spriteArme.setScaleX(-1);
                rt.setByAngle(90);
                spriteArme.setTranslateX((MapJeu.WIDTH / 2) * MapJeu.TUILE_TAILLE);
                spriteArme.setTranslateY(personnage.getY() - 10);
            } else {
                spriteArme.setScaleX(1);
                rt.setByAngle(-90);
                spriteArme.setTranslateX(((MapJeu.WIDTH / 2) * MapJeu.TUILE_TAILLE) - 20);
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
    public ImageView getSpriteArme() {
        return spriteArme;
    }
}
