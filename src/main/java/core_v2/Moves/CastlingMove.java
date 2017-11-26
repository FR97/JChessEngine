package core_v2.Moves;

import core_v2.Pieces.King;
import core_v2.Pieces.Piece;
import core_v2.Pieces.Rook;
import core_v2.Utils.Position;

/**
 * Created by Filip on 11/21/2017.
 */
public class CastlingMove extends Move{

    private final Rook movingRook;
    public final Position rookTo;

    public CastlingMove(King king, Rook rook,Position toPosition, Position rookTo) {
        super(king, toPosition, MoveType.CASTLING);
        this.movingRook = rook;
        this.rookTo = rookTo;
    }

    public Rook getMovingRook() {
        return movingRook;
    }

    @Override
    public String toString() {
        return "CastlingMove{movingKing=" + getMovingPiece() + "movingRook=" + movingRook + ", toPosition=" + toPosition + ", rookTo=" + rookTo + ", type=" + type + '}';
    }
}
