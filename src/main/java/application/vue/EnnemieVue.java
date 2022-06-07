package application.vue;

import application.modele.Ennemie;
import application.modele.Environnement;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class EnnemieVue {
    private Pane rootPane;
    private ImageView image;
    private TilePane tileSol;
    private Ennemie ennemi;
    private Environnement env;


    public EnnemieVue(Pane root, TilePane tilesol, Ennemie ennemie, Environnement env){
        this.rootPane=root;
        this.tileSol=tilesol;
        this.env=env;
        this.ennemi=new Ennemie(env);
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/mortChevalier.png"));
        rootPane.getChildren().add(image);
        image.setLayoutX(500);
        image.setLayoutY(350);
        image.setFitWidth(50);
        image.setFitHeight(45);
       // afficherCadavres();


    }
    public void afficherCadavres(){
        if(this.ennemi.estMort())
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/mortChevalier.png"));
    }

    public void supprimerCadavre(ImageView img) {
        img.setImage(new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
    }

    public ImageView getImage(){
        return this.image;
    }

}
