package fr97.jchess.core.move;

/**
 * Created by Filip on 11/19/2017.
 */
public enum MoveType {
    NORMAL(1),
    PAWN_DOUBLE_JUMP(1),
    CHECKMATE(5),
    CAPTURE(3),
    EN_PASSANT(3),
    CASTLING(2);

    int value;
    MoveType(int value){
        this.value = value;
    }

    public double value(){
        return value;
    }
}
