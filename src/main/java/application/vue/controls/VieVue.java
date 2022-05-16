package application.vue.controls;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class VieVue extends Pane implements EventHandler<T> {

    private StackPane rootPane;
    private Pane viePane;
    private ImageView image;

    public VieVue(StackPane root){
        this.rootPane=root;
        this.viePane=new Pane();
        this.viePane.setMaxWidth(15);
        this.viePane.setMaxHeight(10);
        this.viePane.setLayoutX(500);
        this.viePane.setLayoutY(100);
        this.viePane.setVisible(true);
        this.rootPane.getChildren().add(this.viePane);
    }
    public void afficherVie(int pv ){

        if(pv==100) {
            image = new ImageView(new Image("file:src/main/resources/application/Vie/100.png"));
        }
        else if (pv>=90){
                image = new ImageView(new Image("file:src/main/resources/application/Vie/90.png"));
            }
        else if (pv>=80){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/80.png"));
        }
        else if (pv>=70){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/70.png"));
        }
        else if (pv>=60){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/60.png"));
        }
        else if (pv>=50){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/50.png"));
        }
        else if (pv>=40){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/40.png"));
        }
        else if (pv>=30){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/30.png"));
        }
        else if (pv>=20){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/20.png"));
        }
        else if (pv>=10){
            image = new ImageView(new Image("file:src/main/resources/application/Vie/10.png"));
        }
        else {
            image = new ImageView(new Image("file:src/main/resources/application/Vie/0.png"));
        }
        image.setFitHeight(50);
        image.setFitWidth(120);
        image.setX(350);
        image.setY(-323);
        this.viePane.getChildren().add(image);
    }


}
