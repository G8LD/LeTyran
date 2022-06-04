package application.controleur;

import application.controleur.listeners.VieListener;
import application.modele.Environnement;
import application.vue.ArmeVue;
import application.vue.EtabliVue;
import application.vue.ObjetVue;
import application.vue.PersonnageVue;
import application.vue.VieVue;
import application.vue.vueEnv.EnvironnementVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement env;
    private PersonnageVue personnageVue;
    private EnvironnementVue mapVue;
    private ArmeVue armeVue;
    private VieVue vievue;
    private ObjetVue objetVue;
    private EtabliVue etabliVue;

    private Timeline gameLoop;

    @FXML private Pane root;
    @FXML private TilePane tileSol;
    @FXML private TilePane tileDecors;
    @FXML private TilePane tileFond;
    @FXML private ImageView spriteJoueur;
    @FXML private ImageView spriteArme;
    @FXML private BorderPane bPaneEtabli;
    @FXML private ImageView spriteEtabli;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Environnement();

        personnageVue = new PersonnageVue(env.getJoueur(), spriteJoueur);
        mapVue = new EnvironnementVue(env, tileSol, tileDecors, tileFond);
        objetVue = new ObjetVue(this.env, this.root);
        armeVue = new ArmeVue(env.getJoueur(), spriteArme);
        vievue = new VieVue(root);
        etabliVue =new EtabliVue(env.getEtabli(), spriteEtabli, bPaneEtabli, armeVue);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(env));
        root.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleased(this, env));
        root.addEventHandler(KeyEvent.KEY_PRESSED, new InventaireControleur(root, env));
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed(this, env));
        this.env.getJoueur().getPVProperty().addListener(new VieListener(vievue, this.env.getJoueur()));
        new EtabliControleur(root,env, etabliVue);
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
                    env.getJoueur().update();
                    objetVue.update();
                    this.env.update();

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public ArmeVue getArmeVue() {
        return armeVue;
    }
}