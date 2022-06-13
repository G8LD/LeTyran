package application.vue;

import application.modele.Direction;
import application.modele.personnages.Personnage;
import application.modele.MapJeu;
import application.modele.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

import static application.modele.MapJeu.TUILE_TAILLE;

public class PersonnageVue {

    private final static HashMap<String,ArrayList<Image>> LISTE_SPRITES = new HashMap<>() {{
        put("Joueur", new ArrayList<>() {{
            add(new Image("file:src/main/resources/application/perso/joueur/perso_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/joueur/perso_mouvement1.png"));
            add(new Image("file:src/main/resources/application/perso/joueur/perso_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/joueur/perso_mouvement2.png"));
        }});
        put("Epeiste", new ArrayList<>() {{
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_mouvement1.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_mouvement2.png"));
        }});
        put("Lancier", new ArrayList<>() {{
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_mouvement1.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_mouvement2.png"));
        }});
        put("Archer", new ArrayList<>() {{
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_mouvement1.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_immobile.png"));
            add(new Image("file:src/main/resources/application/perso/ennemi/ennemi_mouvement2.png"));
        }});
        put("Lapin", new ArrayList<>() {{
            add(new Image("file:src/main/resources/application/perso/lapin/lapin_1.png"));
            add(new Image("file:src/main/resources/application/perso/lapin/lapin_2.png"));
            add(new Image("file:src/main/resources/application/perso/lapin/lapin_3.png"));
        }});
    }};

    private Personnage perso;
    private ImageView spritePerso;
    private int indexSprite;
    private long lastUpdate;

    public final static int POSITION_VUE_PERSONNAGE = (MapJeu.WIDTH * MapJeu.TUILE_TAILLE) / 2;

    public PersonnageVue(Personnage perso, ImageView spritesJoueur) {
        this.perso = perso;
        this.spritePerso = spritesJoueur;
        lastUpdate = System.currentTimeMillis();
        indexSprite = 0;
        construirePerso();
    }
    public PersonnageVue(Pane root, Personnage perso) {
        this.perso = perso;
        lastUpdate = System.currentTimeMillis();
        creationSprite();
        root.getChildren().add(root.getChildren().size() - 2, spritePerso);
    }

    private void creationSprite() {
        spritePerso = new ImageView();
        spritePerso.setId(perso.getId());
        spritePerso.setFitWidth(TUILE_TAILLE);
        spritePerso.setFitHeight(TUILE_TAILLE);
    }

    private void construirePerso() {
        spritePerso.setImage(LISTE_SPRITES.get(perso.getClass().getSimpleName()).get(0));
        spritePerso.translateXProperty().bind(perso.getXProperty());
        spritePerso.translateYProperty().bind(perso.getYProperty());
//        spriteJoueur.setImage(listeSprites.get(0));
//        spriteJoueur.setTranslateX((MapJeu.WIDTH * MapJeu.TUILE_TAILLE) / 2);
//        spriteJoueur.setTranslateY((MapJeu.HEIGHT * MapJeu.TUILE_TAILLE) / 2);
//        new PersonnageListener(personnage, this);
    }

    public void animerDeplacement() {
        long now = System.currentTimeMillis();
        //modifie les sprites tout les 200 ms
        if (now - lastUpdate >= 200) {
            lastUpdate = now;
            spritePerso.setImage(LISTE_SPRITES.get(perso.getClass().getSimpleName()).get(indexSprite++));
            if (indexSprite == LISTE_SPRITES.get(perso.getClass().getSimpleName()).size()) indexSprite = 0;
        }
    }

    //inverse l'image selon la direction
    public void inverserSprite() {
        if (perso.getDirection() == Direction.Droit)
            spritePerso.setScaleX(1);
        else
            spritePerso.setScaleX(-1);
    }

    public void immobile() {
        spritePerso.setImage(LISTE_SPRITES.get(perso.getClass().getSimpleName()).get(0));
    }
}
