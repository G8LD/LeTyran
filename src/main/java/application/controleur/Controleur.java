package application.controleur;


import application.controleur.listeners.PersonnageListeners;
import application.controleur.listeners.VieListener;

import application.modele.Environnement;
import application.modele.armes.Epee;
import application.modele.personnages.Ennemi;
import application.modele.personnages.Joueur;
import application.vue.ArmeVue;
import application.vue.EtabliVue;
import application.vue.ObjetVue;
import application.vue.PersonnageVue;
import application.vue.VieVue;
import application.vue.EnvironnementVue;
import application.modele.ModeleDialogue;
import application.vue.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Controleur implements Initializable {

    private Environnement env;
    private PersonnageVue personnageVue;
    private EnvironnementVue mapVue;
    private ArmeVue armeVue;
    private VieVue vievue;
    private ObjetVue objetVue;
    private EtabliVue etabliVue;
    private EnnemiVue ennemieVue;
    private EnnemiControleur ennemiControleur;
    private Ennemi ennemi;

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

    @FXML private Pane inventaireMain;
    @FXML private Pane inventaireSac;
    @FXML private Pane inventaireEquipement;

    @FXML private Text texteDialogue;
    @FXML private TextFlow dialogFlow;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        env = new Environnement();
        modeleDialogue = new ModeleDialogue();


        personnageVue = new PersonnageVue(env.getJoueur(),spriteJoueur);
        mapVue = new EnvironnementVue(env,root, tileSol, tileDecors, tileFond);
        objetVue = new ObjetVue(this.env, this.root);
        armeVue = new ArmeVue(env.getJoueur(), spriteArme);
        vievue = new VieVue(root);
        etabliVue =new EtabliVue(env.getEtabli(), spriteEtabli, bPaneEtabli);
        vueDialog = new VueDialogue(modeleDialogue, dialogFlow,  texteDialogue);

        this.ennemi = new Ennemi(env,new Epee(env , 2), 500, 350,1);
        this.ennemieVue= new EnnemiVue(env,root, ennemi);
        this.ennemiControleur= new EnnemiControleur(root,env, tileSol, ennemi,this.ennemieVue);

        root.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressed(env));
        root.addEventHandler(KeyEvent.KEY_RELEASED, new KeyReleased(this, env));
        root.addEventHandler(Event.ANY, new InventaireControleur(root, env, inventaireMain, inventaireSac, inventaireEquipement));
        root.addEventHandler(MouseEvent.MOUSE_PRESSED, new MousePressed(this, env));
        root.addEventHandler(Event.ANY, new DialogueControleur(vueDialog, modeleDialogue));

        this.env.getJoueur().getPVProperty().addListener(new VieListener(vievue, this.env.getJoueur()));
        this.ennemi.getPVProperty().addListener((old,obs,newP)->{
            if(this.ennemi.estMort()){
                System.out.println("je passe");
                this.env.getListeEnnemis().remove(this.ennemi);
                this.ennemieVue.afficherCadavres((int) this.ennemi.getOrigineX(), (int) this.ennemi.getOrigineY());
                this.ennemieVue.getImage().setOnMouseClicked(mouseEvent ->{
                    if((this.ennemi.getX() <= this.env.getJoueur().getX()+2*TUILE_TAILLE && this.ennemi.getX() >= this.env.getJoueur().getX()-TUILE_TAILLE && this.ennemi.getY() <= this.env.getJoueur().getY()+2*TUILE_TAILLE && this.ennemi.getY() >= this.env.getJoueur().getY()-TUILE_TAILLE)) {
                        this.ennemiControleur.prendreLoot();
                        this.ennemieVue.supprimerCadavre();
                        System.out.println("ok");
                    }
                    System.out.println("ok2");
                });
            }
        });
        new EtabliControleur(root,env, etabliVue);
        new PersonnageListeners(env.getJoueur(), personnageVue, armeVue);

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