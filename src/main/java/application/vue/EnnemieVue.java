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


    public EnnemieVue(Pane root, TilePane tilesol, Ennemie ennemie){
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
        if(!(this.ennemi.estMort())){

        }
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/mortChevalier.png"));
    }

    public void supprimerCadavre() {
        System.out.println("remove cadavre");
        this.rootPane.getChildren().remove(image);
    }

    public ImageView getImage(){
        return this.image;
    }

}
