package core_v2.Moves;


import core_v2.Chessboards.Chessboard;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;

/**
 * Created by Filip on 11/21/2017.
 */
public class CastlingMove extends Move {
    public final Piece movingRook;
    public final byte toPositionRook;

    public CastlingMove(Chessboard chessboard, Piece movingPiece, byte toPosition, Piece movingRook, byte toPositionRook) {
        super(chessboard, movingPiece, toPosition, MoveType.CASTLING);
        this.movingRook = movingRook;
        this.toPositionRook = toPositionRook;
    }

    @Override
    public Chessboard make() {

        Chessboard.BoardBuilder builder = new Chessboard.BoardBuilder();

        for (Piece piece : chessboard.getActivePieces()) {
            if (!this.movingPiece.equals(piece) && !this.movingRook.equals(piece)){
                builder.setPiece(piece);
            }
        }

        for (Piece targetPieces : chessboard.getTargetPieces()) {
            builder.setPiece(targetPieces);
        }
        builder.setCastled(true);
        updateCastlings(builder);

        builder.setPiece(movingPiece.withPosition(toPosition));
        builder.setPiece(movingRook.withPosition(toPositionRook));

        builder.setOnMove(chessboard.getOnMove() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE);

        return builder.build();
    }

    @Override
    public String toString() {
        return "CastlingMove{" +
                "movingRook=" + movingRook +
                ", movingPiece=" + movingPiece +
                ", toPositionRook=" + toPositionRook +
                ", toPosition=" + toPosition +
                ", type=" + type +
                '}';
    }

}
