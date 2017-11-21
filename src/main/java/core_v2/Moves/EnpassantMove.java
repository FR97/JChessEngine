package core_v2.Moves;

import core_v2.Pieces.Pawn;
import core_v2.Pieces.Piece;
import core_v2.Utils.Position;

/**
 * Created by Filip on 11/21/2017.
 */
public class EnpassantMove extends Move{

    private final Pawn eatenPawn;

    public EnpassantMove(Pawn movingPawn, Pawn eatenPawn){
        // eatenPawn.position.Y-eatenPawn.color.value() - na osnovu boje pojedenog piuna mozemo zakljuciti gde ce se pomeriti piun napadac
        super(movingPawn, Position.get(eatenPawn.position.X, eatenPawn.position.Y+eatenPawn.color.value()), MoveType.ENPASSANT);
        this.eatenPawn = eatenPawn;
    }

    public Pawn getEatenPawn() {
        return eatenPawn;
    }

    @Override
    public String toString() {
        return "EnpassantMove{movingPawn=" + getMovingPiece() + "eatenPawn=" + eatenPawn + ", toPosition=" + toPosition + ", type=" + type + '}';
    }
}
