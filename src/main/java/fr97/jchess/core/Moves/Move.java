package fr97.jchess.core.Moves;

import fr97.jchess.core.Chessboards.Chessboard;
import fr97.jchess.core.Pieces.Piece;
import fr97.jchess.core.Pieces.PieceColor;
import fr97.jchess.core.Utils.PieceList;


/**
 * Created by Filip on 11/21/2017.
 */
public class Move {

    protected final Chessboard chessboard;
    public final Piece movingPiece;
    public final int to;
    public final MoveType type;

    public Move(final Chessboard chessboard, final Piece movingPiece, final int to) {
        this.chessboard = chessboard;
        this.to = to;
        this.movingPiece = movingPiece;
        this.type = MoveType.NORMAL;

    }

    public Move(final Chessboard chessboard, final Piece movingPiece, final int to, final MoveType type) {
        this.chessboard = chessboard;
        this.to = to;
        this.movingPiece = movingPiece;
        this.type = type;
    }

    public Chessboard execute(){

     /*   if(this.movingPiece.color != chessboard.getOnMove())
            return this.chessboard;
*/
        Chessboard.BoardBuilder boardBuilder = new Chessboard.BoardBuilder();
        PieceList activePieces = chessboard.getActivePieces();
        PieceColor nextOnmove = this.chessboard.getOnMove().isWhite()? PieceColor.BLACK : PieceColor.WHITE;

        boardBuilder.setOnMove(nextOnmove)
                .addPieces(activePieces.insteadOf(this.movingPiece, this.movingPiece.withPosition(to)),movingPiece.color)
                .addPieces(chessboard.getTargetPieces(),nextOnmove)
                .setEnpassantPosition(this.type == MoveType.PAWN_DOUBLE_JUMP ? to : -1)
                .setWhiteCastled(chessboard.getWhitePlayer().isCastled())
                .setBlackCastled(chessboard.getBlackPlayer().isCastled());

        return boardBuilder.create();
    }

    public Chessboard undo(){
        return chessboard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (to != move.to) return false;
        if (!movingPiece.equals(move.movingPiece)) return false;
        return type == move.type;
    }

    @Override
    public int hashCode() {
        int result = movingPiece.hashCode();
        result = 31 * result + to;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Move{" +
                "to=" + to +
                ", movingPiece=" + movingPiece +
                '}';
    }
}
