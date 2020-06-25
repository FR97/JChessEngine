package core_v2;


import core_v2.AiStrategies.AiStrategy;
import core_v2.AiStrategies.AlphaBeta;
import core_v2.AiStrategies.Minimax;
import core_v2.Chessboards.Chessboard;
import core_v2.Evaluators.AdvancedEvaluator;
import core_v2.Evaluators.BasicEvaluator;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Filip on 11/19/2017.
 */
public class Game {

    private Chessboard chessboard;
    //private List<Move> allPossibleMoves;
    private AiStrategy minimax;
    private Stack<Move> moveHistory;
    private boolean ai;

    public Game() {
        this(false);

    }

    public Game(boolean ai) {

        chessboard = new Chessboard();
        this.minimax = new AlphaBeta(new AdvancedEvaluator());
        chessboard.print();
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
           //moveHistory.push(bestMove);
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
        System.out.println("Selected piece has " + positions.length + " possible moves");
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

