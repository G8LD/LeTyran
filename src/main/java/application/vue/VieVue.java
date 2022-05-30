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

    // constructeur avec placement du Pane de la vie et la taille et le placment des images
    public VieVue(StackPane root){
        this.rootPane=root;
        this.viePane=new Pane();
        this.listeVie= new Image[11];
        this.image= new ImageView(new Image("file:src/main/resources/application/Vie/10.png"));
        this.viePane.setMaxWidth(200);// dimension du pane
        this.viePane.setMaxHeight(100);
        this.viePane.setLayoutX(300);// coordonné du Pane
        this.viePane.setLayoutY(-305);
        this.viePane.setVisible(true);
        image.setFitHeight(50);//dimension de l'image
        image.setFitWidth(120);
        image.setX(460);// coordonnne de l'image
        image.setY(-270);
        this.viePane.getChildren().add(this.image);
        this.rootPane.getChildren().add(this.viePane);
        this.afficherVie(50);

    }
    // fonction pour cree une image View qui contient toute les images de la vie
    public void listeImageVie(){
        for(int i = 0; i<this.listeVie.length; i++) {
            this.listeVie[i] = new Image("file:src/main/resources/application/Vie/" + i + ".png");
        }
    }

    // fonction qu affiche les differente image en fonction de la vie
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


    }




}
