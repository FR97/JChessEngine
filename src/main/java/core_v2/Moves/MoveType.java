package core_v2.Moves;

/**
 * Created by Filip on 11/19/2017.
 */
public enum MoveType {
    NORMAL(1),
    PAWN_DOUBLE_JUMP(1),
    ENPASSANT(1.02),
    CASTLING(1.2);

    double factor;
    MoveType(double factor){
        this.factor = factor;
    }

    public double factor(){
        return factor;
    }
}