package application.modele;
import java.util.Scanner;

public class Personnage {
    private Direction direction;
    private int x;
    private int y;
    Scanner scanner = new Scanner(System.in);

    public Personnage(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void Deplacement(Direction dir) {

        switch (dir) {
            case Droit:
                this.x += 1;
                break;
            case Gauche :
                this.x -= 1;
                break;
            case Haut :
                this.y+=3;
                break;
            case hautDroit:
                this.y+=3;
                this.x+=1;
                break;
            case hautGauche:
                this.y+=3;
                this.x-=1;
                break;
        }

    }
}