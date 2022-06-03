package application.controleur;

import application.controleur.events.DialogueListener;
import application.controleur.listeners.PersonnageListener;
import application.controleur.listeners.VieListener;
import application.modele.Environnement;
import application.modele.ModeleDialogue;
import application.vue.*;
import application.vue.vueEnv.EnvironnementVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement env;
    private KeyReleased keyReleased;
    private PersonnageVue personnageVue;
    private EnvironnementVue mapVue;
    private ArmeVue armeVue;
    private VieVue vievue;
    private VueDialogue vueDialog;

    private Timeline gameLoop;

    private ObjetVue objetVue;

    private ModeleDialogue modeleDialogue;

    @FXML private Pane root;
    @FXML private TilePane tileSol;
    @FXML private TilePane tileDecors;
    @FXML private TilePane tileFond;
    @FXML private Pane paneJoueur;
    @FXML private ImageView spriteJoueur;
    @FXML private ImageView spriteArme;
    @FXML private Text texteDialogue;
    @FXML private ScrollPane scrollDialogue;
    @FXML private TextFlow dialogFlow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Modele
        env = new Environnement();
        modeleDialogue = new ModeleDialogue();
        keyReleased = new KeyReleased(this, env);

        //Vues
        personnageVue = new PersonnageVue(env.getPersonnage(), spriteJoueur);
        mapVue = new EnvironnementVue(env, tileSol, tileDecors, tileFond);
        objetVue = new ObjetVue(this.env, this.root);
        armeVue = new ArmeVue(env.getPersonnage(), spriteArme);
        vievue = new VieVue(root);
        vueDialog = new VueDialogue(modeleDialogue, dialogFlow,  texteDialogue);

        //Event
        dialogFlow.addEventHandler(MouseEvent.MOUSE_CLICKED, new DialogueListener(modeleDialogue));
        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(this, env));
        root.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
        root.addEventHandler(KeyEvent.KEY_PRESSED, new InventaireControleur(root, env));
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, new DialogueControleur(vueDialog, modeleDialogue));


        this.env.getPersonnage().getPVProperty().addListener(new VieListener(vievue, this.env.getPersonnage()));

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
                    objetVue.update();
                    vueDialog.animer(0.017);
                    this.env.update();

                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public PersonnageVue getPersonnageVue() {
        return personnageVue;
    }

    public EnvironnementVue getMapVue() {
        return this.mapVue;
    }

    public ArmeVue getArmeVue() {
        return this.armeVue;
    }
}