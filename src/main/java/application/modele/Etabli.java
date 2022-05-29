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
    private HashMap<Arme, Boolean> disponibles;
    private ObservableMap<Arme, HashMap<Materiau, Integer>> listeMateriaux;
    private Arme armeSelected;
    private boolean fabricable;

    public Etabli(Inventaire inventaire) {
        niveau = 0;
        this.inventaire = inventaire;
        fabricable = false;
        initListeMateriaux();
        initDispo();
        armeSelected = armeCorrespondant("Hache1");
        niveau++;
        debloquer();
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

    private void initDispo() {
        disponibles = new HashMap<>();
        for (Arme arme : listeMateriaux.keySet()) {
            disponibles.put(arme, false);
        }
    }

    private void debloquer() {
        for (Arme arme : listeMateriaux.keySet()) {
            if (arme.getQualite() == niveau) {
                disponibles.remove(arme);
                disponibles.put(arme, true);
            }
        }
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

        disponibles.remove(armeSelected);
        disponibles.put(armeSelected, false);
        //TODO ajouter l'arme dans l'inventaire du perso
    }

    private void peutFabriquer() {
        Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        do {
            //TODO vérifie que le joueur a les matériaux nécessaire
            iterator.next();
        } while (iterator.hasNext() && fabricable);
        fabricable = true;
    }

    public String getArmeSelectedNom() {
        return armeSelected.getClass().getSimpleName() + armeSelected.getQualite();
    }

    public void setArmeSelected(String armeSelected) {
        this.armeSelected = armeCorrespondant(armeSelected);
        peutFabriquer();
    }

    private Arme armeCorrespondant(String nomArme) {
        for (Arme arme : listeMateriaux.keySet()) {
            if (nomArme.equals(arme.getClass().getSimpleName() + arme.getQualite()))
                return arme;
        }
        return null;
    }

    public boolean getFabricable() {
        return fabricable;
    }

    public void setFabricable(boolean fabricable) {
        this.fabricable = fabricable;
    }

    public HashMap<Materiau, Integer> getListeMateriauxArmeSelected() {
        return listeMateriaux.get(armeSelected);
    }

    public ObservableMap<Arme, HashMap<Materiau, Integer>> getListeMateriaux() {
        return listeMateriaux;
    }

    public boolean armeDispo() {
        return disponibles.get(armeSelected);
    }
}
