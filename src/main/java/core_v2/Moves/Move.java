package core_v2.Moves;

import core_v2.Chessboards.Chessboard;
import core_v2.Pieces.Piece;
import core_v2.Utils.Position;

/**
 * Created by Filip on 11/21/2017.
 */
public class Move {

    private final Piece movingPiece;
    public final Position toPosition;
    public final MoveType type;

    public Move(Piece movingPiece, Position toPosition, MoveType type) {
        this.movingPiece = movingPiece;
        this.toPosition = toPosition;
        this.type = type;
    }

    public Move(Piece movingPiece, Position toPosition){
        this.movingPiece = movingPiece;
        this.toPosition = toPosition;
        this.type = MoveType.NORMAL;
    }

    public Piece getMovingPiece() {
        return movingPiece;
    }

    @Override
    public String toString() {
        return "Move{movingPiece=" + movingPiece +
                ", toPosition=" + toPosition +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (!movingPiece.equals(move.movingPiece)) return false;
        if (!toPosition.equals(move.toPosition)) return false;
        return type == move.type;
    }

    @Override
    public int hashCode() {
        int result = movingPiece.hashCode();
        result = 31 * result + toPosition.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
