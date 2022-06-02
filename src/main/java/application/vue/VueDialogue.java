package application.vue;

import application.controleur.Controleur;
import application.modele.MapJeu;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class VueDialogue {
    private ScrollPane scrollDialogue;
    private Pane root;
    private TextFlow dialogFlow;

    private String textAffiche;
    private float caractereActuel;
    private Text dialogTxt;

    private int partieAffiche = 0;


    private String texte = "Emensis itaque difficultatibus multis et nive obrutis callibus plurimis ubi prope Rauracum ventum est ad supercilia fluminis Rheni, resistente multitudine Alamanna pontem suspendere navium conpage Romani vi nimia vetabantur ritu grandinis undique convolantibus telis, et cum id inpossibile videretur, imperator cogitationibus magnis attonitus, quid capesseret ambigebat.\n" +
            "\n" +
            "Vita est illis semper in fuga uxoresque mercenariae conductae ad tempus ex pacto atque, ut sit species matrimonii, dotis nomine futura coniunx hastam et tabernaculum offert marito, post statum diem si id elegerit discessura, et incredibile est quo ardore apud eos in venerem uterque solvitur sexus.";

    public VueDialogue(Pane root, ScrollPane scrollDialogue, TextFlow dialogFlow, Text dialogTxt){

        this.dialogTxt =dialogTxt ;
        this.root = root;

        this.dialogFlow = dialogFlow;
        this.dialogFlow .setPrefSize(MapJeu.WIDTH * MapJeu.TUILE_TAILLE, MapJeu.TUILE_TAILLE * 4);
        this.dialogFlow .setLayoutY(MapJeu.HEIGHT * MapJeu.TUILE_TAILLE - this.dialogFlow .getPrefHeight());


        this.definirText(texte);

    }

    public void definirText(String txt) {
        textAffiche = txt;
        caractereActuel = 0;
    }

    public void animer(double vitesse) {
        if(caractereActuel < textAffiche.length()) {
            caractereActuel += (vitesse + 1);
        }

        if(partieAffiche + (int)caractereActuel < texte.length()) {
            dialogTxt.setText(texte.substring(partieAffiche, partieAffiche + (int) caractereActuel));
        }

        if(dialogTxt.getBoundsInLocal().getHeight() >= this.dialogFlow .getPrefHeight()) {
            partieAffiche = (int)caractereActuel;
            caractereActuel = 0;

        }


    }
}
