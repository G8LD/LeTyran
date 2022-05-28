package application.modele;

import application.modele.armes.Arme;
import application.modele.armes.Hache;
import application.modele.armes.Pioche;
import application.modele.objets.Bois;
import application.modele.objets.Materiau;
import application.modele.objets.Pierre;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private Inventaire inventaire;
    private HashMap<String, Arme> listeArmes;
    private HashMap<String, HashMap<Materiau, Integer>> listeMateriaux;
    private String armeSelected;
    private boolean fabricable;

    public Etabli(Inventaire inventaire) {
        this.inventaire = inventaire;
        fabricable = false;
        initListeArmes();
        initListeMateriaux();
        armeSelected = "Hache1";
    }

    //TODO ajouter les autres armes
    private void initListeArmes() {
        listeArmes = new HashMap<>();
        listeArmes.put("Hache1", new Hache(1));
        listeArmes.put("Pioche1", new Pioche(1));
    }

    private void initListeMateriaux() {
        listeMateriaux = new HashMap<>();
        listeMateriaux.put("Hache1", new HashMap<>() {{
            put(new Bois(), 3);
            put(new Pierre(), 1);
        }});
        listeMateriaux.put("Pioche1", new HashMap<>() {{
            put(new Bois(), 3);
            put(new Pierre(), 1);
        }});
        listeMateriaux.put("Epee1", new HashMap<>() {{
            put(new Bois(), 10);
        }});
    }

    public void fabriquer() {
        Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        Map.Entry materiau;
        while (iterator.hasNext()) {
            materiau = (Map.Entry) iterator.next();
            for (int i = 0; i < (int) materiau.getValue(); i++) {
                //TODO retirer les materiaux de l'inventaire
            }
        }
        listeArmes.remove(armeSelected);
        //TODO ajouter l'arme dans l'inventaire du perso
    }

    private void peutFabriquer() {
        Set listeMateriaux = this.listeMateriaux.get(armeSelected).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        do {
            //TODO vérifie que le joueur a les matériaux nécessaire
        } while(iterator.hasNext() && fabricable);
    }

    public String getArmeSelected() {
        return armeSelected;
    }

    public void setArmeSelected(String armeSelected) {
        this.armeSelected = armeSelected;
        peutFabriquer();
    }

    public boolean getFabricable() {
        return fabricable;
    }

    public HashMap<Materiau, Integer> getListeMateriauxArmeSelected() {
        return listeMateriaux.get(armeSelected);
    }
}
