package application.modele;

import application.modele.armes.Arme;
import application.modele.armures.Armure;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

import static application.modele.MapJeu.TUILE_TAILLE;

public class Inventaire {
    private ObservableList<ObjetInventaire> objets = FXCollections.observableArrayList();

    private int stackMax = 5;
    public final static int PLACE_INVENTAIRE = 25;
    public final static int PLACE_MAIN_PERSONNAGE = 5;


    //Permet de savoir quel bloc le joueur a dans les mains
    private IntegerProperty armeIndexProperty;
    private ObjetInventaire objetMain;


    private HashMap<Integer, Boolean> placesDisponible;

    private ObjetInventaire armure;
    private ObjectProperty<ObjetInventaire> armeProperty;

    private Environnement env;
    public Inventaire(Environnement env) {

        placesDisponible = new HashMap<>();
        this.env = env;

        for(int i = 0; i < PLACE_INVENTAIRE; i++) {

            placesDisponible.put(i, true);
        }

        armeIndexProperty = new SimpleIntegerProperty(0);

        armeProperty = new SimpleObjectProperty<>();
    }

    public ObservableList<ObjetInventaire> getObjets(){

        return objets;
    }

    public Arme getArme() {
        if(armeProperty == null || armeProperty.getValue() == null)
            return null;
        return (Arme) armeProperty.getValue().getEntite();
    }

    public final ObjectProperty<ObjetInventaire> getArmeProperty() {
        return armeProperty;
    }

    public Armure getArmure() {
        if(armure == null) {
            return null;
        }
        return (Armure)armure.getEntite();
    }

    public void mettreEquipement(ObjetInventaire objetInventaire) {
        if(objetInventaire.getEntite() instanceof Armure) {
            System.out.println("Vous vous êtes équiper de " + objetInventaire);
            armure = objetInventaire;
        } else if (objetInventaire.getEntite() instanceof Arme) {
            armeProperty.setValue(objetInventaire);
        }
    }

    public void desequiperArmure() {
        System.out.println("Vous avez retirer l'objet");
        armure = null;
    }

    public void desequiperArme() {
        System.out.println("Vous déséquiper l'arme");
        armeProperty.setValue(null);
    }


    public void selectionnerObjetDansMain(int index) {
        if(objetMain != null && objetMain.getPlaceInventaire() != index){
            objetMain = null;
        }

        int i = 0;
        boolean trouver = false;
        while (i < this.getObjets().size() && !trouver) {
            ObjetInventaire obj = this.getObjets().get(i);
            if (obj.getPlaceInventaire() == index) {
                this.objetMain = obj;
            }
            i++;
        }
    }

    public void setArmeIndex(int valeur) {
        this.armeIndexProperty.setValue(valeur);
    }

    public int getArmeIndex() {
        return this.armeIndexProperty.getValue();
    }

    public IntegerProperty getArmeIndexProperty() {
        return this.armeIndexProperty;
    }

    public void ajouterValeurArmeIndex(int valeur) {
        this.armeIndexProperty.setValue(this.armeIndexProperty.getValue() + valeur);
    }

    public void scrollObjetMain(int delta) {
        this.ajouterValeurArmeIndex(delta);
        if(this.getArmeIndex() > PLACE_MAIN_PERSONNAGE - 1) {
            this.setArmeIndex(0);
        } else if(this.getArmeIndex()  < 0) {
            this.setArmeIndex(PLACE_MAIN_PERSONNAGE - 1);
        }

        selectionnerObjetDansMain(this.getArmeIndex());
        System.out.println("VOus vous équipez de l'objet situé à la place " + armeIndexProperty + " " + objetMain);
    }

    public void definirPlacePrise(int place) {
        this.placesDisponible.put(place, false);
    }

    public void libererPlacePrise(int place) {
        this.placesDisponible.put(place, true);
    }

    public int recupererPlaceDispo() {
        int i = 0;
        int placeTrouve = -1;
        while(i < placesDisponible.size() && placeTrouve < 0) {
            if(placesDisponible.get(i) != null && placesDisponible.get(i)) {
                placeTrouve = i;
            }
            i++;
        }
        return placeTrouve;
    }

