package application.vue;

import application.modele.Direction;
import application.modele.Personnage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import static application.modele.MapJeu.TUILE_TAILLE;


public class PersonnageVue {

    private Personnage personnage;
    private ImageView spriteJoueur;
    private ArrayList<Image> listeSprites;
    private long lastUpdate;

    public PersonnageVue(Personnage personnage, ImageView spritesJoueur) {
        this.personnage = personnage;
        this.spriteJoueur = spritesJoueur;
        listeSprites = new ArrayList<>();
        lastUpdate = System.nanoTime();

        initListeSprites();
        construirePerso();
    }

    private void initListeSprites() {
        listeSprites.add(new Image("file:src/main/resources/application/perso/perso_immobile.png"));
        listeSprites.add(new Image("file:src/main/resources/application/perso/perso_mouvement1.png"));
        listeSprites.add(new Image("file:src/main/resources/application/perso/perso_mouvement2.png"));
    }

    private void construirePerso() {
        spriteJoueur.setFitHeight(TUILE_TAILLE);
        spriteJoueur.setFitWidth(TUILE_TAILLE);
        spriteJoueur.setImage(listeSprites.get(0));

        spriteJoueur.translateXProperty().bind(personnage.getXProperty());
        spriteJoueur.translateYProperty().bind(personnage.getYProperty());

        personnage.getXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                animationDeplacement();
            }
        });
        personnage.getAvanceProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (!t1) immobile();
            }
        });
    }

    public void animationDeplacement() {
        long now = System.nanoTime();
        if (now - lastUpdate >= 150_000_000) {
            lastUpdate = now;

            if (spriteJoueur.getImage() == listeSprites.get(1))
                spriteJoueur.setImage(listeSprites.get(2));
            else
                spriteJoueur.setImage(listeSprites.get(1));

            if (personnage.getDirection() == Direction.Droit)
                spriteJoueur.setScaleX(1);
            else
                spriteJoueur.setScaleX(-1);
        }

    }


    //met l'image du personnage immobile selon sa direction
    public void immobile() {
        spriteJoueur.setImage(listeSprites.get(0));
    }
}
