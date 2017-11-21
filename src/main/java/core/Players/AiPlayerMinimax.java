package core.Players;

import core.BasicEvaluator;
import core.Chessboard;
import core.Minimax;
import core.Moves.Move;
import core.Moves.MoveGenerator;
import core.Moves.MoveValidator;
import core.Pieces.PieceColor;
import core.Utils.Position;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Filip on 11/19/2017.
 */
public class AiPlayerMinimax {

    private BasicEvaluator evaluator;
    private ConcurrentHashMap<Move, Double> results;


    public AiPlayerMinimax(){

        evaluator = new BasicEvaluator();
    }

    public Move getBestMove(Chessboard chessboard,int depth){
        double highest = -50000;
        double lowest = 50000;

        results = new ConcurrentHashMap<>();


        int counter = 0;
        List<Move> moves = MoveGenerator.getAllPossibleMoves(chessboard);
        MoveValidator.removeDangerousMoves(moves,chessboard);
        Collections.shuffle(moves);
        for (Move m : moves) {
           // System.out.println("Broj prolaza: " + counter);
          //  System.out.println("Trentno gleda za potez " + m);
            Chessboard futureChessboard = new Chessboard(chessboard);
            Minimax thread = new Minimax(chessboard, depth, futureChessboard.getOnMove() == PieceColor.WHITE, results, m);
            counter++;
            thread.start();

        }

        while (results.size() < counter){

        }
        Move bestMove = null;

        for ( Move m : results.keySet()) {
          //  System.out.println(m + " has value " + results.get(m));
            if(chessboard.getOnMove() == PieceColor.WHITE &&   results.get(m) >= highest){
                bestMove = m;
                highest = results.get(m);
            }else if(chessboard.getOnMove() == PieceColor.BLACK && results.get(m) <= lowest){
                bestMove = m;
                lowest = results.get(m);
            }
        }

        return bestMove;

    }




    /*
    private BasicEvaluator evaluator;

    public AiPlayerMinimax(){
        evaluator = new BasicEvaluator();
    }

    public Move getBestMove(Chessboard chessboard,int depth){

        double highest = -50000;
        double lowest = 50000;
        Move bestMove = null;


        int counter = 0;
        List<Move> moves = MoveGenerator.getAllPossibleMoves(chessboard);
        MoveValidator.removeDangerousMoves(moves,chessboard);
        Collections.shuffle(moves);
        for (Move m : moves) {
            //System.out.println("Broj prolaza: " + counter);
           // System.out.println("Trentno gleda za potez " + m);
            Chessboard futureChessboard = new Chessboard(chessboard);
            double currentValue = futureChessboard.getOnMove() == PieceColor.WHITE ? min(depth,futureChessboard): max(depth,futureChessboard);
          //  System.out.println("HIGH " + highest + ", LOW " + lowest);
            if(futureChessboard.getOnMove() == PieceColor.WHITE && currentValue >= highest){
                highest = currentValue;
                bestMove = m;
            }else if(futureChessboard.getOnMove() == PieceColor.BLACK && currentValue <= lowest){
                lowest = currentValue;
                bestMove = m;
            }
            counter++;
        }
        return bestMove;
    }


    public double min(int depth, Chessboard chessboard){
      //  System.out.println("Min Dubina " + depth);

       // chessboard.printChessboard();
        if(depth == 0){
            return evaluator.evaluate(chessboard);
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
            return evaluator.evaluate(chessboard);
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
*/


}
