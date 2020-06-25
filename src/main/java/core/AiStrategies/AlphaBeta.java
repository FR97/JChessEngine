package core.AiStrategies;

import core.Chessboards.Chessboard;
import core.Evaluators.Evaluator;
import core.Moves.Move;
import core.Moves.MoveType;

import java.time.Duration;
import java.time.Instant;

public class AlphaBeta implements AiStrategy {
    private Evaluator evaluator;
    private int boardsEvaluated;
    private Move bestMove;

    public AlphaBeta(Evaluator evaluator) {
        this.evaluator = evaluator;
        this.boardsEvaluated = 0;
    }


    @Override
    public Move getBestMove(Chessboard chessboard, int depth) {
        System.out.println("Alpha-beta started thinking with depth" + depth);
        Instant start = Instant.now();
        Move bestMove = null;
        double best = -500000;
        double worst = 500000;
        double current = 0;
        for (Move move : chessboard.getCurrent().getLegalMoves()) {

            Chessboard futureChessboard = move.execute();
            futureChessboard.getCurrent().removeDangerousMoves();
            current = chessboard.getOnMove().isWhite() ?
                    min(depth - 1, futureChessboard, -500000, 500000)
                    : max(depth - 1, futureChessboard, -500000, 500000);

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


    private int min(int depth, Chessboard chessboard, int alpha, int beta) {
      //   chessboard.getOpponent().removeDangerousMoves();
        if (depth == 0 || chessboard.getCurrent().isCheckMated() || chessboard.getCurrent().isStaleMated()) {
            this.boardsEvaluated++;
            return evaluator.evaluate(chessboard, depth);
        }

        int worst = 500000;

        for (Move move : chessboard.getCurrent().getLegalMoves()) {
            if (move.type != MoveType.CHECKMATE) {
                Chessboard futureChessboard = move.execute();
                int current = max(depth - 1, futureChessboard, alpha, beta);


                if (current <= worst)
                    worst = current;
                if (current <= beta)
                    beta = current;
                if (beta < alpha)
                    break;
            }
        }
        return worst;
    }

    private int max(int depth, Chessboard chessboard, int alpha, int beta) {
        if (depth == 0 || chessboard.getCurrent().isCheckMated() || chessboard.getCurrent().isStaleMated()) {
            this.boardsEvaluated++;
            return evaluator.evaluate(chessboard, depth);
        }
        int best = -500000;
        for (Move move : chessboard.getCurrent().getLegalMoves()) {
            if (move.type != MoveType.CHECKMATE) {
                Chessboard futureChessboard = move.execute();
                int current = min(depth - 1, futureChessboard, alpha, beta);
                if (current >= best)
                    best = current;
                if (current >= alpha)
                    alpha = current;
                if (beta < alpha)
                    break;
            }
        }

        return best;
    }

}
