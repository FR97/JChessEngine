package fr97.jchess.core.move;


import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.piece.Piece;
import fr97.jchess.core.piece.PieceColor;
import fr97.jchess.core.util.PieceList;

/**
 * Created by Filip on 11/21/2017.
 */
public class CastlingMove extends Move {
    public final Piece movingRook;
    public final int rookTo;

    public CastlingMove(Chessboard chessboard, Piece movingPiece, int toPosition, Piece movingRook, int rookTo) {
        super(chessboard, movingPiece, toPosition, MoveType.CASTLING);
        this.movingRook = movingRook;
        this.rookTo = rookTo;
    }

    @Override
    public Chessboard execute() {
        if (this.movingPiece.color != chessboard.getOnMove())
            return this.chessboard;

        Chessboard.BoardBuilder boardBuilder = new Chessboard.BoardBuilder();
        PieceList activePieces = chessboard.getActivePieces();
        PieceColor nextOnMove = this.chessboard.getOnMove().isWhite() ? PieceColor.BLACK : PieceColor.WHITE;

        boardBuilder.onMove(nextOnMove)
            .pieces(activePieces
                .insteadOf(this.movingPiece, this.movingPiece.withPosition(to))
                .insteadOf(this.movingRook, this.movingRook.withPosition(rookTo)), movingPiece.color)
            .pieces(chessboard.getTargetPieces(), nextOnMove)
            .whiteCastled(!nextOnMove.isWhite())
            .blackCastled(nextOnMove.isWhite());
        return boardBuilder.create();
    }


    @Override
    public String toString() {
        return "CastlingMove{" +
            "movingRook=" + movingRook +
            ", movingPiece=" + movingPiece +
            ", toPositionRook=" + rookTo +
            ", toPosition=" + to +
            ", type=" + type +
            '}';
    }

}
