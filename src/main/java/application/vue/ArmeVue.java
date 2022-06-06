package application.vue;

import application.controleur.listeners.ArmeListener;
import application.modele.Direction;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
import application.vue.vueEnv.ChargeurRessources;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static application.modele.MapJeu.TUILE_TAILLE;

public class ArmeVue {

    private Personnage perso;
    private ImageView spriteArme;
    private RotateTransition rt;
    private int dir;

    public ArmeVue(Pane root, Personnage perso) {
        this.perso = perso;
        initSprite(); initRt();
        if (perso instanceof Joueur)
            root.getChildren().add(spriteArme);
        else
            root.getChildren().add(root.getChildren().size() - 3, spriteArme);
        perso.getArmeProperty().addListener(new ArmeListener(this));
    }

    private void initSprite() {
        spriteArme = new ImageView();
        spriteArme.setVisible(false);
        spriteArme.setId(perso.getId() + "Arme");
        spriteArme.setFitWidth(28);
        spriteArme.setFitHeight(28);
        spriteArme.setImage(ChargeurRessources.iconObjets.get(perso.getArme().getClass().getSimpleName() + perso.getArme().getQualite()));
    }

    public void initRt() {
        rt = new RotateTransition(Duration.millis(90), spriteArme);
        if (perso.getDirection() == Direction.Droit)
            dir = 1;
        else
            dir = -1;
        spriteArme.setScaleX(-dir);
        rt.setByAngle(dir * 50);
        rt.play();
    }

    public void animationFrappe() {
        if (rt.getCurrentRate() == 0) {
            if (perso.getDirection() == Direction.Droit && dir == -1 || perso.getDirection() == Direction.Gauche && dir == 1)
                inverserSprite();
            else {
                spriteArme.setVisible(true);
                updatePositon();
                rt.setByAngle(dir * 90);
                rt.setOnFinished(actionEvent -> {
                    rt.setByAngle(dir * -90);
                    rt.setOnFinished(actionEvent1 -> {
                        spriteArme.setVisible(false);
                    });
                    rt.play();
                });
                rt.play();
            }
        }
    }

    //inverse l'image selon la direction
    public void inverserSprite() {
        if (rt.getCurrentRate() == 0) {
            rt.setDuration(Duration.ONE);
            dir = -dir;
            spriteArme.setScaleX(-dir);
            rt.setByAngle(dir * 100);
            rt.play();
            rt.setDuration(Duration.millis(150));
        }
    }

    public ImageView getSpriteArme() {
        return spriteArme;
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
        //TODO trouver et mettre les autres sprites
    }

    public void updatePositon() {
        spriteArme.setTranslateX(perso.getX() + dir * 10);
        spriteArme.setTranslateY(perso.getY());
    }
}
