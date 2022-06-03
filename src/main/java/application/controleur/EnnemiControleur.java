package application.controleur;

import application.modele.Ennemie;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.ObjetJeu;
import application.modele.objets.Bois;
import application.vue.EnnemieVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.ResourceBundle;

public class EnnemiControleur{

    private Pane root;
    private Environnement env;
    private TilePane tileSol;
    private Ennemie ennemie;


    private EnnemieVue ennemieVue;
    private AudioClip sound = new AudioClip(getClass().getResource("/application/sons/bruitLoorr.mp3").toExternalForm());
    private AudioClip sound1 = new AudioClip(getClass().getResource("/application/sons/zip.mp3").toExternalForm());

    public EnnemiControleur(Pane root, Environnement env, TilePane tilesol, Ennemie ennemie) {
        this.root = root;
        this.env = env;
        this.tileSol=tilesol;
        this.env=env;
        this.ennemie= ennemie;
        this.ennemieVue= new EnnemieVue(root, tilesol, ennemie, env);

        this.ennemieVue.getImage().setOnMouseClicked(mouseEvent ->{
            System.out.println("On passe");
            this.prendreLoot();
            this.ennemieVue.supprimerCadavre(this.ennemieVue.getImage());
            System.out.println("fini 3");

        });
         }

        public void prendreLoot(){
            Entite bois= new Bois(this.env,0,0);
            this.env.getPersonnage().getInventaire().ajouterObjet(bois);
            System.out.println(this.env.getPersonnage().getInventaire());//On baisse le son de l'audio
            sound.setVolume(15. / 30.);
            sound.play();
            sound1.setVolume(15. / 30.);
            sound1.play();

        }



}