package fr97.jchess.core.move;


import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.piece.*;
import fr97.jchess.core.util.PieceList;

/**
 * Created by Filip on 11/21/2017.
 */
public class EnPassantMove extends Move{
    public final Piece eatenPawn;

    public EnPassantMove(Chessboard chessboard, Piece movingPiece, int toPosition, Piece eatenPawn) {
        super(chessboard, movingPiece, toPosition, MoveType.EN_PASSANT);
        this.eatenPawn = eatenPawn;
    }


    @Override
    public Chessboard execute() {
        Chessboard.BoardBuilder boardBuilder = new Chessboard.BoardBuilder();
        PieceList activePieces = chessboard.getActivePieces();
        PieceColor nextOnMove = this.chessboard.getOnMove().isWhite()? PieceColor.BLACK : PieceColor.WHITE;

        boardBuilder.onMove(nextOnMove)
                .pieces(activePieces.insteadOf(this.movingPiece, this.movingPiece.withPosition(to)),movingPiece.color)
                .pieces(chessboard.getTargetPieces().without(eatenPawn),nextOnMove)
                .whiteCastled(chessboard.getWhitePlayer().isCastled())
                .blackCastled(chessboard.getBlackPlayer().isCastled());


        return boardBuilder.create();
    }

    @Override
    public String toString() {
        return "EnpassantMove{" +
                "eatenPawn=" + eatenPawn +
                ", movingPiece=" + movingPiece +
                ", toPosition=" + to +
                ", type=" + type +
                '}';
    }


}
