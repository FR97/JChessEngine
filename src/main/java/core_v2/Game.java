package core_v2;


import core_v2.Chessboards.Chessboard;
import core_v2.Chessboards.IBoard;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Moves.MoveType;
import core_v2.Pieces.PieceColor;
import core_v2.Players.Player;
import core_v2.Utils.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/19/2017.
 */
public class Game {

    public static final boolean DEBUG_MODE = true;
    private PieceColor onMove;
    private Chessboard chessboard;

    private Player whitePlayer;
    private Player blackPlayer;


    public Game() {
        chessboard = new Chessboard();

        whitePlayer = new Player(PieceColor.WHITE);
        blackPlayer = new Player(PieceColor.BLACK);
    }

    /**
     * @param from
     * @param to
     * @param type
     * @return true if move if valid
     */
    public boolean makeMove(Position from, Position to, MoveType type) {

        return true;
    }

    public double evaluateChessboard() {
        return 0;
    }

    /**
     * @param move to make
     * @return true if move if valid
     */
    public boolean makeMove(Move move) {
        chessboard.makeMove(move);

        toggleOnMove();
        return true;
    }

    /*
    public void printBestMove(){
        AiPlayerMinimax minimax = new AiPlayerMinimax();
        Move m = minimax.getBestMove(chessboard, 4);
        System.out.println("BEST MOVE EVER:" + m);
    }

    public Move getBestMove(int depth){
        AiPlayerMinimax minimax = new AiPlayerMinimax();
        return  minimax.getBestMove(chessboard, depth);
    }*/

    public List<Move> getPossibleMoves(Position position) {
        List<Move> moves = new ArrayList<>();


        return moves;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void printPossibleMoves(Position position) {
        List<Move> moves = getPossibleMoves(position);
        for (Move move : moves) {
            System.out.println(move);
        }
    }

    private void toggleOnMove() {
        onMove = (onMove == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

}

