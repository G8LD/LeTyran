package application.vue;

import application.controleur.listeners.PersonnageListener;
import application.modele.Direction;
import application.modele.personnages.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

import static application.modele.MapJeu.TUILE_TAILLE;

public class PersonnageVue {

    private final static HashMap<String,ArrayList<Image>> LISTE_SPRITES = new HashMap<>() {{
        put("Joueur", new ArrayList<>() {{
            add(new Image("file:src/main/resources/application/perso/perso_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/perso_mouvement1.png"));
            add(new Image("file:src/main/resources/application/perso/perso_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/perso_mouvement2.png"));
        }});
        put("Ennemi", new ArrayList<>() {{
            add(new Image("file:src/main/resources/application/perso/perso_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/perso_mouvement1.png"));
            add(new Image("file:src/main/resources/application/perso/perso_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/perso_mouvement2.png"));
        }});
    }};

    private Personnage personnage;
    private ImageView spritePerso;
    private int indexSprite = 0;
    private long lastUpdate;

    public PersonnageVue(Personnage personnage, ImageView spritesJoueur) {
        this.personnage = personnage;
        this.spritePerso = spritesJoueur;
        lastUpdate = System.nanoTime();

        construirePerso();
    }

    public PersonnageVue(Pane root, Personnage personnage) {
        this.personnage = personnage;
        lastUpdate = System.nanoTime();
        creationSprite();
        construirePerso();
        root.getChildren().add(root.getChildren().size() - 3, spritePerso);
    }

    private void creationSprite() {
        spritePerso = new ImageView();
        spritePerso.setId(personnage.getId());
        spritePerso.setFitWidth(TUILE_TAILLE);
        spritePerso.setFitHeight(TUILE_TAILLE);
    }

    private void construirePerso() {
        spritePerso.setImage(LISTE_SPRITES.get(personnage.getClass().getSimpleName()).get(0));
        spritePerso.translateXProperty().bind(personnage.getXProperty());
        spritePerso.translateYProperty().bind(personnage.getYProperty());
        new PersonnageListener(personnage, this);
    }

    public void animerDeplacement() {
        long now = System.nanoTime();
        //modifie les sprites tout les 150 ms
        if (now - lastUpdate >= 150_000_000) {
            lastUpdate = now;
            //change le sprite du joueur
//            if (spritePerso.getImage() == LISTE_SPRITES.get(personnage.getClass().getSimpleName()).get(1))
//                spritePerso.setImage(LISTE_SPRITES.get(personnage.getClass().getSimpleName()).get(2));
//            else
//                spritePerso.setImage(LISTE_SPRITES.get(personnage.getClass().getSimpleName()).get(1));
            spritePerso.setImage(LISTE_SPRITES.get(personnage.getClass().getSimpleName()).get(indexSprite++));
            if (indexSprite == LISTE_SPRITES.get(personnage.getClass().getSimpleName()).size()) indexSprite = 0;
        }
    }

    //inverse l'image selon la direction
    public void inverserSprite() {
        if (personnage.getDirection() == Direction.Droit)
            spritePerso.setScaleX(1);
        else
            spritePerso.setScaleX(-1);
    }

    public void immobile(){
        spritePerso.setImage(LISTE_SPRITES.get(personnage.getClass().getSimpleName()).get(0));
    }
}
