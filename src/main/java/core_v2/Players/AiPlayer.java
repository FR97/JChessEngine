package core_v2.Players;

import core.Moves.MoveValidator;
import core_v2.Chessboards.Chessboard;
import core_v2.Minimax;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Filip on 11/22/2017.
 */
public class AiPlayer extends Player{

    private ConcurrentHashMap<Move, Double> results;

    public AiPlayer(PieceColor color){
        super(color);

    }

    public Move getBestMove(Chessboard chessboard, int depth){

        double highest = -50000;
        double lowest = 50000;

        results = new ConcurrentHashMap<>();


        int counter = 0;
        List<Move> moves = MoveGenerator.getAllPossibleMoves(chessboard, chessboard.getOnMove());

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
}
