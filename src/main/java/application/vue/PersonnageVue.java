package application.vue;

import application.controleur.listeners.PersonnageListener;
import application.modele.Direction;
import application.modele.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class PersonnageVue {

    private Personnage personnage;
    private ImageView spriteJoueur;
    private ArrayList<Image> listeSprites;
    private long lastUpdate;

    public PersonnageVue(Personnage personnage, ImageView spritesJoueur) {
        this.personnage = personnage;
        this.spriteJoueur = spritesJoueur;
        lastUpdate = System.nanoTime();

        initListeSprites();
        construirePerso();
    }

    private void initListeSprites() {
        listeSprites = new ArrayList<>();
        listeSprites.add(new Image("file:src/main/resources/application/perso/perso_immobile.png"));
        listeSprites.add(new Image("file:src/main/resources/application/perso/perso_mouvement1.png"));
        listeSprites.add(new Image("file:src/main/resources/application/perso/perso_mouvement2.png"));
    }

    private void construirePerso() {
        spriteJoueur.setImage(listeSprites.get(0));
        spriteJoueur.translateXProperty().bind(personnage.getXProperty());
        spriteJoueur.translateYProperty().bind(personnage.getYProperty());
        new PersonnageListener(personnage, this);
    }

    public void animationDeplacement() {
        long now = System.nanoTime();
        //modifie les sprites tout les 150 ms
        if (now - lastUpdate >= 150_000_000) {
            lastUpdate = now;
            //change le sprite du joueur
            if (spriteJoueur.getImage() == listeSprites.get(1))
                spriteJoueur.setImage(listeSprites.get(2));
            else
                spriteJoueur.setImage(listeSprites.get(1));
        }
    }

    public void inversionSprite() {
        //inverse l'image selon la direction
        if (personnage.getDirection() == Direction.Droit)
            spriteJoueur.setScaleX(1);
        else
            spriteJoueur.setScaleX(-1);
    }

    public void immobile(){
        spriteJoueur.setImage(listeSprites.get(0));
    }
}
