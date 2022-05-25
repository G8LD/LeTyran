package application.vue;

import application.modele.Direction;
import application.modele.Personnage;
import application.modele.armes.Arme;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class ArmeVue {

    private Personnage personnage;
    private Arme arme;
    private ArrayList<Image> listeSprites;
    private ImageView spriteArme;
    private RotateTransition rt;

    public ArmeVue(Personnage personnage, ImageView spriteArme) {
        this.personnage = personnage;
        this.spriteArme = spriteArme;
        this.arme = personnage.getArme();

        listeSprites = new ArrayList<>();
        initListeSprites();

        rt = new RotateTransition(Duration.millis(200), spriteArme);
        rt.setOnFinished(actionEvent -> spriteArme.setVisible(false));

        spriteArme.setImage(listeSprites.get(0));
        spriteArme.setVisible(false);
    }

    private void initListeSprites() {
        listeSprites.add(new Image("file:src/main/resources/application/arme/sprite_hache.png"));
    }

    public void animationFrappe() {
        spriteArme.setVisible(true);

        if (personnage.getDirection() == Direction.Droit) {
            spriteArme.setScaleX(1);
            rt.setByAngle(360);
            spriteArme.setTranslateX(personnage.getX());
            spriteArme.setTranslateY(personnage.getY());
        }
        else {
            spriteArme.setScaleX(-1);
            rt.setByAngle(-360);
            spriteArme.setTranslateX(personnage.getX() - 20);
            spriteArme.setTranslateY(personnage.getY());
        }
        rt.play();
    }
}
