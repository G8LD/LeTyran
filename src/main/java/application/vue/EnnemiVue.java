package application.vue;

import application.modele.Ennemie;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class EnnemieVue extends PersonnageVue {

    private Pane root;
    private ImageView image;
    private Ennemie ennemi;


    public EnnemieVue(Pane root, Ennemie ennemi){
        super(root, ennemi);
        this.root = root;
        this.ennemi=ennemi;
        afficherCadavres();
        image.setLayoutX(500);
        image.setLayoutY(350);
        image.setFitWidth(50);
        image.setFitHeight(45);
        root.getChildren().add(image);


    }
    public void afficherCadavres(){
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/mortChevalier.png"));
    }

    public void supprimerCadavre() {
        this.root.getChildren().remove(image);
    }

    public ImageView getImage(){
        return this.image;
    }

}
