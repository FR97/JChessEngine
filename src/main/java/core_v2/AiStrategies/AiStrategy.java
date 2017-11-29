package core_v2.AiStrategies;
import core_v2.Moves.Move;
import core_v2.Chessboards.Chessboard;

/**
 * Created by Filip on 11/28/2017.
 */
public interface AiStrategy {



    Move getBestMove(Chessboard chessboard, int depth);


}
