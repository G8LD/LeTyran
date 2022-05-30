package application.vue;

import application.controleur.InventaireControleur;
import application.modele.ObjetJeu;
import application.vue.controls.InvItem;
import application.modele.Inventaire;
import application.vue.controls.InvSlot;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip;

public class InventaireVue {
    private Inventaire inv;

    private InvItem objPrit;
    private boolean affiche = false;

    private Pane invPaneConteneur;

    private Pane rootPane;
    private InventaireControleur controleur;

    private Image slotImg = new Image("file:src/main/resources/application/inventaire/slot.png");
    private AudioClip sound = new AudioClip(getClass().getResource("/application/sons/ui_menu_button_click_24.mp3").toExternalForm());

    public InventaireVue(Inventaire inv, Pane root, InventaireControleur controleur) {
        this.rootPane = root;
        this.inv = inv;
        this.controleur = controleur;

        this.invPaneConteneur = new Pane();

        this.invPaneConteneur.setVisible(false);
        this.ajouterListeObjets();

        this.inv.getObjets().addListener(new ListChangeListener<ObjetJeu>() {
            @Override
            public void onChanged(Change<? extends ObjetJeu> change) {
                change.next();
                for(int i = 0; i < change.getRemovedSize(); i++) {
                    ObjetJeu obj = change.getRemoved().get(i);
                    retirerObjetAffichage(obj);

                }

                for(int i = 0; i < change.getAddedSize(); i++) {
                    ObjetJeu obj = change.getAddedSubList().get(i);
                    ajouterUnObjet(obj);
                }
            }
        });

    }

    public void lacherObjet(InvItem invItem) {
        if(this.invPaneConteneur != null) {

            boolean found = false;
            float minDist = 0;
            InvSlot seletecSlot = null;
            int index = 0;
            int indexConteneurTrouve = -1;
            int nouvellePlace = 0;

            int nombreObjetsInventaire = this.inv.getObjets().size();

            InvSlot slotParent = (InvSlot) this.objPrit.getParent();


            //On vérifie parmis tout les conteneurs qui est le plus proche
            while(!found && index < this.invPaneConteneur.getChildren().size()) {
                //System.out.println(index + " " + this.invPaneConteneur.getChildren().get(index));
                if(this.invPaneConteneur.getChildren().get(index) instanceof InvSlot) {
                    InvSlot img = (InvSlot) this.invPaneConteneur.getChildren().get(index);
                    InvSlot parent = (InvSlot)this.objPrit.getParent();
                    float distanceX = (float) Math.abs(img.getLayoutX() - this.objPrit.getLayoutX() - parent.getLayoutX());
                    float distanceY = (float) Math.abs(img.getLayoutY() - this.objPrit.getLayoutY() - parent.getLayoutY());


                    float totalDist = distanceX + distanceY;
                    if(minDist == 0 || totalDist < minDist) {
                        seletecSlot = img;
                        minDist = totalDist;
                        indexConteneurTrouve = index;
                    }
                }
                index++;
            }

            if(seletecSlot != slotParent) {

                if (seletecSlot.getChildren().size() == 2) {
                    //Code pour échanger deux items
                    int autrePlace = this.invPaneConteneur.getChildren().indexOf(slotParent);

                    InvItem selectSlotItem = (InvItem) seletecSlot.getChildren().get(1);
                    seletecSlot.getChildren().remove(selectSlotItem);
                    slotParent.getChildren().remove(this.objPrit);

                    seletecSlot.getChildren().add(this.objPrit);
                    slotParent.getChildren().add(selectSlotItem);

                    this.controleur.echangerObjet(this.objPrit, selectSlotItem, indexConteneurTrouve, autrePlace);

                } else {
                    slotParent.getChildren().remove(this.objPrit);
                    seletecSlot.getChildren().add(this.objPrit);

                    //On calcul la place en prenant en sachant que ça fait + 1 après avoir placé l'imageview et l'objet à affiché
                    this.controleur.objetPlaceInventaireChanger(objPrit, nouvellePlace + 1);
                }

                this.objPrit.setLayoutX(8);
                this.objPrit.setLayoutY(8);

                //On baisse le son de l'audio
                sound.setVolume(1. / 30.);
                sound.play();
            }
            this.objPrit = null;
        }
    }

    public void ajouterUnObjet(ObjetJeu obj) {
        InvSlot slot = (InvSlot) this.invPaneConteneur.getChildren().get(obj.getPlaceInventaire());

        InvItem item = new InvItem(this, obj, slot);
        item.setPrefWidth(32);
        item.setPrefHeight(32);

        slot.getChildren().add(item);

    }

    public void ajouterListeObjets() {
        ColorInput color = new ColorInput();
        color.setPaint(Color.RED);

        int indexItem = 0;
        for(int i = 0; i < 4; i++) {
            for(int j =0; j < 10; j++) {
                InvSlot invSlot = new InvSlot(slotImg);

                invSlot.setSize(48,48);
                invSlot.setLayoutX(48 * j);
                invSlot.setLayoutY(48 * i);

                //Ajouter un autre conteneur pour les items
                this.invPaneConteneur.getChildren().add(invSlot);

                //On vérifie que l'index ne dépasse pas le nombre d'objets actuellement portés
                if (indexItem < inv.getObjets().size()) {

                    InvItem slot = new InvItem(this, inv.getObjets().get(indexItem), invSlot);
                    slot.setPrefWidth(32);
                    slot.setPrefHeight(32);

                    invSlot.getChildren().add(slot);
                    indexItem++;
                }
            }
        }

        this.rootPane.getChildren().add(this.invPaneConteneur);
    }

    public void afficherInventaire() {
        this.invPaneConteneur.setVisible(!this.invPaneConteneur.isVisible());

    }

    public void retirerObjetAffichage(ObjetJeu obj) {
        for(int i = 0; i < this.invPaneConteneur.getChildren().size(); i++) {
            InvSlot node = (InvSlot) this.invPaneConteneur.getChildren().get(i);

            if(node.getChildren().size() == 2) {
                InvItem invItem = (InvItem) node.getChildren().get(1);
                if(invItem.getObjet() == obj) {
                    node.getChildren().remove(invItem);
                }
            }

        }
    }

    public void lacherObjetInventaire(InvItem item) {
        this.controleur.lacherObjet(item);
    }

    public void definirObjetPrit(InvItem obj) {
        System.out.println(obj);
        this.objPrit = obj;
    }
}
