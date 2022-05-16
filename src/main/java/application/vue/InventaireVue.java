package application.vue;

import application.controleur.InventaireControleur;
import application.modele.ObjetJeu;
import application.vue.controls.InvSlot;
import application.modele.Inventaire;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip;

public class InventaireVue {
    private Inventaire inv;

    private InvSlot objPrit;
    private ImageView provenantDeImgView;
    private boolean affiche = false;

    private Pane invPaneConteneur;

    private Image slotImg;

    private StackPane rootPane;
    private InventaireControleur controleur;
    private int indexObjet;

    private AudioClip sound = new AudioClip(getClass().getResource("/application/sons/ui_menu_button_click_24.mp3").toExternalForm());

    public InventaireVue(Inventaire inv, StackPane root, InventaireControleur controleur) {
        this.rootPane = root;
        this.inv = inv;
        this.controleur = controleur;
        this.slotImg = new Image("file:src/main/resources/application/inventaire/slot.png");
        System.out.println(getClass());



    }

    public void lacherObjet() {
        if(this.invPaneConteneur != null) {

            boolean found = false;
            float minDist = 0;
            ImageView selectedImg = null;
            int index = 0;
            int indexConteneurTrouve = -1;
            int nouvellePlace = 0;

            int nombreObjetsInventaire = this.inv.getObjets().size();

            //On vérifie parmis tout les conteneurs qui est le plus proche
            while(!found && index < this.invPaneConteneur.getChildren().size()) {
                //System.out.println(index + " " + this.invPaneConteneur.getChildren().get(index));
                if(this.invPaneConteneur.getChildren().get(index) instanceof ImageView) {
                    ImageView img = (ImageView) this.invPaneConteneur.getChildren().get(index);
                    float distanceX = (float) Math.abs(img.getX() - this.objPrit.getLayoutX());
                    float distanceY = (float) Math.abs(img.getY() - this.objPrit.getLayoutY());

                    float totalDist = distanceX + distanceY;
                    if(minDist == 0 || totalDist < minDist) {
                        selectedImg = img;
                        minDist = totalDist;
                        indexConteneurTrouve = index;
                    }
                }
                index++;
            }

            //Permet de placer l'objet à la même position que l'image view la plus proche
            this.objPrit.setLayoutX(selectedImg.getX() + 8);
            this.objPrit.setLayoutY(selectedImg.getY() + 8);

            //On calcul la place en prenant en sachant que ça fait + 1 après avoir placé l'imageview et l'objet à affiché
            nouvellePlace = (indexConteneurTrouve - 1 * (indexConteneurTrouve / 2));

            if(nouvellePlace >= nombreObjetsInventaire) {

                nouvellePlace = indexConteneurTrouve - nombreObjetsInventaire;
            }


            //System.out.println("index : " + nouvellePlace);

            /*new EventHandler<ActionEvent>() {MapJeu
                @Override
                public void handle(ActionEvent event) {
                    objPrit.fireEvent(new InventaireObjetLacheEvent(InventaireObjetLacheEvent.SAUVEGARDER_OBJET, objPrit));
                }
            };*/


            this.controleur.objetLache(objPrit, nouvellePlace + 1);

            this.objPrit = null;

            //On baisse le son de l'audio
            sound.setVolume(1./30.);
            sound.play();
        }
    }

    public void afficherInventaire() {
        this.affiche = !this.affiche;
        int size = 64;
        if(affiche) {
            ColorInput color = new ColorInput();
            color.setPaint(Color.RED);

            this.invPaneConteneur = new Pane();

            int indexItem = 0;
            for(int i = 0; i < 4; i++) {
                for(int j =0; j < 10; j++) {
                    ImageView imgv = new ImageView(this.slotImg);
                    imgv.setFitWidth(48);
                    imgv.setFitHeight(48);
                    imgv.setX(48 * j);
                    imgv.setY(48 * i);

                    //Ajouter un autre conteneur pour les items
                    this.invPaneConteneur.getChildren().add(imgv);

                    //On vérifie que l'index ne dépasse pas le nombre d'objets actuellement portés
                    if (indexItem < inv.getObjets().size()) {
                        ObjetJeu objetJeu = inv.getObjets().get(indexItem);
                        int placeInventaire = objetJeu.getPlaceInventaire() - 1;

                        //System.out.println(objetJeu.getPlaceInventaire());

                        InvSlot slot = new InvSlot(this, inv.getObjets().get(indexItem), imgv);
                        slot.setPrefWidth(32);
                        slot.setPrefHeight(32);

                        if(placeInventaire >= 10) {
                            //Pour faire en sorte que ça reprenne à 0 dès qu'on atteint la dernière place de la ligne
                            int placeAffiche = placeInventaire - (10 * (placeInventaire/10));
                            slot.setLayoutX(48 * placeAffiche + 8);
                        }else {
                            slot.setLayoutX(48 * placeInventaire + 8);
                        }

                        slot.setLayoutY(48 * (placeInventaire/10) + 8);

                        this.invPaneConteneur.getChildren().add(slot);
                        indexItem++;
                    }
                }
            }

            this.rootPane.getChildren().add(this.invPaneConteneur);
        }else {
            this.rootPane.getChildren().remove(this.invPaneConteneur);
        }
    }

    public void definirObjetPrit(InvSlot obj) {
        this.objPrit = obj;
        provenantDeImgView = obj.getViewParent();
    }
}
