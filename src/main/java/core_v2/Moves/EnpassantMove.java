package core_v2.Moves;


import core_v2.Chessboards.Chessboard;
import core_v2.Pieces.FinalPiece;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.PieceList;
import core_v2.Utils.Position;

/**
 * Created by Filip on 11/21/2017.
 */
public class EnpassantMove extends Move{
    public final Piece eatenPawn;

    public EnpassantMove(Chessboard chessboard, Piece movingPiece, int toPosition, Piece eatenPawn) {
        super(chessboard, movingPiece, toPosition, MoveType.ENPASSANT);
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

        boardBuilder.setOnMove(nextOnmove)
                .addPieces(activePieces.insteadOf(this.movingPiece, this.movingPiece.withPosition(to)),movingPiece.color)
                .addPieces(chessboard.getTargetPieces().without(eatenPawn),nextOnmove)
                .setWhiteCastled(chessboard.getWhitePlayer().isCastled())
                .setBlackCastled(chessboard.getBlackPlayer().isCastled());


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
