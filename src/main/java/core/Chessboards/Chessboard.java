package core.Chessboards;

import core.Moves.Move;
import core.Moves.MoveGenerator;
import core.Pieces.Piece;
import core.Pieces.PieceColor;
import core.Players.Player;
import core.Utils.PieceList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 11/21/2017.
 */
public class Chessboard implements Serializable {


    PieceList whitePieces;
    PieceList blackPieces;
    private PieceColor onMove;
    //private Stack<Move> moveHistory;
    private boolean enpassantPossible;
    private int enpassantPawnPosition;

    private Player whitePlayer;
    private Player blackPlayer;
    private Player current;
    private Player opponent;

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

       // this.blackPlayer.removeDangerousMoves();
        //whitePlayer.findLegalMoves(this);
        // blackPlayer.findLegalMoves(this);

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

/*
    public void move(int from, int to, boolean capture) {
        if (onMove.isWhite()) {
            whitePieces.put(to, whitePieces.remove(from).withPosition(to));
            if (capture) {
                blackPieces.remove(to);
            }
        } else {
            blackPieces.put(to, blackPieces.remove(from).withPosition(to));
            if (capture) {
                whitePieces.remove(to);
            }
        }

    }*/
/*
    public void toHistory(Move move) {
        moveHistory.push(move);
    }

    public void undo() {

        Move m = moveHistory.pop();
        m.undo(this);
        if (moveHistory.size() > 0 && moveHistory.peek().type == MoveType.PAWN_DOUBLE_JUMP) {
            setEnpassantPossible(true);
            setEnpassantPawnPosition(moveHistory.peek().to);
        }
        if(m.type == MoveType.CASTLING)
            current.setCastled(false);
    }
*/

    public Piece getPiece(int position) {
        Piece p = whitePieces.get(position);
        if (p != null)
            return p;
        return blackPieces.get(position);
    }

/*
    public void setPiece(Piece p) {
        if (p.color.isWhite())
            whitePieces.put(p.position, p);
        else
            blackPieces.put(p.position, p);
    }*/
/*
    public Piece removePiece(int position) {
        if (whitePieces.containsKey(position))
            return whitePieces.remove(position);
        else if (blackPieces.containsKey(position))
            return blackPieces.remove(position);

        return null;
    }*/


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

   /* public void setOnMove(PieceColor onMove) {
        this.onMove = onMove;
    }*/
/*
    public void toggleOnMove() {
        enpassantPossible = false;
        enpassantPawnPosition = -1;
        onMove = onMove.isWhite() ? PieceColor.BLACK : PieceColor.WHITE;

        whitePlayer.setPieces(this.whitePieces);
        blackPlayer.setPieces(this.blackPieces);

        whitePlayer.findLegalMoves(this);
        blackPlayer.findLegalMoves(this);
        if (onMove.isWhite()) {
            this.current = whitePlayer;
            this.opponent = blackPlayer;
        } else {
            this.current = blackPlayer;
            this.opponent = whitePlayer;
        }

        current.setInCheck(false);
        if (current.isKingAttacked(opponent.getLegalMoves())) {
            current.setInCheck(true);
        }
        System.out.println("Current " + current);
    }*/

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
        String s = "\n-------------------------------------\n          CHESSBOARD STATE\n\n   ";
        char c = 'A';
        for (int i = 0; i < 8; i++) {
            s += " " + c++ + "  ";
        }
        s += "\t\n  ---------------------------------\n";
        for (int i = 1; i < 65; i++) {
            int j = i / 8;
            if ((i - 1) % 8 == 0)
                s += 8 - j + " ";
            s += "| ";
            Piece p = getPiece(i - 1);
            if (p != null)
                s += p.forPrint();
            else
                s += " ";
            s += " ";

            if (j != 0 && i % 8 == 0)
                s += "|\t\n  ---------------------------------\n";
            else if (j == 0 && i % 8 == 0)
                s += "\t\n  ---------------------------------\n";
        }

        s += "\nCurrently on move: " + onMove;

        s += "\nEnpassant possible: " + enpassantPossible;
        s += "\nWhite " + whitePlayer;
        s += "\nBlack " + blackPlayer;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return whitePieces != null ? whitePieces.hashCode() : 0;
    }

    public static class BoardBuilder {

        private PieceList whitePieces;
        private PieceList blackPieces;
        private List<Piece> addedPieces;
        private boolean whiteCastled;
        private boolean blackCastled;
        private PieceColor onMove;
        private int enpassantPosition;

        public BoardBuilder() {
            this.enpassantPosition = -1;
            this.addedPieces = new ArrayList<>();
        }


        public BoardBuilder addPiece(Piece p) {
            this.addedPieces.add(p);

            return this;
        }

        public BoardBuilder addPieces(PieceList pieces, PieceColor color) {
            if (color.isWhite())
                this.whitePieces = pieces;
            else
                this.blackPieces = pieces;

            return this;
        }

        public BoardBuilder setOnMove(PieceColor color) {
            this.onMove = color;
            return this;
        }

        public BoardBuilder setEnpassantPosition(int position) {
            if (position > 0 && position < 63)
                this.enpassantPosition = position;

            return this;
        }

        public BoardBuilder setWhiteCastled(boolean whiteCastled) {
            this.whiteCastled = whiteCastled;
            return this;
        }

        public BoardBuilder setBlackCastled(boolean blackCastled) {
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
