package core_v2;


import core_v2.Chessboards.Chessboard;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Players.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/19/2017.
 */
public class Game {

    private Chessboard chessboard;
    //private List<Move> allPossibleMoves;
    private boolean ai;

    public Game() {
        this(false);

    }

    public Game(boolean ai) {

        chessboard = new Chessboard();

        chessboard.print();
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
       this.chessboard.getCurrent().removeDangerousMoves();
       this.chessboard.print();
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


    }
}

