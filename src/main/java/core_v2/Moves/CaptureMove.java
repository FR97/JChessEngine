package core_v2.Moves;

import core_v2.Chessboards.Chessboard;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Pieces.PieceType;
import core_v2.Utils.PieceList;

/**
 * Created by Filip on 11/21/2017.
 */
public class CaptureMove extends Move {

    public final Piece eatenPiece;

    public CaptureMove(final Chessboard chessboard, final Piece movingPiece, final Piece eatenPiece) {
        super(chessboard, movingPiece, eatenPiece.position, eatenPiece.type == PieceType.KING ? MoveType.CHECKMATE : MoveType.CAPTURE);

        this.eatenPiece = eatenPiece;
    }

    @Override
    public Chessboard execute() {
      /*  if(this.movingPiece.color != chessboard.getOnMove())
            return chessboard;
       */


        Chessboard.BoardBuilder boardBuilder = new Chessboard.BoardBuilder();
        PieceList activePieces = chessboard.getActivePieces();
        PieceColor nextOnmove = this.chessboard.getOnMove().isWhite()? PieceColor.BLACK : PieceColor.WHITE;

        boardBuilder.setOnMove(nextOnmove)
                .addPieces(activePieces.insteadOf(this.movingPiece, this.movingPiece.withPosition(to)),movingPiece.color)
                .addPieces(chessboard.getTargetPieces().without(eatenPiece),nextOnmove)
                .setWhiteCastled(chessboard.getWhitePlayer().isCastled())
                .setBlackCastled(chessboard.getBlackPlayer().isCastled());

        return boardBuilder.create();
    }


    @Override
    public String toString() {
        return "AttackMove{" +
                "eatenPiece=" + eatenPiece +
                ", movingPiece=" + movingPiece +
                ", toPosition=" + to +
                "eatenPiece=" + eatenPiece +
                ", type=" + type +
                '}';
    }
}
