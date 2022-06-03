package application.vue;

import application.modele.MapJeu;
import application.modele.ModeleDialogue;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class VueDialogue {
    private TextFlow dialogFlow;

    private float caractereActuel;
    private Text dialogTxt;

    private int partieTexteAffiche = 0;
    private ModeleDialogue modDialog;
    private boolean afficherFin;
    private boolean doitFermer = false;


    public VueDialogue(ModeleDialogue modDiag, TextFlow dialogFlow, Text dialogTxt){

        this.modDialog = modDiag;
        this.dialogTxt =dialogTxt ;

        this.dialogTxt.setTranslateX(10);
        this.dialogFlow = dialogFlow;
        this.dialogFlow .setPrefSize(MapJeu.WIDTH * MapJeu.TUILE_TAILLE, MapJeu.TUILE_TAILLE * 4);
        this.dialogFlow .setLayoutY(MapJeu.HEIGHT * MapJeu.TUILE_TAILLE - this.dialogFlow .getPrefHeight());
        afficherFin = false;

    }

    public void prochainePartie() {
        this.afficherFin = false;
        partieTexteAffiche = 0;
        caractereActuel = 0;

    }

    public void afficherFin() {
        this.afficherFin = true;
    }

    public void animer(double vitesse) {
        String txt = modDialog.getTexteDialogue();

        if(!afficherFin) {
            if (caractereActuel < txt.length()) {
                caractereActuel += (vitesse + 0.6);
            }
        } else {
            partieTexteAffiche = 0;
            caractereActuel = txt.length();
        }

        if(partieTexteAffiche + (int)caractereActuel <= txt.length()) {
            dialogTxt.setText(txt.substring(partieTexteAffiche, partieTexteAffiche + (int) caractereActuel));

            if(dialogTxt.getBoundsInLocal().getHeight() >= this.dialogFlow .getPrefHeight()) {
                partieTexteAffiche = (int)caractereActuel;
                caractereActuel = 0;

            }
        }
    }

    public void fermer() {
        this.dialogFlow.setVisible(false);
    }
}
