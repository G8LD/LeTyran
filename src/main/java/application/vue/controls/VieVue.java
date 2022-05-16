package application.vue.controls;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class VieVue extends Pane {

    private StackPane rootPane;
    private Pane viePane;
    private ImageView image;

    public VieVue(StackPane root){
        this.rootPane=root;
        this.viePane=new Pane();
        this.viePane.setMaxWidth(100);
        this.viePane.setMaxHeight(15);
        this.viePane.setLayoutX(500);
        this.viePane.setLayoutY(100);
        this.viePane.setVisible(true);
        this.rootPane.getChildren().add(this.viePane);
    }
    public void afficherVie(int pv ){

        if(pv>80) {
            image = new ImageView(new Image("file:src/main/resources/application/Vie/full_hp.png"));
        }
        else if (pv>50){
                image = new ImageView(new Image("file:src/main/resources/application/Vie/presque_full_hp.png"));
            }
        else if( pv==50){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/mid_hp.png"));
        }
        else if( pv>30){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/low_hp.png"));
        }
        else {
            image = new ImageView(new Image("file:src/main/resources/application/Vie/tres_low_hp.png"));
        }
        image.setFitHeight(100);
        image.setFitWidth(200);
        this.viePane.getChildren().add(image);
    }

}
