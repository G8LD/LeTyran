package application.controleur;

import application.modele.Environnement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import application.vue.vueMap.MapVue;
import application.vue.vuePerso.PersonnageVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    public final static int TUILE_TAILLE = 32;

    private Environnement env;
    private KeyReleased keyReleased;
    private PersonnageVue personnageVue;
    private MapVue mapVue;
    private Timeline gameLoop;

    @FXML
    private StackPane root;
    @FXML
    private TilePane tileSol;
    @FXML
    private TilePane tileDecors;
    @FXML
    private Pane paneJoueur;
    @FXML
    private StackPane spritesJoueur;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Environnement();
        keyReleased = new KeyReleased(this, env);
        personnageVue = new PersonnageVue(env.getPersonnage(), spritesJoueur, paneJoueur);
        mapVue = new MapVue(env.getMapJeu().getTabMap(), tileSol, tileDecors);

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
                    if (personnageVue.pasAnimations())
                        env.getPersonnage().seDeplacer();

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public PersonnageVue getPersonnageVue() {
        return personnageVue;
    }
}
