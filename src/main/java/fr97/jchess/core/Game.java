package fr97.jchess.core;


import fr97.jchess.core.ai.AiStrategy;
import fr97.jchess.core.ai.AlphaBeta;
import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.evaluator.AdvancedEvaluator;
import fr97.jchess.core.move.Move;
import fr97.jchess.core.piece.PieceColor;
import fr97.jchess.core.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Filip on 11/19/2017.
 */
public class Game {

    private Chessboard chessboard;
    private final AiStrategy minimax;
    private final Stack<Move> moveHistory;
    private final boolean ai;

    public Game() {
        this(false);
    }

    public Game(boolean ai) {
        this.chessboard = new Chessboard();
        this.minimax = new AlphaBeta(new AdvancedEvaluator());
        this.moveHistory = new Stack<>();
        this.ai = ai;
    }



    public String[] boardAsArray() {
        return chessboard.toStringArray();
    }

    public boolean makeMove(int from, int to) {

       Move move = chessboard.getCurrent().findMove(from, to);
       if(move == null){
           return false;
       }

       this.chessboard = move.execute();
       moveHistory.push(move);
       this.chessboard.getCurrent().removeDangerousMoves();
       this.chessboard.print();
       if(ai){
           Move bestMove = this.minimax.getBestMove(this.chessboard, 4);
           this.chessboard = bestMove.execute();
           this.chessboard.getCurrent().removeDangerousMoves();
           this.chessboard.print();
       }

       return true;
    }

    public int[] getPossibleMoves(int piecePosition) {
        List<Move> moves = new ArrayList<>();

        for(Move m : chessboard.getCurrent().getLegalMoves()){
            if(m.movingPiece.position == piecePosition)
                moves.add(m);
        }

        int[] positions = new int[moves.size()];
        for (int i = 0; i <moves.size(); i++) {
            positions[i] =moves.get(i).to;
        }

        return positions;
    }

    public Player getCurrentPlayer() {
        return chessboard.getCurrent();
    }

    public Player getOpponentPlayer() {
        return chessboard.getOpponent();
    }

    public PieceColor getOnMove() {
        return chessboard.getOnMove();
    }

    public void undoLastMove() {
        this.chessboard = moveHistory.pop().undo();
        this.chessboard.print();
    }
}

