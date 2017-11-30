package core_v2.AiStrategies;



import core_v2.Chessboards.Chessboard;
import core_v2.Evaluators.Evaluator;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.PieceColor;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by Filip on 11/28/2017.
 */
public class Minimax implements AiStrategy{

    private Evaluator evaluator;
    private Move bestMove;
    public Minimax(Evaluator evaluator){
        this.evaluator = evaluator;

    }


    @Override
    public Move getBestMove(Chessboard chessboard, int depth) {
        Instant start = Instant.now();
        Move bestMove = null;
        double value = 0;
        double best = -500000;
        double worst = 500000;
        System.out.println("Minimax started with depth " + depth);
        for(Move move : MoveGenerator.getAllPossibleMoves(chessboard, true)){
            Chessboard futureBoard = move.make();
            double current = chessboard.getOnMove() == PieceColor.WHITE ?
                    alfabeta(depth-1, futureBoard, -500000, 500000, false)
                    : alfabeta(depth-1,futureBoard, -500000, 500000, true);
            if(chessboard.getOnMove() == PieceColor.WHITE && current >= best){

                best = current;
                value = current;
                bestMove = move;
            }else if(chessboard.getOnMove() == PieceColor.BLACK && current <= worst){
                System.out.println("Current: " + current);
                worst = current;
                value = current;
                bestMove = move;
            }
        }
        Instant end = Instant.now();
        System.out.println("Minimax with " + depth + " finished in " + Duration.between(start, end).toMillis() + "\nBest move found: " + bestMove + " with value " + value);
        return bestMove;
    }


    private double min(Chessboard chessboard, int depth){
        if(depth == 0){
            return evaluator.evaluate(chessboard,depth);
        }
        double worst = 500000;
        for(Move move : MoveGenerator.getAllPossibleMoves(chessboard, true)){
            Chessboard futureBoard = move.make();
            double current =  max(futureBoard, depth-1);
            if(current <= worst){
                worst = current;
                bestMove = move;
            }
        }
        return worst;
    }
    private double max(Chessboard chessboard, int depth){
        if(depth == 0){
            return evaluator.evaluate(chessboard,depth);
        }
        double best = -500000;
        for(Move move : MoveGenerator.getAllPossibleMoves(chessboard, true)){
            Chessboard futureBoard = move.make();
           double current =  min(futureBoard, depth-1);
           if(current >= best){
               best = current;
               bestMove = move;
           }
        }


        return best;
    }

    public double alfabeta(int depth, Chessboard chessboard, double alfa, double beta, boolean max){

        if(depth == 0){
            return evaluator.evaluate(chessboard, depth);
        }
        if(max){
            double current = -500000;
            for (Move move : MoveGenerator.getAllPossibleMoves(chessboard, true)){
                Chessboard futureBoard = move.make();
                current =Math.max(current, alfabeta(depth-1, futureBoard, alfa, beta, !max));
                alfa = Math.max(alfa, current);
                if(beta <=alfa)
                    break;

            }
            return alfa;
        }else {
            double current = 500000;
            for (Move move : MoveGenerator.getAllPossibleMoves(chessboard, true)){
                Chessboard futureBoard = move.make();
                current =Math.min(current, alfabeta(depth-1, futureBoard, alfa, beta, !max));
                beta = Math.min(beta, current);
                if(beta <=alfa)
                    break;

            }
            return beta;
        }

    }


}
