package application.vue;

import application.controleur.InventaireControleur;
import application.modele.ObjetInventaire;
import application.vue.controls.InvItem;
import application.modele.Inventaire;
import application.vue.controls.InvSlot;
import application.vue.vueEnv.ChargeurRessources;
import javafx.collections.ListChangeListener;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

import static application.modele.Inventaire.PLACE_INVENTAIRE;
import static application.modele.Inventaire.PLACE_MAIN_PERSONNAGE;

public class InventaireVue {
    private Inventaire inv;

    private InvItem objPrit;
    private boolean affiche = false;

    private Pane paneSacInventaire;
    private Pane paneInventaireMain;

    private InventaireControleur controleur;
    public final static int TAILLE_ICON_INVENTAIRE = 32;

    private AudioClip sound = new AudioClip(getClass().getResource("/application/sons/ui_menu_button_click_24.mp3").toExternalForm());

    public InventaireVue(Inventaire inv, InventaireControleur controleur, Pane paneInventaireMain, Pane paneSacInventaire) {
        this.inv = inv;
        this.controleur = controleur;

        this.paneInventaireMain = paneInventaireMain;
        this.paneSacInventaire = paneSacInventaire;

        this.paneSacInventaire.setLayoutY(32);

        this.paneInventaireMain.setPrefWidth(TAILLE_ICON_INVENTAIRE * PLACE_MAIN_PERSONNAGE);
        this.paneInventaireMain.setPrefHeight(TAILLE_ICON_INVENTAIRE);

        this.paneInventaireMain.setBackground(Background.fill(Color.BLUE));
        this.paneSacInventaire.setVisible(false);

        this.ajouterListeObjetDansLaMain();
        this.ajouterListeObjetsSac();


        this.inv.getObjets().addListener(new ListChangeListener<ObjetInventaire>() {
            @Override
            public void onChanged(Change<? extends ObjetInventaire> change) {
                change.next();
                for(int i = 0; i < change.getRemovedSize(); i++) {
                    ObjetInventaire obj = change.getRemoved().get(i);
                    retirerObjetAffichage(obj);

                }

                for(int i = 0; i < change.getAddedSize(); i++) {
                    ObjetInventaire obj = change.getAddedSubList().get(i);
                    ajouterUnObjet(obj);
                }
            }
        });

    }

    //On va récupérer l'élément le plus proche dans le pane par rapport à l'objet déplacer
    public int regarderDansPane(Pane paneChoisi) {
        boolean trouver = false;
        float minDist = 0;
        int index = 0;
        int indexConteneurTrouve = -1;

        while(!trouver && index < paneChoisi.getChildren().size()) {
            //System.out.println(index + " " + this.invPaneConteneur.getChildren().get(index));
            if(paneChoisi.getChildren().get(index) instanceof InvSlot) {
                InvSlot img = (InvSlot) paneChoisi.getChildren().get(index);
                InvSlot parent = (InvSlot)this.objPrit.getParent();
                float distanceX = (float) Math.abs(img.getLayoutX() - this.objPrit.getLayoutX() - parent.getLayoutX());
                float distanceY = (float) Math.abs(img.getLayoutY() - this.objPrit.getLayoutY() - parent.getLayoutY());


                float totalDist = distanceX + distanceY;
                if(minDist == 0 || totalDist < minDist) {
                    minDist = totalDist;
                    indexConteneurTrouve = index;
                }
            }
            index++;
        }
        return indexConteneurTrouve;
    }


    public void lacherObjet(double positionYSouris) {
        if(this.paneSacInventaire != null) {

            InvSlot seletecSlot = null;

            int indexConteneurTrouve = -1;

            InvSlot slotParent = (InvSlot) this.objPrit.getParent();
            if(positionYSouris < TAILLE_ICON_INVENTAIRE) {
                seletecSlot = (InvSlot) this.paneInventaireMain.getChildren().get(regarderDansPane(this.paneInventaireMain));
            } else {
                seletecSlot = (InvSlot) this.paneSacInventaire.getChildren().get(regarderDansPane(this.paneSacInventaire));

            }
            //InvSlot comparerDeux = (InvSlot) this.paneSacInventaire.getChildren().get(regarderDansPane(this.paneSacInventaire));

            //seletecSlot = prendrePlusPetit(comparerUn, comparerDeux, this.paneInventaireMain.getLayoutY(), this.paneSacInventaire.getLayoutY());

            if(seletecSlot != slotParent) {

                //On vérifie si il y a un objet ou non dans la case la plus proche, si c'est le cas, on interverti les deux objets
                if (seletecSlot.getChildren().size() == 2) {
                    //Code pour échanger deux items
                    int autrePlace = this.paneSacInventaire.getChildren().indexOf(slotParent);

                    InvItem selectSlotItem = (InvItem) seletecSlot.getChildren().get(1);
                    seletecSlot.getChildren().remove(selectSlotItem);
                    slotParent.getChildren().remove(this.objPrit);

                    seletecSlot.getChildren().add(this.objPrit);
                    slotParent.getChildren().add(selectSlotItem);

                    this.controleur.echangerObjet(this.objPrit, selectSlotItem, indexConteneurTrouve, autrePlace);

                } else {;

                    this.controleur.objetPlaceInventaireChanger(objPrit, slotParent.getIndex(), seletecSlot.getIndex());

                    slotParent.getChildren().remove(this.objPrit);
                    seletecSlot.getChildren().add(this.objPrit);

                    //On calcul la place en prenant en sachant que ça fait + 1 après avoir placé l'imageview et l'objet à affiché


                }



                //On baisse le son de l'audio
                sound.setVolume(1. / 30.);
                sound.play();
            }
            this.objPrit.setLayoutX(0);
            this.objPrit.setLayoutY(0);
            this.objPrit = null;
        }
    }



