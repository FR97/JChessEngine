package core.Moves;

import core.Pieces.Piece;
import core.Utils.Position;


/**
 * Created by Filip on 11/19/2017.
 */
public class Move {

    public final Piece MOVING_PIECE;
    public final Piece EATEN_PIECE;
    public final Position FROM;
    public final Position TO;
    public final MoveType TYPE;

    public Move(Position from, Position to,Piece movingPiece, MoveType type){
        this(from, to, movingPiece, null, type);
    }

    public Move( Position from, Position to,Piece movingPiece, Piece eatenPiece) {
        // DA NIKAD VISE NISI ZABORAVIO DA PROMENIS PARAMETRE KAD KOPIRAS NESTO
        // JER CES OPET 3 SATA DA SE CUDIS STA SE DESILO DOK NE VIDIS DA SI KOPIRAO NULL KAO DEBILCINA
        this(from, to, movingPiece, eatenPiece, MoveType.NORMAL);
    }

    public Move( Position from, Position to,Piece movingPiece, Piece eatenPiece, MoveType type) {
        MOVING_PIECE = movingPiece;
        EATEN_PIECE = eatenPiece;
        FROM = from;
        TO = to;
        TYPE = type;
    }

    @Override
    public String toString() {
        return "Move{" +
                "MOVING_PIECE=" + MOVING_PIECE +
                ", EATEN_PIECE=" + EATEN_PIECE +
                ", FROM=" + FROM +
                ", TO=" + TO +
                ", TYPE=" + TYPE +
                '}';
    }
}