    public void trierObjetInventaireParPlace() {
        int j;
        ObjetInventaire actuel;
        for (int i = 0; i < objets.size(); i++) {
            j = i;
            actuel = objets.get(i);


            while(j > 0 &&  objets.get(j-1).getPlaceInventaire() > actuel.getPlaceInventaire()) {
                objets.set(j, objets.get(j-1));

                j = j - 1;
            }

            objets.set(i,actuel);


        }
    }

    public boolean ajouterObjetVersionDeux(Entite ent) {

        boolean ajouter = false;
        //On stock l'index de l'endroit dans lequel on peut empiler
        int indexStack = -1;
        //ça permet de vérifier qu'elle est le premier bloc a pouvoir être empiler
        int placeActuel = -1;


        for(int i = 0; i < this.getObjets().size(); i++) {
            ObjetInventaire objetStockee = this.getObjets().get(i);
            if(objetStockee.getEntite().getClass() == ent.getClass()) {

                if(objetStockee.getNombre() < stackMax && placeActuel < objetStockee.getPlaceInventaire()) {
                    placeActuel = objetStockee.getPlaceInventaire();
                    indexStack = i;
                }
            }
        }
        if(indexStack < 0) {
            int placeTrouve = recupererPlaceDispo();

            if(placeTrouve >= 0) {
                ObjetInventaire nouvObjet = new ObjetInventaire(this, ent);

                System.out.println("Place trouvé " + placeTrouve);
                nouvObjet.setPlaceInventaire(placeTrouve);

                definirPlacePrise(placeTrouve);

                this.getObjets().add(nouvObjet);
                ajouter = true;
            } else {
                System.out.println("L'inventaire est rempli");
            }
        } else {
            this.getObjets().get(indexStack).ajouterDansStack();
            ajouter = true;

        }
        return ajouter;
    }

    public void ajouterObjet(Entite obj) {

        if(ajouterObjetVersionDeux(obj)) {
            if (this.env.getListeEntites() != null) {
                this.env.getListeEntites().remove(obj);
            }

            //trierObjetInventaireParPlace();
        }

    }

    public void retirerObjet(ObjetInventaire objetInventaire) {
        libererPlacePrise(objetInventaire.getPlaceInventaire());
        objets.remove(objetInventaire);
        armeProperty.setValue(null);
    }

    public void lacherObjet(ObjetInventaire objet) {
        //On retire l'objet de l'inventaire
        Entite ent = objet.getEntite();
        objets.remove(objet);


        //On veut afficher l'objet sur la carte
        ent.setX(this.env.getJoueur().getX() + TUILE_TAILLE);
        ent.setY(this.env.getJoueur().getY());

        this.env.getListeEntites().add(ent);
        ent.getCollider().setIgnoreCollision(false);

    }

    public ObjetInventaire getObjetCorrespondant(Entite entite) {
        for (ObjetInventaire objet : objets)
            if (objet.getEntite() == entite)
                return objet;
        return null;
    }

    public ObjetInventaire getObjetInventaireSelectionnee() {
        ObjetInventaire objet = null;
        if(this.getObjets().size() > this.getArmeIndex()) {
            objet = this.getObjets().get(this.getArmeIndex());
        }

        return objet;
    }

    public int recupererNombreRessources(String nom) {
        int nombre = 0;
        for(int i = 0; i < this.getObjets().size(); i++) {
            ObjetInventaire obj = this.getObjets().get(i);
            if(obj.getEntite().getClass().getSimpleName() == nom) {
                nombre += obj.getNombre();
            }
        }

        return nombre;
    }

    public boolean retirerNbRessources(String nom, int nombre) {
        boolean aToutRetirer = false;
        int i = 0;
        int nbRetirer = 0;

        while(i < this.getObjets().size() && !aToutRetirer) {
            ObjetInventaire obj = this.getObjets().get(i);
            if(obj.getEntite().getClass().getSimpleName() == nom) {
                while(nbRetirer < nombre && obj.getNombre() > 0) {
                    obj.retirerDansStack();
                    nbRetirer += 1;
                }

                if(nbRetirer == nombre) {
                    aToutRetirer = true;
                }
            }
            i++;
        }

        return aToutRetirer;
    }

    public String toString() {
        return "[Inventaire]" + "\nPlace Main : " + PLACE_MAIN_PERSONNAGE + "\nPlaceTotal" + PLACE_INVENTAIRE;
    }



}
