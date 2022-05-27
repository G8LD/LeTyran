package application.modele;

import application.modele.armes.Arme;
import application.modele.objets.Bois;
import application.modele.objets.Materiau;
import application.modele.objets.Pierre;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Etabli {

    private Environnement env;
    private HashMap<String, HashMap<Materiau, Integer>> listeMateriaux;
    private Arme armeSelected;
    private boolean fabricable;

    public Etabli(Environnement env) {
        this.env = env;
        armeSelected = null;
        fabricable = false;
        initListeMateriaux();
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
        //TODO ajouter les autres armes/qualités
    }

    public void fabriquer() {
        Set listeMateriaux = this.listeMateriaux.get(armeSelected.getClass().getSimpleName() + armeSelected.getQualite()).entrySet();
        Iterator iterator = listeMateriaux.iterator();
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
        Set listeMateriaux = this.listeMateriaux.get(armeSelected.getClass().getSimpleName() + armeSelected.getQualite()).entrySet();
        Iterator iterator = listeMateriaux.iterator();
        do {
            //TODO vérifie que le joueur a les matériaux nécessaire
        } while(iterator.hasNext() && fabricable);
    }

    public void setArmeSelected(Arme armeSelected) {
        this.armeSelected = armeSelected;
        peutFabriquer();
    }

    public boolean getFabricable() {
        return fabricable;
    }
}
