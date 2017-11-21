package core_v2.Pieces;

import java.io.Serializable;

/**
 * Created by Filip on 11/18/2017.
 */
public enum PieceType implements Serializable{

    PAWN(100,"P"),
    ROOK(500,"R"),
    KNIGHT(320,"N"),
    BISHOP(330,"B"),
    QUEEN(900,"Q"),
    KING(30000,"K");

    private final int value;
    private final String name;
    PieceType(int value, String name) {

        this.value = value;
        this.name = name;
    }

    public int value(){
        return this.value;
    }

    @Override
    public String toString() {
        return name;
    }
}
