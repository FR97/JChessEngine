package core_v2.Moves;

import core_v2.Pieces.Piece;
import core_v2.Utils.Position;

/**
 * Created by Filip on 11/21/2017.
 */
public class Move {

    public final int movingPiece;
    public final int eatenPiece;
    public final Position from;
    public final Position to;
    public final MoveType type;

    public Move(int movingPiece,Position from, Position to ) {
        this.movingPiece = movingPiece;
        this.from = from;
        this.to = to;
        this.eatenPiece = 0;
        this.type = MoveType.NORMAL;
    }

    public Move(int movingPiece,Position from, Position to, MoveType type) {
        this.movingPiece = movingPiece;
        this.from = from;
        this.to = to;
        this.eatenPiece = 0;
        this.type = type;
    }

    public Move(int movingPiece,Position from, Position to, int eatenPiece) {
        this.movingPiece = movingPiece;
        this.from = from;
        this.to = to;
        this.eatenPiece = eatenPiece;
        this.type = MoveType.NORMAL;
    }

    public Move(int movingPiece,Position from, Position to, int eatenPiece, MoveType type) {
        this.movingPiece = movingPiece;
        this.from = from;
        this.to = to;
        this.eatenPiece = eatenPiece;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Move{" +
                "movingPiece=" + movingPiece +
                ", eatenPiece=" + eatenPiece +
                ", from=" + from +
                ", to=" + to +
                ", type=" + type +
                '}';
    }
}
