package fr97.jchess.core.player;


import fr97.jchess.core.chessboard.Chessboard;
import fr97.jchess.core.move.Move;
import fr97.jchess.core.move.MoveType;
import fr97.jchess.core.piece.PieceColor;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Player {


    public final PieceColor color;
    private final Chessboard chessboard;
    private final List<Move> legalMoves;
    //private final PieceList pieces;
    private int kingPosition;
    private boolean inCheck;
    private boolean castled;

    public Player(final Chessboard chessboard, final boolean castled, final PieceColor color, List<Move> legalMoves, List<Move> opponentMoves, final int kignPosition/*, final PieceList pieces*/) {
        this.chessboard = chessboard;
        this.color = color;
        //this.pieces = pieces;
        this.legalMoves = legalMoves;
        this.legalMoves.sort((lhs, rhs) -> {

            //TODO return 1 if rhs should be before lhs
            //     return -1 if lhs should be before rhs
            //     return 0 otherwise
            if (lhs.type.value() < rhs.type.value())
                return 1;
            if (lhs.type.value() > rhs.type.value())
                return -1;

            return 0;
        });
        this.inCheck = isKingAttacked(opponentMoves);
        this.castled = castled;
        this.kingPosition = kignPosition;
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
        return this.kingPosition;
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
        for (Iterator<Move> i = legalMoves.iterator(); i.hasNext();) {
            Move move = i.next();
            Chessboard cb = move.execute();
            if (cb.getOpponent().isInCheck()){
                i.remove();
            }
        }
    }

    public boolean isKingAttacked(List<Move> enemyMoves) {
        for (Move move : enemyMoves) {
            if (move.type == MoveType.CHECKMATE){
                return true;
            }
        }
        return false;
    }


   /* public PieceList getPieces() {
        return pieces;
    }*/


    @Override
    public String toString() {
        return "Player{" +
                "color=" + color +
                ", legalMoves=" + legalMoves.size() +
              /*  ", pieces=" + pieces.size() +*/
                ", inCheck=" + inCheck +
                ", inCheckMate=" + isCheckMated() +
                ", inStaleMate=" + isStaleMated() +
                ", castled=" + castled +
                '}';
    }
}