    public void ajouterUnObjet(ObjetInventaire obj) {
        InvSlot slot;
        if(obj.getPlaceInventaire() < 5) {
            slot = (InvSlot) this.paneInventaireMain.getChildren().get(obj.getPlaceInventaire());
        } else {
            slot = (InvSlot) this.paneSacInventaire.getChildren().get(obj.getPlaceInventaire() - 5);
        }


        InvItem item = new InvItem(this, obj, TAILLE_ICON_INVENTAIRE);
        item.setPrefWidth(32);
        item.setPrefHeight(32);

        slot.getChildren().add(item);

    }


    public void ajouterListeObjetsSac() {
        ColorInput color = new ColorInput();
        color.setPaint(Color.RED);

        int indexItem = 0;
        for(int i = 0; i < PLACE_INVENTAIRE / 10; i++) {
            for(int j =0; j < 10; j++) {
                InvSlot invSlot = new InvSlot(ChargeurRessources.iconObjets.get("inventaireSac"));

                invSlot.setSize(TAILLE_ICON_INVENTAIRE,TAILLE_ICON_INVENTAIRE);
                invSlot.setLayoutX(TAILLE_ICON_INVENTAIRE * j);
                invSlot.setLayoutY(TAILLE_ICON_INVENTAIRE * i);

                invSlot.setIndex(PLACE_MAIN_PERSONNAGE + (i *10) + j);

                //Ajouter un autre conteneur pour les items
                this.paneSacInventaire.getChildren().add(invSlot);


                //On vérifie que l'index ne dépasse pas le nombre d'objets actuellement portés
                if (indexItem < inv.getObjets().size() && inv.getObjets().get(indexItem).getPlaceInventaire() > 5) {

                    InvItem slot = new InvItem(this, inv.getObjets().get(indexItem), 0);
                    slot.setPrefWidth(32);
                    slot.setPrefHeight(32);

                    invSlot.getChildren().add(slot);
                    indexItem++;
                }
            }
        }

    }

    public void ajouterListeObjetDansLaMain() {

        int indexItem = 0;

        for(int j =0; j < PLACE_MAIN_PERSONNAGE; j++) {
            InvSlot invSlot = new InvSlot(ChargeurRessources.iconObjets.get("inventaireMain"), true);
            invSlot.setIndex(j);
            invSlot.setSize(TAILLE_ICON_INVENTAIRE,TAILLE_ICON_INVENTAIRE);
            invSlot.setLayoutX(TAILLE_ICON_INVENTAIRE * j);

            //Ajouter un autre conteneur pour les items
            this.paneInventaireMain.getChildren().add(invSlot);

            //On vérifie que l'index ne dépasse pas le nombre d'objets actuellement portés
            if (indexItem < inv.getObjets().size() && inv.getObjets().get(indexItem).getPlaceInventaire() < 5) {

                InvItem slot = new InvItem(this, inv.getObjets().get(indexItem), TAILLE_ICON_INVENTAIRE);
                slot.setPrefWidth(32);
                slot.setPrefHeight(32);

                invSlot.getChildren().add(slot);
                indexItem++;
            }
        }

    }


    public void afficherInventaire() {
        this.paneSacInventaire.setVisible(!this.paneSacInventaire.isVisible());

    }

    public void retirerObjetAffichage(ObjetInventaire obj) {
        for(int i = 0; i < this.paneSacInventaire.getChildren().size(); i++) {
            InvSlot node = (InvSlot) this.paneSacInventaire.getChildren().get(i);

            if(node.getChildren().size() == 2) {
                InvItem invItem = (InvItem) node.getChildren().get(1);
                if(invItem.getObjetInventaire() == obj) {
                    node.getChildren().remove(invItem);
                }
            }

        }
    }



    public void jeterObjetInventaire(InvItem item) {
        this.controleur.jeterObjet(item);
    }

    public void definirObjetPrit(InvItem obj) {
        this.objPrit = obj;
    }
}
