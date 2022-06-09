package application.controleur;

import application.modele.Ennemi;
import application.modele.Entite;
import application.modele.Environnement;
import application.modele.objets.Bois;
import application.vue.EnnemiVue;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

import static application.modele.MapJeu.TUILE_TAILLE;

public class EnnemiControleur{

    private Pane root;
    private Environnement env;
    private TilePane tileSol;
    private Ennemi ennemie;



    private EnnemiVue ennemieVue;
    private AudioClip sound = new AudioClip(getClass().getResource("/application/sons/bruitLoorr.mp3").toExternalForm());
    private AudioClip sound1 = new AudioClip(getClass().getResource("/application/sons/zip.mp3").toExternalForm());

    public EnnemiControleur(Pane root, Environnement env, TilePane tilesol, Ennemi ennemie, EnnemiVue ennemieVue) {
        this.root = root;
        this.tileSol=tilesol;
        this.env=env;
        this.ennemie= ennemie;
        this.ennemieVue= ennemieVue;

        this.ennemieVue.getImage().setOnMouseClicked(mouseEvent ->{
            if((this.ennemie.getX() <= this.env.getPersonnage().getX()+2*TUILE_TAILLE && this.ennemie.getX() >= this.env.getPersonnage().getX()-TUILE_TAILLE && this.ennemie.getY() <= this.env.getPersonnage().getY()+2*TUILE_TAILLE && this.ennemie.getY() >= this.env.getPersonnage().getY()-TUILE_TAILLE)) {
                this.prendreLoot();
                this.ennemieVue.supprimerCadavre();
            }
        });
         }

        public void prendreLoot(){
            //On baisse le son de l'audio
            sound.setVolume(15. / 30.);
            sound.play();
            sound1.setVolume(15. / 30.);
            sound1.play();
            for (int i=0 ; i<this.ennemie.getLoot().size();i++) {
                this.env.getPersonnage().getInventaire().ajouterObjet(this.ennemie.getLoot().get(i));
            }
        }



}