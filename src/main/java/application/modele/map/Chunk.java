package application.modele.map;

import java.util.HashMap;

public class Chunk {
    private int x,y;
    private HashMap<String, Block> blocsChunk;

    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;

    }
}
