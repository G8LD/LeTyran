package application.controleur;


import application.modele.Environnement;
import application.modele.personnages.Ennemi;
import application.vue.EnnemiVue;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.media.AudioClip;

import static application.modele.MapJeu.TUILE_TAILLE;

public class EnnemiControleur{

    private Pane root;
    private Environnement env;
    private TilePane tileSol;
    private Ennemi ennemi;


    private EnnemiVue ennemieVue;
    private AudioClip sound = new AudioClip(getClass().getResource("/application/sons/bruitLoorr.mp3").toExternalForm());
    private AudioClip sound1 = new AudioClip(getClass().getResource("/application/sons/zip.mp3").toExternalForm());

    public EnnemiControleur(Pane root, Environnement env, TilePane tilesol, Ennemi ennemi, EnnemiVue ennemieVue) {
        this.root = root;
        this.tileSol=tilesol;
        this.env=env;
        this.ennemi= ennemi;
        this.ennemieVue= ennemieVue;


         }

        public void prendreLoot(){
            //On baisse le son de l'audio
            sound.setVolume(15. / 30.);
            sound.play();
            sound1.setVolume(15. / 30.);
            sound1.play();
            for (int i=0 ; i<this.ennemi.getLoot().size();i++) {
                this.env.getJoueur().getInventaire().ajouterObjet(this.ennemi.getLoot().get(i));
            }
        }



}