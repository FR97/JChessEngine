package core.Pieces;

import java.io.Serializable;

/**
 * Created by Filip on 11/18/2017.
 */
public enum PieceType implements Serializable{

    PAWN(100,"P",0),
    ROOK(500,"R",3),
    KNIGHT(320,"N",2),
    BISHOP(330,"B",4),
    QUEEN(900,"Q",5),
    KING(30000,"K",1);

    private final int value;
    private final int id;
    private final String name;
    PieceType(int value, String name,int id) {

        this.value = value;
        this.name = name;
        this.id = id;
    }

    public int value(){
        return this.value;
    }

    public int id(){return this.id; }
    @Override
    public String toString() {
        return name;
    }
}
