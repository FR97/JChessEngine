package core_v2.AiStrategies;


import core_v2.Chessboards.Chessboard;
import core_v2.Evaluators.Evaluator;
import core_v2.Moves.Move;


/**
 * Created by Filip on 11/28/2017.
 */
public class Minimax implements AiStrategy {

    private Evaluator evaluator;
    private Move bestMove;

    public Minimax(Evaluator evaluator) {
        this.evaluator = evaluator;

    }


    @Override
    public Move getBestMove(Chessboard chessboard, int depth) {
        Move bestMove = null;
        double best = -500000;
        double worst = -500000;
        double current = 0;
       /* for (int i = 0; i < chessboard.getCurrent().getLegalMoves().size(); i++) {
            if (chessboard.getCurrent().makeMove(chessboard.getCurrent().getLegalMoves().get(i))) {

                current = chessboard.getOnMove().isWhite() ? max(depth - 1, chessboard) : min(depth - 1, chessboard);
                chessboard.undo();
                if (chessboard.getOnMove().isWhite() && current >= best) {
                    bestMove = chessboard.getCurrent().getLegalMoves().get(i);
                    best = current;
                } else if (!chessboard.getOnMove().isWhite() && current <= worst) {
                    bestMove = chessboard.getCurrent().getLegalMoves().get(i);
                    worst = current;
                }
            }
        }*/
        System.out.println("Best " + bestMove + " has value " + current);
        return bestMove;
    }


    private double min(int depth, Chessboard chessboard) {

        if (depth == 0) {
            return evaluator.evaluate(chessboard, depth);
        }

        double worst = 500000;
        for (int i = 0; i < chessboard.getCurrent().getLegalMoves().size(); i++) {
         /*   if (chessboard.getCurrent().makeMove(chessboard.getCurrent().getLegalMoves().get(i))) {
                double current = max(depth - 1, chessboard);
                if (current <= worst) {
                    worst = current;
                }
                chessboard.undo();
            }*/
        }
        return worst;
    }

    private double max(int depth, Chessboard chessboard) {

        if (depth == 0) {
            return evaluator.evaluate(chessboard, depth);
        }

        double best = -500000;
        for (int i = 0; i < chessboard.getCurrent().getLegalMoves().size(); i++) {
           /* if (chessboard.getCurrent().makeMove(chessboard.getCurrent().getLegalMoves().get(i))) {
                double current = min(depth - 1, chessboard);
                if (current >= best) {
                    best = current;
                }
                chessboard.undo();
            }*/

        }

        return best;
    }


}
