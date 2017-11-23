package core;

import core.Moves.Move;
import core.Moves.MoveGenerator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Filip on 11/20/2017.
 */
public class Minimax extends Thread{

    private final Chessboard chessboard;
    private int depth;
    private Evaluator evaluator;
    private ConcurrentHashMap<Move,Double> results;
    private boolean min;
    private Move move;

    public Minimax(Chessboard chessboard, int depth, boolean min, ConcurrentHashMap<Move, Double> results, Move m) {
        this.chessboard = chessboard;
        this.depth = depth;
        this.evaluator = new BasicEvaluator();
        this.results = results;
        this.move = m;
        this.min = min;

    }

    @Override
    public void run() {
        double result;
        result = alfabeta(depth, chessboard, -50000, 50000, min);
        System.out.println("\u001B[32m And The final result"+ result +"\u001B[0m");
        results.put(move,result);
    }

    public double min(int depth, Chessboard chessboard){
        //  System.out.println("Min Dubina " + depth);

        // chessboard.printChessboard();
        if(depth == 0){
            return evaluator.evaluate(chessboard, depth);
        }

        double minimum = -50000;

        for (Move m : MoveGenerator.getAllPossibleMoves(chessboard)) {
            Chessboard cb = new Chessboard(chessboard);
            cb.move(m);
            double current = max(depth-1, cb);
            if(current > minimum)
                minimum = current;
        }
        return minimum;
    }

    public double max(int depth, Chessboard chessboard){
        //   System.out.println("Max Dubina " + depth);
        // chessboard.printChessboard();
        if(depth == 0){
            return evaluator.evaluate(chessboard, depth);
        }

        double maximum = 50000;

        for (Move m : MoveGenerator.getAllPossibleMoves(chessboard)) {
            Chessboard cb = new Chessboard(chessboard);
            cb.move(m);
            double current = min(depth-1, cb);
            if(current < maximum)
                maximum = current;

        }
        return maximum;
    }

    public double alfabeta(int depth, Chessboard chessboard, double alfa, double beta, boolean min){

        if(depth == 0){
            double result = evaluator.evaluate(chessboard, depth);
            System.out.println("\u001B[31m The result is "+ result +"\u001B[0m");
            return result;
        }
        if(min){
            double current = -50000;
            for (Move m : MoveGenerator.getAllPossibleMoves(chessboard)){
                Chessboard cb = new Chessboard(chessboard);
                cb.move(m);
                current =Math.max(current, alfabeta(depth-1, cb, alfa, beta, !min));
                alfa = Math.max(alfa, current);
                if(beta <=alfa)
                    break;


            }
            return current;
        }else {
            double current = 50000;
            for (Move m : MoveGenerator.getAllPossibleMoves(chessboard)){
                Chessboard cb = new Chessboard(chessboard);
                cb.move(m);
                current =Math.min(current, alfabeta(depth-1, cb, alfa, beta, !min));
                beta = Math.min(beta, current);
                if(beta <=alfa)
                    break;

            }
            return current;
        }

    }

}
