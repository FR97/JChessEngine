package fr97.jchess.core.chessboard;

import fr97.jchess.core.move.Move;
import fr97.jchess.core.move.MoveGenerator;
import fr97.jchess.core.piece.Piece;
import fr97.jchess.core.piece.PieceColor;
import fr97.jchess.core.player.Player;
import fr97.jchess.core.util.PieceList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Chessboard implements Serializable {


    PieceList whitePieces;
    PieceList blackPieces;
    private final PieceColor onMove;
    private final boolean enpassantPossible;
    private int enpassantPawnPosition;

    private final Player whitePlayer;
    private final Player blackPlayer;

    public Chessboard() {

        this.whitePieces = PieceList.getStartingWhiteList();
        this.blackPieces = PieceList.getStartingBlackList();

        this.onMove = PieceColor.WHITE;
        this.enpassantPossible = false;
        List<Move> whiteMoves = new ArrayList<>(20);
        List<Move> blackMoves = new ArrayList<>(20);

        for (Piece p : whitePieces) {
            whiteMoves.addAll(MoveGenerator.findPossibleMoves(p, this));
        }
        for (Piece p : blackPieces) {
            blackMoves.addAll(MoveGenerator.findPossibleMoves(p, this));
        }

        this.whitePlayer = new Player(this, false, PieceColor.WHITE, whiteMoves, blackMoves, whitePieces.getKing().position);
        this.blackPlayer = new Player(this, false, PieceColor.BLACK, whiteMoves, blackMoves, blackPieces.getKing().position);
    }

    private Chessboard(BoardBuilder builder) {
        this.whitePieces = builder.whitePieces;
        this.blackPieces = builder.blackPieces;
        this.onMove = builder.onMove;

        this.enpassantPawnPosition = builder.enpassantPosition;
        this.enpassantPossible = enpassantPawnPosition != -1;
        List<Move> whiteMoves = new ArrayList<>(20);
        List<Move> blackMoves = new ArrayList<>(20);

        for (Piece p : whitePieces) {
            whiteMoves.addAll(MoveGenerator.findPossibleMoves(p, this));
        }
        for (Piece p : blackPieces) {
            blackMoves.addAll(MoveGenerator.findPossibleMoves(p, this));
        }

        this.whitePlayer = new Player(this, builder.whiteCastled, PieceColor.WHITE, whiteMoves, blackMoves, whitePieces.getKing().position);
        this.blackPlayer = new Player(this, builder.blackCastled, PieceColor.BLACK, blackMoves, whiteMoves, blackPieces.getKing().position);
    }

    public Piece getPiece(int position) {
        Piece p = whitePieces.get(position);
        if (p != null)
            return p;
        return blackPieces.get(position);
    }

    public PieceList getWhitePieces() {
        return whitePieces;
    }

    public PieceList getBlackPieces() {
        return blackPieces;
    }

    public PieceList getTargetPieces() {
        if (onMove.isWhite()) {
            return blackPieces;
        }
        return whitePieces;
    }

    public PieceList getActivePieces() {

        if (onMove.isWhite()) {
            return whitePieces;
        }
        return blackPieces;
    }

    public PieceColor getOnMove() {
        return this.onMove;
    }

    public boolean isEnpassantPossible() {
        return enpassantPossible;
    }

    public int getEnpassantPawnPosition() {
        return enpassantPawnPosition;
    }

    public String[] toStringArray() {
        String[] array = new String[64];
        for (int i = 0; i < 64; i++) {
            Piece p = this.getPiece(i);
            if (p != null)
                array[i] = p.forArray();
            else
                array[i] = "";
        }
        return array;
    }

    public void print() {
        StringBuilder s = new StringBuilder("\n------------------------------------\n        CHESSBOARD STATE\n\n   ");
        char c = 'A';
        for (int i = 0; i < 8; i++) {
            s.append(" ").append(c++).append("  ");
        }
        s.append("\t\n  --------------------------------\n");
        for (int i = 1; i < 65; i++) {
            int j = i / 8;
            if ((i - 1) % 8 == 0)
                s.append(8 - j).append(" ");
            s.append("| ");
            Piece p = getPiece(i - 1);
            if (p != null)
                s.append(p.forPrint());
            else
                s.append(" ");
            s.append(" ");

            if (j != 0 && i % 8 == 0)
                s.append("|\t\n  --------------------------------\n");
            else if (j == 0 && i % 8 == 0)
                s.append("\t\n  --------------------------------\n");
        }

        s.append("\nCurrently on move: ").append(onMove);

        s.append("\nEnpassant possible: ").append(enpassantPossible);
        s.append("\nWhite ").append(whitePlayer);
        s.append("\nBlack ").append(blackPlayer);
        System.out.println(s);
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getCurrent() {
        return onMove.isWhite() ? whitePlayer : blackPlayer;
    }

    public Player getOpponent() {
        return onMove.isWhite() ? blackPlayer : whitePlayer;
    }

    @Override
    public int hashCode() {
        return whitePieces != null ? whitePieces.hashCode() : 0;
    }

    public static class BoardBuilder {

        private PieceList whitePieces;
        private PieceList blackPieces;
        private boolean whiteCastled;
        private boolean blackCastled;
        private PieceColor onMove;
        private int enpassantPosition;

        public BoardBuilder() {
            this.enpassantPosition = -1;
        }

        public BoardBuilder pieces(PieceList pieces, PieceColor color) {
            if (color.isWhite())
                this.whitePieces = pieces;
            else
                this.blackPieces = pieces;

            return this;
        }

        public BoardBuilder onMove(PieceColor color) {
            this.onMove = color;
            return this;
        }

        public BoardBuilder setEnpassantPosition(int position) {
            if (position > 0 && position < 63)
                this.enpassantPosition = position;

            return this;
        }

        public BoardBuilder whiteCastled(boolean whiteCastled) {
            this.whiteCastled = whiteCastled;
            return this;
        }

        public BoardBuilder blackCastled(boolean blackCastled) {
            this.blackCastled = blackCastled;
            return this;
        }

        public Chessboard createStartingBoard() {
            return new Chessboard();
        }

        public Chessboard create() {

            return new Chessboard(this);
        }

    }

}
