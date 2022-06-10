package application.controleur;

import application.controleur.listeners.PersonnageListener;
import application.controleur.listeners.VieListener;
import application.modele.Ennemie;
import application.modele.Environnement;
import application.modele.MapJeu;
import application.modele.ModeleDialogue;
import application.vue.*;
import application.vue.vueEnv.EnvironnementVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    private Environnement env;
    private PersonnageVue personnageVue;
    private EnvironnementVue mapVue;
    private ArmeVue armeVue;
    private VieVue vievue;
    private ObjetVue objetVue;
    private EtabliVue etabliVue;
    private EnnemieVue ennemieVue;
    private EnnemiControleur ennemiControleur;
    private  Ennemie ennemie;

    private VueDialogue vueDialog;

    private ModeleDialogue modeleDialogue;

    private Timeline gameLoop;

    @FXML private Pane root;
    @FXML private TilePane tileSol;
    @FXML private TilePane tileDecors;
    @FXML private TilePane tileFond;
    @FXML private ImageView spriteJoueur;
    @FXML private ImageView spriteArme;
    @FXML private BorderPane bPaneEtabli;
    @FXML private ImageView spriteEtabli;

    @FXML private Pane paneDecors;

    @FXML private Pane inventaireMain;
    @FXML private Pane inventaireSac;
    @FXML private Pane inventaireEquipement;

    @FXML private Text texteDialogue;
    @FXML private TextFlow dialogFlow;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Environnement();
        modeleDialogue = new ModeleDialogue();


        personnageVue = new PersonnageVue(env.getPersonnage(), spriteJoueur);
        mapVue = new EnvironnementVue(env, tileSol, tileDecors, tileFond);
        objetVue = new ObjetVue(this.env, this.paneDecors);
        armeVue = new ArmeVue(env.getPersonnage(), spriteArme);
        vievue = new VieVue(root);
        etabliVue =new EtabliVue(env.getEtabli(), spriteEtabli, bPaneEtabli, armeVue);
        vueDialog = new VueDialogue(modeleDialogue, dialogFlow,  texteDialogue);

        this.ennemie= new Ennemie(env, 500, 350);
        this.ennemieVue= new EnnemieVue(root,paneDecors,ennemie);
        this.ennemiControleur= new EnnemiControleur(root,env, tileSol,ennemie,this.ennemieVue);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(env));
        root.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleased(this, env));
        root.addEventHandler(Event.ANY, new InventaireControleur(root, env, inventaireMain, inventaireSac, inventaireEquipement));
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed(this, env));
        root.addEventHandler(Event.ANY, new DialogueControleur(vueDialog, modeleDialogue));
        this.env.getPersonnage().getPVProperty().addListener(new VieListener(vievue, this.env.getPersonnage()));

        tileSol.translateXProperty().bind(env.getPersonnage().getXProperty().multiply(-1).add(((MapJeu.TUILE_TAILLE * MapJeu.WIDTH)) / 2));
        paneDecors.translateXProperty().bind(env.getPersonnage().getXProperty().multiply(-1).add(((MapJeu.TUILE_TAILLE * MapJeu.WIDTH)) /2));
        tileSol.translateYProperty().bind(env.getPersonnage().getYProperty().multiply(-1).add(((MapJeu.TUILE_TAILLE * MapJeu.HEIGHT)) / 2));
        paneDecors.translateYProperty().bind(env.getPersonnage().getYProperty().multiply(-1).add(((MapJeu.TUILE_TAILLE * MapJeu.HEIGHT)) / 2));

        new EtabliControleur(paneDecors,env, etabliVue);

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

    public ArmeVue getArmeVue() {
        return armeVue;
    }
}