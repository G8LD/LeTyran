package application.modele;

import java.util.ArrayList;

public class ModeleDialogue {
    private int partieActuelle;
    private int nombrePartieTotal;
    private ArrayList<String> texteEntier;

    public ModeleDialogue () {
        texteEntier = new ArrayList<>();
        texteEntier.add(new String("Bonjour, bienvenue au village Village"));
        texteEntier.add(new String("Je suis le chef de ce village"));

        partieActuelle = 0;
        nombrePartieTotal = texteEntier.size() - 1;
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
