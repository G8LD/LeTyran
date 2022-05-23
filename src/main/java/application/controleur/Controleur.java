package application.controleur;

import application.modele.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import application.vue.vueMap.MapVue;
import application.vue.vuePerso.PersonnageVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

import static application.modele.MapJeu.*;
import static application.modele.MapJeu.TUILE_TAILLE;

public class Controleur implements Initializable {

    private Environnement env;
    private KeyReleased keyReleased;
    private PersonnageVue personnageVue;
    private MapVue mapVue;
    private Timeline gameLoop;

    @FXML
    private Pane root;
    @FXML
    private TilePane tileSol;
    @FXML
    private TilePane tileDecors;
    @FXML
    private StackPane spritesJoueur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //root.setMaxSize(10000,10000);
        root.setBackground(Background.fill(Color.RED));
        System.out.println(WIDTH * TUILE_TAILLE + " "+ HEIGHT * TUILE_TAILLE);
        env = new Environnement();
        keyReleased = new KeyReleased(this, env);
        mapVue = new MapVue(env.getMapJeu().getTabMap(), tileSol, tileDecors);
        personnageVue = new PersonnageVue(env.getPersonnage(), spritesJoueur);


        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(this, env));
        root.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        root.addEventHandler(KeyEvent.KEY_PRESSED, new InventaireControleur(root, env));

        initAnimation();
        gameLoop.play();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                (ev ->{
                    env.getPersonnage().update();

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public PersonnageVue getPersonnageVue() {
        return personnageVue;
    }
}
