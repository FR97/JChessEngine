package fr97.jchess.core.Evaluators;

import fr97.jchess.core.Chessboards.Chessboard;

/**
 * Created by Filip on 11/20/2017.
 */
public interface Evaluator {

    int evaluate(Chessboard chessboard, int depth);
}
