package application.modele.armes;

public abstract class Arme {

    private int qualite;

    public Arme(int qualite) {
        this.qualite = qualite;
    }

    public abstract void frapper();

    public  abstract int nbDegat();

    public int getQualite() {
        return qualite;
    }
}
