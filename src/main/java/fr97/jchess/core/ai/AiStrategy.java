package fr97.jchess.core.ai;
import fr97.jchess.core.move.Move;
import fr97.jchess.core.chessboard.Chessboard;

/**
 * Created by Filip on 11/28/2017.
 */
public interface AiStrategy {

    Move getBestMove(Chessboard chessboard, int depth);
}
