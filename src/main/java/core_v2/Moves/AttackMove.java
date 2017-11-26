package core_v2.Moves;

import core_v2.Pieces.Piece;

/**
 * Created by Filip on 11/21/2017.
 */
public class AttackMove extends Move{

    private final Piece eatenPiece;

    public AttackMove(Piece movingPiece, Piece eatenPiece){
        super(movingPiece, eatenPiece.position, MoveType.ATTACK);

        this.eatenPiece = eatenPiece;
    }

    public Piece getEatenPiece(){
        return this.eatenPiece;
    }

    @Override
    public String toString() {
        return "AttackMove{movingPiece="+ getMovingPiece()+ "eatenPiece=" + eatenPiece + ", toPosition=" + toPosition + ", type=" + type + '}';
    }
}
