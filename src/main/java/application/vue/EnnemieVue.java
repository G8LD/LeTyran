package application.vue;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class EnnemieVue {
    private Pane rootPane;
    private ImageView image;

    public EnnemieVue(Pane root){
        this.rootPane=root;
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
    public ImageView getImage(){
        return this.image;
    }

}
