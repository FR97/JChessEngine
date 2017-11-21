package core;

import core.Moves.Move;
import core.Moves.MoveGenerator;
import core.Moves.MoveType;
import core.Moves.MoveValidator;
import core.Pieces.PieceColor;
import core.Players.AiPlayerMinimax;
import core.Utils.Position;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Filip on 11/19/2017.
 */
public class Game {

    public static final boolean DEBUG_MODE = false;
    private PieceColor onMove;
    private Chessboard chessboard;
    private MoveGenerator moveGenerator;
    private BasicEvaluator evaluator;

    public Game() {
        chessboard = new Chessboard();
        chessboard.printChessboard();
        moveGenerator = new MoveGenerator();
        evaluator = new BasicEvaluator();
    }

    /**
     * @param from
     * @param to
     * @param type
     * @return true if move if valid
     */
    public boolean makeMove(Position from, Position to, MoveType type) {
        return makeMove(new Move(from, to, chessboard.getPiece(from), type));
    }

    public double evaluateChessboard(){
        return evaluator.evaluate(chessboard, 1);
    }

    /**
     * @param move to make
     * @return true if move if valid
     */
    public boolean makeMove(Move move) {
        chessboard.move(move);
        chessboard.printChessboard();
        toggleOnMove();
        return true;
    }

    public void printBestMove(){
        AiPlayerMinimax minimax = new AiPlayerMinimax();
        Move m = minimax.getBestMove(chessboard, 4);
        System.out.println("BEST MOVE EVER:" + m);
    }

    public Move getBestMove(int depth){
        AiPlayerMinimax minimax = new AiPlayerMinimax();
        return  minimax.getBestMove(chessboard, depth);
    }

    public List<Move> getPossibleMoves(Position position) {
        List<Move> moves = MoveGenerator.getPossibleMovesForPieceAt(position, chessboard);
        MoveValidator.removeDangerousMoves(moves,chessboard);


        return moves;
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
