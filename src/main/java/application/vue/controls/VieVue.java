package application.vue.controls;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class VieVue  {

    private StackPane rootPane;

    private  Image[] listeVie;
    private ImageView image ;
    private Pane viePane;


    public VieVue(StackPane root){
        this.rootPane=root;
        this.viePane=new Pane();
        this.listeVie= new Image[11];
        this.image= new ImageView(new Image("file:src/main/resources/application/Vie/10.png"));
        this.rootPane.getChildren().add(this.viePane);
        this.viePane.setMaxWidth(15);
        this.viePane.setMaxHeight(10);
        this.viePane.setLayoutX(500);
        this.viePane.setLayoutY(100);
        this.viePane.setVisible(true);
        image.setFitHeight(50);
        image.setFitWidth(120);
        image.setX(512);
        image.setY(-312);
        this.afficherVie(50);

    }
    public void listeImageVie(){
        for(int i = 0; i<this.listeVie.length; i++) {
                this.listeVie[i] = new Image("file:src/main/resources/application/Vie/" + i + ".png");
        }
    }


    public void afficherVie(int pv ){
        this.listeImageVie();

        if  (pv==100){
            image.setImage(this.listeVie[10]);
        }
        else if (pv>=90){
            image.setImage(this.listeVie[9]);
            }
        else if (pv>=80){
            image.setImage(this.listeVie[8]);
        }
        else if (pv>=70){
            image.setImage(this.listeVie[7]);
        }
        else if (pv>=60){
            image.setImage(this.listeVie[6]);
        }
        else if (pv>=50){
            image.setImage(this.listeVie[5]);
        }
        else if (pv>=40){
            image.setImage(this.listeVie[4]);
        }
        else if (pv>=30){
            image.setImage(this.listeVie[3]);
        }
        else if (pv>=20){
            image.setImage(this.listeVie[2]);
        }
        else if (pv>=10){
            image.setImage(this.listeVie[1]);
        }
        else {
            image.setImage(this.listeVie[0]);
        }

        this.viePane.getChildren().add(this.image);

    }




}
