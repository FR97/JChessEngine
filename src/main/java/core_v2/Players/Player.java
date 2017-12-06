package core_v2.Players;


import core_v2.Chessboards.Chessboard;
import core_v2.Moves.Move;
import core_v2.Moves.MoveGenerator;
import core_v2.Moves.MoveType;
import core_v2.Pieces.Piece;
import core_v2.Pieces.PieceColor;
import core_v2.Utils.PieceList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Player {


    public final PieceColor color;
    private final Chessboard chessboard;
    private final List<Move> legalMoves;
    private final PieceList pieces;
    private boolean inCheck;
    private boolean castled;

    public Player(final Chessboard chessboard, final boolean castled, final PieceColor color, List<Move> legalMoves, List<Move> opponentMoves, final PieceList pieces) {
        this.chessboard = chessboard;
        this.color = color;
        this.pieces = pieces;
        this.legalMoves = legalMoves;
        this.inCheck = isKingAttacked(opponentMoves);
        this.castled = castled;
    }

    // TODO implement check and castling methods
    public boolean isInCheck() {
        return this.inCheck;
    }

    public boolean isCheckMated() {

        if (this.inCheck && this.legalMoves.size() == 0) {
            return true;
        }
        return false;
    }

    public boolean isStaleMated() {
        if (!this.inCheck && this.legalMoves.size() == 0) {
            return true;
        }
        return false;
    }


    public int getKingPosition() {
        return this.pieces.getKing().position;
    }

    public boolean isCastled() {
        return castled;
    }


    public Move findMove(int from, int to) {
        for (Move move : legalMoves) {
            if (move.movingPiece.position == from && move.to == to)
                return move;
        }
        return null;
    }

    public boolean isMoveValid(Move move) {
        if (move.movingPiece.color != this.color)
            return false;
        if (move.type == MoveType.CASTLING && this.castled)
            return false;
        if (move.type == MoveType.ENPASSANT && !this.chessboard.isEnpassantPossible())
            return false;
        if (!legalMoves.contains(move))
            return false;

        return true;
    }

    public List<Move> getLegalMoves() {
        return legalMoves;
    }
/*
    public void findLegalMoves(Chessboard chessboard) {
        // Obavezno ocisti listu
        legalMoves.clear();
        for (Piece piece : pieces) {
            legalMoves.addAll(MoveGenerator.findPossibleMoves(piece, this.chessboard));
        }
    }
*/
    public void removeDangerousMoves() {
        int counter = 0;
        for (int i = 0; i < legalMoves.size(); i++) {
            Move move = legalMoves.get(i);
            Chessboard cb = move.execute();
            if (cb.getOpponent().isInCheck()){
                legalMoves.remove(i);
                counter++;
            }
        }
        System.out.println("Removed " + counter);
    }

    public boolean isKingAttacked(List<Move> enemyMoves) {
        for (Move move : enemyMoves) {
            if (move.to == this.getKingPosition()){
                System.out.println("MOVE " + move);
                return true;
            }

        }
        return false;
    }


    public PieceList getPieces() {
        return pieces;
    }


    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                ", legalMoves=" + legalMoves.size() +
                ", pieces=" + pieces.size() +
                ", inCheck=" + inCheck +
                ", castled=" + castled +
                '}';
    }
}
