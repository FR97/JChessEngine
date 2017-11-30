package core_v2.Moves;


import core_v2.Chessboards.Chessboard;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;

/**
 * Created by Filip on 11/21/2017.
 */
public class EnpassantMove extends Move{
    public final Piece eatenPawn;

    public EnpassantMove(Chessboard chessboard, Piece movingPiece, byte toPosition, Piece eatenPawn) {
        super(chessboard, movingPiece, toPosition, MoveType.ENPASSANT);
        this.eatenPawn = eatenPawn;
    }

    @Override
    public Chessboard make() {
        Chessboard.BoardBuilder builder = new Chessboard.BoardBuilder();

        for(Piece piece : chessboard.getActivePieces()){
            if(!this.movingPiece.equals(piece))
                builder.setPiece(piece);
            // else
            // System.out.println("This piece will be moved " + piece + " from " + piece.position + " to " + toPosition);
        }

        for(Piece targetPieces : chessboard.getTargetPieces()){
            if(!this.eatenPawn.equals(targetPieces))
                builder.setPiece(targetPieces);
            // else
            //System.out.println("This piece is removed " + targetPieces);
        }

        builder.setPiece(movingPiece.withPosition(toPosition));

        builder.setOnMove(chessboard.getOnMove() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE);

        return builder.build();
    }

    @Override
    public String toString() {
        return "EnpassantMove{" +
                "eatenPawn=" + eatenPawn +
                ", movingPiece=" + movingPiece +
                ", toPosition=" + toPosition +
                ", type=" + type +
                '}';
    }


}
