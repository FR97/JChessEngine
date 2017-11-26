package core_v2.Evaluators;



import core_v2.Chessboards.IntBoard;
import core_v2.Game;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.Position;

import java.util.List;

/**
 * Created by Filip on 11/20/2017.
 */
public class BasicEvaluator{

    private int lastBlackCount = 0;
    private int lastWhiteCount = 0;
    private double lastSum = 0;
    public BasicEvaluator(){

    }

    public double evaluate(IntBoard chessboard){

        if(chessboard.getBlackPieceCount() == lastBlackCount && chessboard.getWhitePieceCount() == lastWhiteCount){
            return lastSum;
        }

        List<Position> onMovePositions = chessboard.getPiecePositionsForColor(chessboard.getOnMove());
        PieceColor opponent = chessboard.getOnMove() == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
        List<Position> opponentPositions = chessboard.getPiecePositionsForColor(opponent);
        double sum = chessboard.getOnMove().value();
        for (Position p: onMovePositions) {
            sum += Piece.getPieceById(chessboard.getPiece(p)).type.value();
        }
        for (Position p: opponentPositions){
            sum -=  Piece.getPieceById(chessboard.getPiece(p)).type.value();
        }
       // System.out.println("FINAL SUM IS " + sum);
        lastSum = sum;
        return sum;

    }

    /**
     * Sabira vrednost svih polja sa belim figurama pa od toga oduzima vrednost svih sa crnim
     * Tako da ako trazimo najbolji potez za crnog onda trazimo sto manji broj
     * A ako trazimo za belog onda gledamo sto veci
     * @param chessboard - trenutna sahovska tabla
     * @return odnos vrednosti crnih i belih polja(0 - jednaki, <0 vodi crni, >0 vodi beli
     */


}
