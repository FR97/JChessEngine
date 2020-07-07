package fr97.jchess.core.move;


import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.piece.Piece;
import fr97.jchess.core.piece.PieceColor;
import fr97.jchess.core.util.PieceList;

/**
 * Created by Filip on 11/21/2017.
 */
public class EnpassantMove extends Move{
    public final Piece eatenPawn;

    public EnpassantMove(Chessboard chessboard, Piece movingPiece, int toPosition, Piece eatenPawn) {
        super(chessboard, movingPiece, toPosition, MoveType.EN_PASSANT);
        this.eatenPawn = eatenPawn;
    }


    @Override
    public Chessboard execute() {
     /*   if(this.movingPiece.color != chessboard.getOnMove())
            return this.chessboard;
*/
        Chessboard.BoardBuilder boardBuilder = new Chessboard.BoardBuilder();
        PieceList activePieces = chessboard.getActivePieces();
        PieceColor nextOnmove = this.chessboard.getOnMove().isWhite()? PieceColor.BLACK : PieceColor.WHITE;

        boardBuilder.onMove(nextOnmove)
                .pieces(activePieces.insteadOf(this.movingPiece, this.movingPiece.withPosition(to)),movingPiece.color)
                .pieces(chessboard.getTargetPieces().without(eatenPawn),nextOnmove)
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
