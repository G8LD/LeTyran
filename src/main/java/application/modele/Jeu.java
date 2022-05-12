package application.modele;

public class Jeu {

    private Personnage personnage;
    private MapJeu mapJeu;

    public Jeu() {
        mapJeu = new MapJeu();
        personnage = new Personnage(mapJeu);
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public MapJeu getMapJeu() {
        return mapJeu;
    }
}
