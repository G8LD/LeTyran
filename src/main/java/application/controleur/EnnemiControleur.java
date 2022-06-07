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
        this.tileSol=tilesol;
        this.env=env;
        this.ennemie= ennemie;
        this.ennemieVue= new EnnemieVue(this.root, this.tileSol, this.ennemie);

        this.ennemieVue.getImage().setOnMouseClicked(mouseEvent ->{
            this.prendreLoot();
           this.ennemieVue.supprimerCadavre();

        });
         }

        public void prendreLoot(){
            Entite bois= new Bois(this.env,0,0);
            this.env.getPersonnage().getInventaire().ajouterObjet(bois);
            //On baisse le son de l'audio
            sound.setVolume(15. / 30.);
            sound.play();
            sound1.setVolume(15. / 30.);
            sound1.play();

        }



}