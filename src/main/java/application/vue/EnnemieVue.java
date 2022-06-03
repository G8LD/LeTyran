package application.vue;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class EnnemieVue {
    private Pane rootPane;
    private ImageView image;
    private TilePane tileSol;


    public EnnemieVue(Pane root, TilePane tilesol){
        this.rootPane=root;
        this.tileSol=tilesol;
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/cadavre.png"));
        rootPane.getChildren().add(image);
        image.setLayoutX(500);
        image.setLayoutY(362);
        image.setFitWidth(75);
        image.setFitHeight(25);
       // afficherCadavres();


    }
    public void afficherCadavres(){
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/cadavre.png"));
    }

    public void supprimerCadavre(ImageView img) {
        img.setImage(new Image("file:src/main/resources/application/pack1/tile_transparent.png"));
    }

    public ImageView getImage(){
        return this.image;
    }

}
