package application.modele.map;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.HashMap;

public class Block {
    private int x,y;
    private int type;

    public Block(int x, int y, int type) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


}
