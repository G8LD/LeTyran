package application.vue;

import application.modele.Environnement;
import application.modele.personnages.Ennemi;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class EnnemiVue extends PersonnageVue {

    private Pane root;
    private ImageView image;
    private Ennemi ennemi;
    private Environnement env ;


    public EnnemiVue(Environnement env ,Pane root, Ennemi ennemi){
        super(root, ennemi);
        this.root = root;
        this.ennemi=ennemi;
        this.env=env;



    }
    public void afficherCadavres(int x, int y){
        this.image=new ImageView(new Image("file:src/main/resources/application/perso/mortChevalier.png"));
        image.setLayoutX(x);
        image.setLayoutY(y);
        image.setFitWidth(50);
        image.setFitHeight(45);
        root.getChildren().add(image);
    }

    public void supprimerCadavre() {
        this.root.getChildren().remove(image);
    }

    public ImageView getImage(){
        return this.image;
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }
}
