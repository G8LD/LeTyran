package application.vue;

import application.modele.Direction;
import application.modele.personnages.Personnage;
import application.modele.personnages.ennemi.Boss;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

import static application.modele.MapJeu.*;

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
        put("Boss", new ArrayList<>() {{
           // add(new Image("file:src/main/resources/application/Boss/dodo.png"));
            add(new Image("file:src/main/resources/application/Boss/debout1.png"));
            add(new Image("file:src/main/resources/application/Boss/debout2.png"));
            //add(new Image("file:src/main/resources/application/Boss/marche.png"));
           // add(new Image("file:src/main/resources/application/Boss/debout3.png"));
            add(new Image("file:src/main/resources/application/Boss/immobile.png"));
        }});
    }};

    private Personnage perso;
    private ImageView spritePerso;
    private int indexSprite = 0;
    private long lastUpdate;

    public PersonnageVue(Personnage perso, ImageView spritesJoueur) {
        this.perso = perso;
        this.spritePerso = spritesJoueur;
        lastUpdate = System.currentTimeMillis();
        construirePerso();
    }

    public PersonnageVue(Pane root, Personnage perso) {
        this.perso = perso;
        lastUpdate = System.currentTimeMillis();
        creationSprite();
        construirePerso();
        root.getChildren().add(root.getChildren().size() - 2, spritePerso);

    }

    private void creationSprite() {
        spritePerso = new ImageView();
        spritePerso.setId(perso.getId());
        spritePerso.setFitWidth(TUILE_TAILLE);
        spritePerso.setFitHeight(TUILE_TAILLE);
      /* if(perso.getClass().getSimpleName().equals("Boss")){
            spritePerso.setFitWidth(TUILE_TAILLE*3);
            spritePerso.setFitHeight(TUILE_TAILLE*2);
        }*/
    }

    private void construirePerso() {
        spritePerso.setImage(LISTE_SPRITES.get(perso.getClass().getSimpleName()).get(0));
        spritePerso.translateXProperty().bind(perso.getXProperty());
        spritePerso.translateYProperty().bind(perso.getYProperty());
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
    public void animationAttaqueBoss(){
        if(perso instanceof Boss){
            spritePerso.setImage(new Image("file:src/main/resources/application/Boss/attaque2.png"));
        }
    }

    public void immobile() {
        spritePerso.setImage(LISTE_SPRITES.get(perso.getClass().getSimpleName()).get(0));
    }
}
