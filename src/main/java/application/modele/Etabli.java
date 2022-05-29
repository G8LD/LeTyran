package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Epee;
import application.modele.armes.Hache;
import application.modele.armes.Pioche;
import application.modele.objets.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private Inventaire inventaire;
    private ObservableMap<Arme, HashMap<Materiau, Integer>> listeMateriaux;
    private Arme armeSelected;
    private boolean fabricable;

    public Etabli(Inventaire inventaire) {
        this.inventaire = inventaire;
        fabricable = false;
        initListeMateriaux();
        armeSelected = armeCorrespondant("Hache1");
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

    private void peutFabriquer() {
        //TODO vérifier que le joueur de l'a pas déjà
        if (!listeMateriaux.containsKey(armeSelected))
            fabricable = false;
        else {
            Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
            Iterator iterator = listeMateriaux.iterator();
            do {
                //TODO vérifie que le joueur a les matériaux nécessaire
                iterator.next();
            } while (iterator.hasNext() && fabricable);
            fabricable = true;
        }
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

    public HashMap<Materiau, Integer> getListeMateriauxArmeSelected() {
        return listeMateriaux.get(armeSelected);
    }

    public ObservableMap<Arme, HashMap<Materiau, Integer>> getListeMateriaux() {
        return listeMateriaux;
    }
}
