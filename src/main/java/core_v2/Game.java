package core_v2;


import core_v2.AiStrategies.Minimax;
import core_v2.Chessboards.Chessboard;
import core_v2.Evaluators.BasicEvaluator;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Pieces.PieceColor;
import core_v2.Pieces.PieceType;
import core_v2.Utils.Position;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by Filip on 11/19/2017.
 */
public class Game {

    public static final boolean DEBUG_MODE = false;

    private GameStatus status;
    private Chessboard chessboard;
    private List<Move> possibleMoves;

    private Position whiteKingPosition;
    private Position blackKingPosition;
    private Minimax minimax;
    private boolean aiGame;

    public Game(boolean aiGame) {

        this.aiGame = aiGame;
        minimax = new Minimax(new BasicEvaluator());
        chessboard = new Chessboard.BoardBuilder().buildStartingBoard();
        possibleMoves = MoveGenerator.getAllPossibleMoves(chessboard, true);
        status = GameStatus.WHITE_ON_MOVE;
        whiteKingPosition = Position.get(4, 7);
        blackKingPosition = Position.get(4, 0);

        chessboard.printChessboard();
        System.out.println(chessboard.getOnMove() + " has " + possibleMoves.size() + " possible moves");

    }

    public boolean move(Position from, Position to) {
        if(status == GameStatus.WHITE_ON_MOVE || status == GameStatus.BLACK_ON_MOVE){
            if (chessboard.getPiece(from.to1D()).color != chessboard.getOnMove()) {
                return false;
            } else {
                for (Move move : possibleMoves) {
                    if (move.movingPiece.position.equals(from) && move.toPosition.equals(to)) {
                        System.out.println("Move " + move + " is valid");
                        if (move.movingPiece.type == PieceType.KING) {
                            if (chessboard.getOnMove() == PieceColor.WHITE)
                                whiteKingPosition = move.toPosition;
                            else
                                blackKingPosition = move.toPosition;

                            System.out.println("Changed king position to " + move.toPosition);
                        }
                        chessboard = move.make();
                        chessboard.printChessboard();
                        possibleMoves = MoveGenerator.getAllPossibleMoves(chessboard, true);
                        /*
                        for (Iterator<Move> i = possibleMoves.iterator(); i.hasNext(); ) {
                            Move m = i.next();
                            if (MoveGenerator.isMoveDangerous(m)) {
                                i.remove();
                            }
                        }*/
                        if (possibleMoves.size() > 0)
                            System.out.println(chessboard.getOnMove() + " has " + possibleMoves.size() + " possible moves");
                        else {
                            System.out.println("Player is check mated");
                            status = chessboard.getOnMove() == PieceColor.WHITE ? GameStatus.BLACK_WON : GameStatus.WHITE_WON;
                            System.out.println("Result: " + status);
                            return false;
                        }
                        status =  chessboard.getOnMove() == PieceColor.WHITE ? GameStatus.WHITE_ON_MOVE : GameStatus.BLACK_ON_MOVE;

                        return true;
                    }
                    System.out.println("Move " + move + " isn't valid");
                }

                return false;
            }
        }
        throw new RuntimeException("Game has finished");
    }

    public boolean getAiMove(){
        if(aiGame){
            Move best = minimax.getBestMove(chessboard,3);
            System.out.println("BEST MOVE EVEN: " + best);
            chessboard = best.make();
            chessboard.printChessboard();
            possibleMoves = MoveGenerator.getAllPossibleMoves(chessboard, true);
            /*
            for (Iterator<Move> i = possibleMoves.iterator(); i.hasNext(); ) {
                Move m = i.next();
                if (MoveGenerator.isMoveDangerous(m)) {
                    i.remove();
                }
            }*/
            if (possibleMoves.size() > 0)
                System.out.println(chessboard.getOnMove() + " has " + possibleMoves.size() + " possible moves");
            else {
                System.out.println("Player is check mated");
                status = chessboard.getOnMove() == PieceColor.WHITE ? GameStatus.BLACK_WON : GameStatus.WHITE_WON;
                System.out.println("Result: " + status);
                return false;
            }
            status =  chessboard.getOnMove() == PieceColor.WHITE ? GameStatus.WHITE_ON_MOVE : GameStatus.BLACK_ON_MOVE;
            return true;
        }
        return false;
    }

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }


    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public boolean isKingInCheck() {
        return MoveGenerator.isKingInCheck(chessboard);
    }

    public TreeSet<Position> getPossibleMoveDestinations(Position forPieceAt) {
        if (status == GameStatus.WHITE_ON_MOVE || status == GameStatus.BLACK_ON_MOVE) {
            TreeSet<Position> destinations = new TreeSet<>();
            for (Move move : possibleMoves){
                if(move.movingPiece.position.equals(forPieceAt)){
                    destinations.add(move.toPosition);
                }
            }
            System.out.println("Selected piece has " + destinations.size() + " possible moves");
            return destinations;
        }

        throw new RuntimeException("Game has finished");
    }

    public String[][] boardAs2DString() {
        return chessboard.to2DString();
    }

    public GameStatus getStatus() {
        return status;
    }

    public PieceColor getOnMove() {
        return chessboard.getOnMove();
    }

    public enum GameStatus {
        WHITE_ON_MOVE,
        BLACK_ON_MOVE,
        WHITE_WON,
        BLACK_WON,
        DRAW
    }


}

