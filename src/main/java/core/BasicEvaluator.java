package core;

import core.Pieces.PieceColor;
import core.Utils.Position;

import java.util.TreeSet;

/**
 * Created by Filip on 11/20/2017.
 */
public class BasicEvaluator implements Evaluator<Chessboard>{

    @Override
    public double evaluate(Chessboard chessboard) {

        TreeSet<Position> whitePositions = chessboard.getAllPositionsForColor(PieceColor.WHITE);
        TreeSet<Position> blackPositions = chessboard.getAllPositionsForColor(PieceColor.BLACK);

        return 0;
    }

    public double calculatePieceValue(Chessboard chessboard){
        return 0;
    }
}
