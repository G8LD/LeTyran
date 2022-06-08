package application.controleur;

import application.controleur.listeners.PersonnageListener;
import application.controleur.listeners.VieListener;
import application.modele.Ennemie;
import application.modele.Environnement;
import application.vue.*;
import application.vue.vueEnv.EnvironnementVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
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
    private EnnemieVue ennemieVue;
    private EnnemiControleur ennemiControleur;
    private  Ennemie ennemie;

    private Timeline gameLoop;

    @FXML private Pane root;
    @FXML private TilePane tileSol;
    @FXML private TilePane tileDecors;
    @FXML private TilePane tileFond;
    @FXML private ImageView spriteJoueur;
    @FXML private ImageView spriteArme;
    @FXML private BorderPane bPaneEtabli;
    @FXML private ImageView spriteEtabli;

    @FXML private Pane inventaireMain;
    @FXML private Pane inventaireSac;
    @FXML private Pane inventaireEquipement;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Environnement();

        personnageVue = new PersonnageVue(env.getPersonnage(), spriteJoueur);
        mapVue = new EnvironnementVue(env, tileSol, tileDecors, tileFond);
        objetVue = new ObjetVue(this.env, this.root);
        armeVue = new ArmeVue(env.getPersonnage(), spriteArme);
        vievue = new VieVue(root);
        etabliVue =new EtabliVue(env.getEtabli(), spriteEtabli, bPaneEtabli, armeVue);

        this.ennemie= new Ennemie(env, 500, 350);
        this.ennemieVue= new EnnemieVue(root,tileSol,ennemie);
        this.ennemiControleur= new EnnemiControleur(root,env, tileSol,ennemie,this.ennemieVue);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(env));
        root.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleased(this, env));
        root.addEventHandler(Event.ANY, new InventaireControleur(root, env, inventaireMain, inventaireSac, inventaireEquipement));
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed(this, env));
        this.env.getPersonnage().getPVProperty().addListener(new VieListener(vievue, this.env.getPersonnage()));
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
                    env.getPersonnage().update();
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