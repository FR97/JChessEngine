package core_v2;

import core_v2.Chessboards.Chessboard;
import core_v2.Evaluators.BasicEvaluator;
import core_v2.Evaluators.Evaluator;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Filip on 11/22/2017.
 */
public class Minimax extends Thread{

    private final Chessboard chessboard;
    private int depth;
    private Evaluator evaluator;
    private ConcurrentHashMap<Move,Double> results;
    private boolean min;
    private Move move;

    public Minimax(Chessboard chessboard, int depth, boolean min, ConcurrentHashMap<Move, Double> results, Move move) {
        this.chessboard = chessboard;
        this.depth = depth;
        this.evaluator = new BasicEvaluator();
        this.results = results;
        this.move = move;
        this.min = min;

    }

    @Override
    public void run() {
        double result;
        result = alfabeta(depth, chessboard, -50000, 50000, min);
        results.put(move,result);
    }


    public double alfabeta(int depth, Chessboard chessboard, double alfa, double beta, boolean min){

        if(depth == 0){
            return evaluator.evaluate(chessboard, depth);
        }
        if(min){
            double current = -50000;
            for (Move m : MoveGenerator.getAllPossibleMoves(chessboard, chessboard.getOnMove())){
                Chessboard cb = new Chessboard(chessboard);
                cb.makeMove(m);
                current =Math.max(current, alfabeta(depth-1, cb, alfa, beta, !min));
                alfa = Math.max(alfa, current);
                if(beta <=alfa)
                    break;


            }
            return current;
        }else {
            double current = 50000;
            for (Move m : MoveGenerator.getAllPossibleMoves(chessboard, chessboard.getOnMove())){
                Chessboard cb = new Chessboard(chessboard);
                cb.makeMove(m);
                current =Math.max(current, alfabeta(depth-1, cb, alfa, beta, !min));
                beta = Math.min(beta, current);
                if(beta <=alfa)
                    break;

            }
            return current;
        }

    }

}
