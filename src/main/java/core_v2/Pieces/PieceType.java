package core_v2.Pieces;

import java.io.Serializable;

/**
 * Created by Filip on 11/18/2017.
 */
public enum PieceType implements Serializable{

    PAWN(100,"P",(byte)1),
    ROOK(500,"R",(byte)2),
    KNIGHT(320,"N",(byte)3),
    BISHOP(330,"B",(byte)4),
    QUEEN(900,"Q",(byte)5),
    KING(30000,"K",(byte)6);

    private final int value;
    private final byte id;
    private final String name;
    PieceType(int value, String name,byte id) {

        this.value = value;
        this.name = name;
        this.id = id;
    }

    public int value(){
        return this.value;
    }

    public byte id(){return this.id; }
    @Override
    public String toString() {
        return name;
    }
}
