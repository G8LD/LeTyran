package application.vue;

import application.modele.Ennemi;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class EnnemiVue {
    private Pane rootPane;
    private ImageView image;
    private TilePane tileSol;
    private Ennemi ennemi;


    public EnnemiVue(Pane root, TilePane tilesol, Ennemi ennemie){
        this.rootPane=root;
        this.tileSol=tilesol;
        this.ennemi=ennemie;
        afficherCadavres();
        image.setLayoutX(500);
        image.setLayoutY(350);
        image.setFitWidth(50);
        image.setFitHeight(45);
        rootPane.getChildren().add(image);


    }
    public void afficherCadavres(){
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/mortChevalier.png"));
    }

    public void supprimerCadavre() {
        this.rootPane.getChildren().remove(image);
    }

    public ImageView getImage(){
        return this.image;
    }

}
