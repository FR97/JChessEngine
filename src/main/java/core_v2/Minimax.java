package core_v2;

import core_v2.Chessboards.IntBoard;
import core_v2.Evaluators.BasicEvaluator;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;


/**
 * Created by Filip on 11/26/2017.
 */
public class Minimax {

    private BasicEvaluator evaluator;

    public Minimax(){

        evaluator = new BasicEvaluator();
    }

    public double negaMax(int depth, IntBoard chessboard){

        if(depth == 0){
            return evaluator.evaluate(chessboard);
        }
        double best = -50000;
        for(Move move : MoveGenerator.getAllPossibleMovesForPlayer(chessboard)){
            IntBoard newChessboard = new IntBoard(chessboard);
            newChessboard.makeMove(move);
            double current = -negaMax(depth-1, newChessboard);
            best = Math.max(best,current);

        }
        return best;
    }

    public double minimax(int depth, IntBoard chessboard, boolean max){

        if(depth == 0){
            return evaluator.evaluate(chessboard);
        }
        if(max){
            double best = -50000;
            for(Move move : MoveGenerator.getAllPossibleMovesForPlayer(chessboard)){
                IntBoard newChessboard = new IntBoard(chessboard);
                newChessboard.makeMove(move);
                double current = minimax(depth-1, newChessboard,!max);
                best = Math.max(best,current);

            }
            return best;
        }else{
            double min = 50000;
            for(Move move : MoveGenerator.getAllPossibleMovesForPlayer(chessboard)){
                IntBoard newChessboard = new IntBoard(chessboard);
                newChessboard.makeMove(move);
                double current = minimax(depth-1, newChessboard,!max);
                min = Math.min(min,current);

            }
            return min;
        }

    }

    public double alphabeta(int depth, IntBoard chessboard, double alpha, double beta){

        if(depth == 0){
            return evaluator.evaluate(chessboard);
        }


        return 0;
    }
}
