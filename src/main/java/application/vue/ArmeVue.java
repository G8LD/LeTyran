package application.vue;

import application.controleur.listeners.ArmeListener;
import application.controleur.listeners.AttaqueListener;
import application.modele.Direction;
import application.modele.MapJeu;
import application.modele.armes.Hache;
import application.modele.armes.Lance;
import application.modele.armes.arc.Arc;
import application.modele.personnages.Ennemi;
import application.modele.personnages.Joueur;
import application.modele.personnages.Personnage;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static application.modele.MapJeu.TUILE_TAILLE;

public class ArmeVue {

    private Personnage perso;
    private ImageView spriteArme;
    private RotateTransition rt;
    private TranslateTransition tt;
    private int dir;
    private boolean rendreVisible;

    public ArmeVue(Personnage perso, ImageView spriteArme) {
        this.perso = perso;
        this.spriteArme = spriteArme;
        spriteArme.setVisible(false);
        spriteArme.setImage(ChargeurRessources.iconObjets.get(perso.getArme().getClass().getSimpleName() + perso.getArme().getQualite()));
        rt = new RotateTransition(Duration.millis(90), spriteArme);
        tt = new TranslateTransition(Duration.millis(150), spriteArme);
        initDirection(); initAnimation(); initTt();
        perso.getArmeProperty().addListener(new ArmeListener(this));
        rendreVisible = false;
    }

    public ArmeVue(Pane root, Personnage perso) {
        this.perso = perso;
        initSprite();
        rt = new RotateTransition(Duration.millis(90), spriteArme);
        tt = new TranslateTransition(Duration.millis(150), spriteArme);
        initDirection(); initAnimation();
        if (perso.getArme() instanceof Lance) initTt();
        root.getChildren().add(root.getChildren().size() - 2, spriteArme);
        ((Ennemi) perso).getAttaqueProperty().addListener(new AttaqueListener(this));
        rendreVisible = false;
    }

    //region INITIALISATION
    private void initSprite() {
        spriteArme = new ImageView();
        spriteArme.setVisible(false);
        spriteArme.setId(perso.getId() + "Arme");
        spriteArme.setFitWidth(28);
        spriteArme.setFitHeight(28);
        spriteArme.setImage(ChargeurRessources.iconObjets.get(perso.getArme().getClass().getSimpleName() + perso.getArme().getQualite()));
    }

    private void initDirection() {
        if (perso.getDirection() == Direction.Droit)
            dir = -1;
        else
            dir = 1;
    }

    public void initAnimation() {
        if (perso.getArme() instanceof Lance)
            rt.setByAngle(dir * 135);
        else if (perso.getArme() instanceof Arc)
            rt.setByAngle(dir * 233);
        else
            rt.setByAngle(dir * 50);
        rt.setOnFinished(actionEvent -> inverserSprite());
        rt.play();
    }

    private void initTt() {
        tt.setCycleCount(2);
        tt.setAutoReverse(true);
        tt.setOnFinished(actionEvent -> {
            spriteArme.setVisible(rendreVisible);
            rendreVisible = false;
            if (perso.getDirection() == Direction.Droit && dir == -1 || perso.getDirection() == Direction.Gauche && dir == 1)
                inverserSprite();
        });
    }
    //endregion

    public void animationFrappe() {
        if (rt.getCurrentRate() == 0 && tt.getCurrentRate() == 0) {
            if (perso instanceof Joueur) rendreVisible();
            if (perso.getArme() instanceof Lance) {
                tt.setByX(dir*TUILE_TAILLE);
                tt.play();
            } else {
                if (perso.getArme() instanceof Arc) {
                    rt.setDuration(Duration.millis(300));
                    rt.setByAngle(0);
                } else {
                    rt.setDuration(Duration.millis(150));
                    rt.setByAngle(dir * 90);
                    rt.setAutoReverse(true);
                    rt.setCycleCount(2);
                }
                rt.setOnFinished(actionEvent -> {
                    spriteArme.setVisible(rendreVisible);
                    rendreVisible = false;
                    if (perso.getDirection() == Direction.Droit && dir == -1 || perso.getDirection() == Direction.Gauche && dir == 1)
                        inverserSprite();
                });
                rt.play();
            }
        }
    }

    //inverse l'image selon la direction
    public void inverserSprite() {
        if (rt.getCurrentRate() == 0 && tt.getCurrentRate() == 0) {
            spriteArme.setScaleX(dir);
            dir = -dir;
            rt.setDuration(Duration.ONE);
            rt.setCycleCount(1);
            rt.setOnFinished(actionEvent1 -> {});
            if (perso.getArme() instanceof Lance) {
                rt.setByAngle(dir * -90);
            } else {
                rt.setByAngle(dir * 100);
            }
            rt.play();
        }
    }

    public void rendreVisible() {
        updatePositon();
        if (rt.getCurrentRate() == 0)
            spriteArme.setVisible(true);
        else
            rendreVisible = true;
    }

    public void updatePositon() {
        spriteArme.setTranslateX(perso.getX() + dir * 10);
        spriteArme.setTranslateY(perso.getY());
    }

    public ImageView getSpriteArme() {
        return spriteArme;
    }
}
