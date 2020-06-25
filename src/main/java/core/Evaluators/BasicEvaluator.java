package core.Evaluators;

import core.Chessboards.Chessboard;
import core.Pieces.Piece;
import core.Utils.PieceList;

/**
 * Created by Filip on 11/26/2017.
 */
public class BasicEvaluator implements Evaluator {


    private int lastWhitePieceCount;
    private int lastBlackPieceCount;
    private double lastSum;

    public BasicEvaluator() {

        lastWhitePieceCount = 0;
        lastBlackPieceCount = 0;
        lastSum = 0;
    }

    /**
     * White is minimizer
     * Black is maximizer
     *
     * @param chessboard
     * @param depth
     * @return
     */
    @Override
    public int evaluate(Chessboard chessboard, int depth) {

        PieceList whitePieces = chessboard.getWhitePieces();
        PieceList blackPieces = chessboard.getBlackPieces();


        /*
        if (lastBlackPieceCount == whitePieces.size() && lastWhitePieceCount == blackPieces.size()) {
            return lastSum;
        }

        lastWhitePieceCount = whitePieces.size();
        lastBlackPieceCount = blackPieces.size();*/

       int sum = 0;

        for (Piece p : whitePieces) {
            sum += p.type.value();
        }
        for (Piece p : blackPieces) {
            sum -= p.type.value();
        }
        //lastSum = sum;

        return sum;
    }




}
