package fr97.jchess.core.AiStrategies;
import fr97.jchess.core.Moves.Move;
import fr97.jchess.core.Chessboards.Chessboard;

/**
 * Created by Filip on 11/28/2017.
 */
public interface AiStrategy {



    Move getBestMove(Chessboard chessboard, int depth);


}
