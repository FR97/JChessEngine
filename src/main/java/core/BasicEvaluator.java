package core;

import core.Pieces.PieceColor;
import core.Utils.Position;

import java.util.TreeSet;

/**
 * Created by Filip on 11/20/2017.
 */
public class BasicEvaluator implements Evaluator<Chessboard>{

    private int lastPieceCount;

    public BasicEvaluator(){
        lastPieceCount = 0;
    }

    /**
     * Sabira vrednost svih polja sa belim figurama pa od toga oduzima vrednost svih sa crnim
     * Tako da ako trazimo najbolji potez za crnog onda trazimo sto manji broj
     * A ako trazimo za belog onda gledamo sto veci
     * @param chessboard - trenutna sahovska tabla
     * @return odnos vrednosti crnih i belih polja(0 - jednaki, <0 vodi crni, >0 vodi beli
     */
    @Override
    public double evaluate(Chessboard chessboard, int depth) {

        //chessboard.printChessboard();
        if(chessboard.getPieceCount() != lastPieceCount){
            lastPieceCount = chessboard.getPieceCount();
        }else{
            return chessboard.getOnMove().value();
        }


        TreeSet<Position> whitePositions = chessboard.getAllPositionsForColor(PieceColor.WHITE);
        TreeSet<Position> blackPositions = chessboard.getAllPositionsForColor(PieceColor.BLACK);
        double sum = chessboard.getOnMove().value();
        for (Position p: whitePositions) {
            sum -= chessboard.getPiece(p).TYPE.value();
        }
        for (Position p: blackPositions){
            sum += chessboard.getPiece(p).TYPE.value();
        }

        return sum;
    }


}
