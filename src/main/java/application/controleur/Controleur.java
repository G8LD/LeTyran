package application.controleur;

import application.modele.Environnement;
import application.vue.ArmeVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import application.vue.vueEnv.EnvironnementVue;
import application.vue.PersonnageVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement env;
    private KeyReleased keyReleased;
    private Timeline gameLoop;
    private ArmeVue armeVue;

    @FXML private Pane root;
    @FXML private TilePane tileSol;
    @FXML private TilePane tileDecors;
    @FXML private TilePane tileFond;
    @FXML private ImageView spriteJoueur;
    @FXML private ImageView spriteArme;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Environnement();
        keyReleased = new KeyReleased(this, env);

        new EnvironnementVue(env, tileSol, tileDecors, tileFond);
        new PersonnageVue(env.getPersonnage(), spriteJoueur);
        armeVue = new ArmeVue(env.getPersonnage(), spriteArme);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(this, env));
        root.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        root.addEventHandler(KeyEvent.KEY_PRESSED, new InventaireControleur(root, env));
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed(this, env));

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

    public ArmeVue getArmeVue() {
        return armeVue;
    }
}