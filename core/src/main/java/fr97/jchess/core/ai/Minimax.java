package fr97.jchess.core.ai;


import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.evaluator.Evaluator;
import fr97.jchess.core.move.Move;

import java.time.Duration;
import java.time.Instant;


/**
 * Created by Filip on 11/28/2017.
 */
public class Minimax implements AiStrategy {

    private final Evaluator evaluator;
    private int boardsEvaluated;

    public Minimax(Evaluator evaluator) {
        this.evaluator = evaluator;
        this.boardsEvaluated = 0;
    }


    @Override
    public Move getBestMove(Chessboard chessboard, int depth) {
        Instant start = Instant.now();
        Move bestMove = null;
        int best = -500000;
        int worst = 500000;
        int current = 0;
        for (Move move : chessboard.getCurrent().getLegalMoves()) {

            Chessboard futureChessboard = move.execute();
            futureChessboard.getCurrent().removeDangerousMoves();
            current = chessboard.getOnMove().isWhite() ? min(depth - 1, futureChessboard) : max(depth - 1, futureChessboard);

            if (chessboard.getOnMove().isWhite() && current >= best) {
                bestMove = move;
                best = current;
            } else if (!chessboard.getOnMove().isWhite() && current <= worst) {
                bestMove = move;
                worst = current;
            }
        }
        Instant end = Instant.now();
        System.out.println("Best " + bestMove + " with value " + current + " found in " + Duration.between(start, end).toMillis()
            + "\nTotal boards evaluated: " + boardsEvaluated);
        return bestMove;
    }


    private int min(int depth, Chessboard chessboard) {
        if (depth == 0 || chessboard.getOpponent().isCheckMated() || chessboard.getOpponent().isStaleMated()) {
            this.boardsEvaluated++;
            return evaluator.evaluate(chessboard, depth);
        }

        int worst = 500000;
        for (Move move : chessboard.getCurrent().getLegalMoves()) {

            Chessboard futureChessboard = move.execute();
            int current = max(depth - 1, futureChessboard);
            if (current <= worst) {
                worst = current;
            }

        }
        return worst;
    }

    private int max(int depth, Chessboard chessboard) {
        if (depth == 0 || chessboard.getOpponent().isCheckMated() || chessboard.getOpponent().isStaleMated()) {
            this.boardsEvaluated++;
            return evaluator.evaluate(chessboard, depth);
        }

        int best = -500000;
        for (Move move : chessboard.getCurrent().getLegalMoves()) {

            Chessboard futureChessboard = move.execute();
            int current = min(depth - 1, futureChessboard);
            if (current >= best) {
                best = current;
            }

        }

        return best;
    }


}
