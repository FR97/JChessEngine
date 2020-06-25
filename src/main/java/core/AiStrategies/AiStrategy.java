package core.AiStrategies;
import core.Moves.Move;
import core.Chessboards.Chessboard;

/**
 * Created by Filip on 11/28/2017.
 */
public interface AiStrategy {



    Move getBestMove(Chessboard chessboard, int depth);


}
