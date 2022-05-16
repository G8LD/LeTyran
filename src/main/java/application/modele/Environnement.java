package application.modele;

public class Environnement {

    private Personnage personnage;
    private MapJeu mapJeu;

    public Environnement() {
        mapJeu = new MapJeu();
        personnage = new Personnage(mapJeu,50,4,3);
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public MapJeu getMapJeu() {
        return mapJeu;
    }
}
