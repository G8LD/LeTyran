package application.modele;

import java.util.ArrayList;

public class ModeleDialogue {
    private int partieActuelle;
    private int nombrePartieTotal;
    private ArrayList<String> texteEntier;


    public ModeleDialogue () {
        texteEntier = new ArrayList<>();
        texteEntier.add("Bonjour, bienvenue au village Village");
        texteEntier.add("Je suis le chef de ce village");
        texteEntier.add("Nous devons arrêter le méchant Steven");
        texteEntier.add("Il nous a volé nos précieuses cartes pokémons");
        texteEntier.add("Ayoub a tenté de les récupérer, cependant il est décédé après avoir trébuché sur un oustiti");

        partieActuelle = 0;
        nombrePartieTotal = texteEntier.size() - 1;
    }

    public void reinitialiserDialogue() {
        partieActuelle = 0;
    }

    public String getTexteDialogue() {
        return this.texteEntier.get(partieActuelle);
    }


    public void avancerPartie() {
        if(partieActuelle < nombrePartieTotal) {
            partieActuelle++;
        }
    }

    public boolean dernierePartie() {
        return this.partieActuelle == this.nombrePartieTotal;
    }


}
