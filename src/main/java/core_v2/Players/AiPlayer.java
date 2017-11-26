package core_v2.Players;


import core.Chessboard;
import core_v2.Chessboards.IntBoard;
import core_v2.Minimax;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.PieceColor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Filip on 11/22/2017.
 */
public class AiPlayer extends Player{

    public AiPlayer(PieceColor color){
        super(color);
    }

    public Move getBestMove(IntBoard chessboard, int depth){

        Minimax minimax = new Minimax();
        Move bestMove = null;
        double best = -50000;
        List<Move> moves = MoveGenerator.getAllPossibleMovesForPlayer(chessboard);
        Collections.shuffle(moves);
        for (Move move : moves){
            IntBoard newChessboard = new IntBoard(chessboard);
            newChessboard.makeMove(move);
            double current = minimax.negaMax(depth, newChessboard);
            if(current > best){
                best = current;
                bestMove = move;
            }

        }
        System.out.println("Best move found: " + bestMove);
        return bestMove;
    }


}
