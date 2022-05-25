package application.modele.armes;

public abstract class Arme {

    private int qualite;

    public Arme(int qualite) {
        this.qualite = qualite;
    }
    //pour les ennemis
    public abstract void frapper();
    //renvoie les dégâts de l'arme selon la qualité
    public  abstract int nbDegat();

    public int getQualite() {
        return qualite;
    }
}
