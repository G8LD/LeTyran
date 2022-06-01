package application.modele;

import application.modele.armes.*;
import application.modele.objets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private int niveau;
    private Inventaire inventaire;
    private ObservableMap<Arme, HashMap<Materiau, Integer>> listeMateriaux;
    private Arme armeSelected;

    public Etabli(Inventaire inventaire) {
        niveau = 0;
        this.inventaire = inventaire;
        initListeMateriaux();
        armeSelected = armeCorrespondant("Hache1");
        niveau++;
    }

    //TODO ajouter les autres armes
    private void initListeMateriaux() {
        listeMateriaux = FXCollections.observableHashMap();
        Materiau bois = new Bois();
        Materiau pierre = new Pierre();
        Materiau fer = new Fer();
        Materiau platine = new Platine();

        listeMateriaux.put(new Hache(1), new HashMap<>() {{
            put(bois, 3);
            put(pierre, 1);
        }});
        listeMateriaux.put(new Pioche(1), new HashMap<>() {{
            put(bois, 3);
            put(pierre, 1);
        }});
        listeMateriaux.put(new Epee(1), new HashMap<>() {{
            put(bois, 10);
        }});
        listeMateriaux.put(new Arc(1), new HashMap<>() {{
            put(bois, 15);
        }});
        listeMateriaux.put(new Lance(1), new HashMap<>() {{
            put(bois, 25);
            put(pierre, 10);
        }});
    }

    public void fabriquer() {
        Set listeMateriauxArme = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriauxArme.iterator();
        Map.Entry materiau;
        while (iterator.hasNext()) {
            materiau = (Map.Entry) iterator.next();
            for (int i = 0; i < (int) materiau.getValue(); i++) {
                //TODO retirer les materiaux de l'inventaire
            }
        }
        //TODO ajouter l'arme dans l'inventaire du perso
    }

    public boolean peutFabriquer() {
        boolean fabricable;
        Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        Map.Entry materiau;
        int cpt, i;
        do {
            materiau = (Map.Entry) iterator.next();
            cpt = 0; i = 0;
            while (cpt < (int) materiau.getValue() && i < inventaire.getObjets().size()) {
                if (inventaire.getObjets().get(i).getEntite().getClass().equals(materiau.getKey().getClass()))
                    cpt++;
                i++;
            }
            if (cpt == (int) materiau.getValue())
                fabricable = true;
            else
                fabricable = false;
        } while (iterator.hasNext() && fabricable);
        return fabricable;
    }

    public String getArmeSelectedNom() {
        return armeSelected.getClass().getSimpleName() + armeSelected.getQualite();
    }

    public void setArmeSelected(String armeSelected) {
        this.armeSelected = armeCorrespondant(armeSelected);
    }

    private Arme armeCorrespondant(String nomArme) {
        for (Arme arme : listeMateriaux.keySet()) {
            if (nomArme.equals(arme.getClass().getSimpleName() + arme.getQualite()))
                return arme;
        }
        return null;
    }

    public HashMap<Materiau, Integer> getListeMateriauxArmeSelected() {
        return listeMateriaux.get(armeSelected);
    }
}
