package core_v2.Moves;

import core_v2.Chessboards.Chessboard;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Pieces.PieceType;

/**
 * Created by Filip on 11/21/2017.
 */
public class AttackMove extends Move{

    public final Piece eatenPiece;

    public AttackMove(final Chessboard chessboard, final Piece movingPiece, final Piece eatenPiece) {
        super(chessboard, movingPiece, eatenPiece.position, eatenPiece.type == PieceType.KING ? MoveType.CHECKMATE: MoveType.ATTACK);

        this.eatenPiece = eatenPiece;
    }

    @Override
    public Chessboard make() {
        Chessboard.BoardBuilder builder = new Chessboard.BoardBuilder();

        for(Piece piece : chessboard.getActivePieces()){
            if(!this.movingPiece.equals(piece))
                builder.setPiece(piece);
            //else
            // System.out.println("This piece will be moved " + piece + " from " + piece.position + " to " + toPosition);
        }

        for(Piece targetPieces : chessboard.getTargetPieces()){
            if(!this.eatenPiece.equals(targetPieces))
                builder.setPiece(targetPieces);
            //else
            // System.out.println("This piece is removed " + targetPieces);
        }

        updateCastlings(builder);

        builder.setPiece(movingPiece.withPosition(toPosition));

        builder.setOnMove(chessboard.getOnMove() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE);

        return builder.build();
    }

    @Override
    public String toString() {
        return "AttackMove{" +
                "eatenPiece=" + eatenPiece +
                ", movingPiece=" + movingPiece +
                ", toPosition=" + toPosition +
                ", type=" + type +
                '}';
    }
}
