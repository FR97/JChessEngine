package core_v2.Evaluators;

import core_v2.Chessboards.Chessboard;
import core_v2.Chessboards.IBoard;

/**
 * Created by Filip on 11/20/2017.
 */
public interface Evaluator {

    double evaluate(Chessboard chessboard, int depth);
}
