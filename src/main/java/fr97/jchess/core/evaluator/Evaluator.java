package fr97.jchess.core.evaluator;

import fr97.jchess.core.chessboard.Chessboard;

/**
 * Created by Filip on 11/20/2017.
 */
public interface Evaluator {

    int evaluate(Chessboard chessboard, int depth);
}
